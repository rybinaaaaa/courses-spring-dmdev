package core.rybina.database.integration.repository;

import core.rybina.database.entity.Company;
import core.rybina.database.integration.annotation.IT;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.support.TransactionTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

//import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
//@Transactional
class CompanyRepositoryTest {

    private final EntityManager entityManager;
    private final TransactionTemplate transactionTemplate;

    @Test
    void findById() {
        transactionTemplate.executeWithoutResult(tx -> {
                Company company = entityManager.find(Company.class, 1);
                assertNotNull(company);
                Assertions.assertThat(company.getLocales()).hasSize(2);
        });
    }
}