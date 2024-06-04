package core.rybina.database.service;

import core.rybina.database.entity.Company;
import core.rybina.database.repository.CompanyRepository;
import core.rybina.dto.CompanyReadDto;
import core.rybina.listener.entity.AccessType;
import core.rybina.listener.entity.EntityEvent;
import core.rybina.mapper.CompanyReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final CompanyReadMapper companyReadMapper;

    public Optional<CompanyReadDto> findById(Integer id) {
        return companyRepository.findById(id)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
                    return new CompanyReadDto(entity.getId(), null);
                });
    }

    public List<CompanyReadDto> findAll() {
        return companyRepository.findAll()
                .stream()
                .map(companyReadMapper::map)
                .toList();
    }
}
