package core.rybina.database.integration.repository;

import core.rybina.database.entity.Company;
import core.rybina.database.integration.annotation.IT;
import core.rybina.database.repository.CompanyRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
//@Transactional
class CompanyRepositoryTest {

    private static final Integer APPLE_ID = 5;
    private final EntityManager entityManager;
    private final TransactionTemplate transactionTemplate;
    private final CompanyRepository companyRepository;

    @Test
    void findById() {
        transactionTemplate.executeWithoutResult(tx -> {
            Company company = entityManager.find(Company.class, 1);
            assertNotNull(company);
            assertThat(company.getLocales()).hasSize(2);
        });
    }

    @Test
    void delete() {
        Optional<Company> maybeCompany = companyRepository.findById(APPLE_ID);
        assertThat(maybeCompany.isPresent()).isTrue();
        maybeCompany.ifPresent(companyRepository::delete);
        entityManager.flush();
    }
}