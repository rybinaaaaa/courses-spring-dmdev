package core.rybina.database.service;

import core.rybina.database.entity.Company;
import core.rybina.database.repository.CrudRepository;
import core.rybina.dto.CompanyReadDto;
import core.rybina.listener.entity.AccessType;
import core.rybina.listener.entity.EntityEvent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {

    private final CrudRepository<Integer, Company> companyRepository;
    private final ApplicationEventPublisher eventPublisher;

    public CompanyService(@Qualifier("companyRepository") CrudRepository<Integer, Company> companyRepository, ApplicationEventPublisher eventPublisher) {
        this.companyRepository = companyRepository;
        this.eventPublisher = eventPublisher;
    }

    public Optional<CompanyReadDto> findById(Integer id) {
        return companyRepository.findById(id)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
                    return new CompanyReadDto(entity.getId());
                });
    }
}
