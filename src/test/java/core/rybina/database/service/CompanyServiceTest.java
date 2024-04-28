package core.rybina.database.service;

import core.rybina.database.entity.Company;
import core.rybina.database.integration.IntegrationTestBase;
import core.rybina.database.integration.annotation.IT;
import core.rybina.dto.CompanyReadDto;
import core.rybina.listener.entity.EntityEvent;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RequiredArgsConstructor
@IT
class CompanyServiceTest extends IntegrationTestBase {
    private static final Integer COMPANY_ID = 1;

    @Autowired
    private final CompanyService companyService;

    @Test
    void findById() {
//        Optional<CompanyReadDto> actualResult = companyService.findById(COMPANY_ID);
//
//        assertTrue(actualResult.isPresent());
//
//        CompanyReadDto expectedResult = new CompanyReadDto(COMPANY_ID);
//        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));
    }
}