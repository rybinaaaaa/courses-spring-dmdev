package core.rybina.mapper;

import core.rybina.database.entity.Company;
import core.rybina.dto.CompanyReadDto;
import org.springframework.stereotype.Component;

@Component
public class CompanyReadMapper implements Mapper<Company, CompanyReadDto> {
    @Override
    public CompanyReadDto map(Company from) {
        return new CompanyReadDto(from.getId(), from.getName());
    }
}
