package core.rybina.database.service;

import com.querydsl.core.types.Predicate;
import core.rybina.database.querydsl.QPredicates;
import core.rybina.database.repository.UserRepository;
import core.rybina.dto.UserCreateEditDto;
import core.rybina.dto.UserFilter;
import core.rybina.dto.UserReadDto;
import core.rybina.mapper.UserCreateEditMapper;
import core.rybina.mapper.UserReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static core.rybina.database.entity.QUser.user;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateEditMapper userCreateEditMapper;

    public List<UserReadDto> findAll() {
        return userRepository.findAll().stream()
                .map(userReadMapper::map)
                .toList();
    }

    public Page<UserReadDto> findAll(UserFilter userFilter, Pageable pageable) {
        Predicate predicate = QPredicates.builer()
                .add(userFilter.firstname(), user.firstname::containsIgnoreCase)
                .add(userFilter.lastname(), user.lastname::containsIgnoreCase)
                .add(userFilter.birthdate(), user.birthdate::before)
                .build();

        return userRepository.findAll(predicate, pageable)
                .map(userReadMapper::map);
    }

    public Optional<UserReadDto> findById(Long id) {
        return userRepository.findById(id)
                .map(userReadMapper::map);
    }

    @Transactional
    public UserReadDto create(UserCreateEditDto user) {
        return Optional.of(user)
                .map(userCreateEditMapper::map)
                .map(userRepository::save)
                .map(userReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<UserReadDto> update(Long id, UserCreateEditDto user) {
        return userRepository.findById(id)
                .map(entity -> userCreateEditMapper.map(user, entity))
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return userRepository.findById(id)
                .map(entity -> {
                    userRepository.delete(entity);
                    return true;
                })
                .orElse(false);
    }
}
