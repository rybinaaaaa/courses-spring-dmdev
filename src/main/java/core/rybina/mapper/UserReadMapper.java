package core.rybina.mapper;

import core.rybina.database.entity.User;
import core.rybina.dto.CompanyReadDto;
import core.rybina.dto.UserReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserReadMapper implements Mapper<User, UserReadDto> {

    private final CompanyReadMapper companyReadMapper;

    @Override
    public UserReadDto map(User from) {
        CompanyReadDto company = Optional.ofNullable(from.getCompany())
                .map(companyReadMapper::map)
                .orElse(null);
        return new UserReadDto(
                from.getId(),
                from.getUsername(),
                from.getFirstname(),
                from.getLastname(),
                from.getBirthdate(),
                from.getRole(),
                company
        );
    }
}
