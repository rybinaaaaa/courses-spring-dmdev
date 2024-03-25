package core.rybina.database.integration;

import core.rybina.ApplicationRunner;
import core.rybina.database.entity.Company;
import core.rybina.database.integration.annotation.IT;
import core.rybina.database.service.CompanyService;
import core.rybina.dto.CompanyReadDto;
import core.rybina.listener.entity.EntityEvent;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

//ЛИБО

//@ExtendWith(SpringExtension.class)
////initializer добавляем в случае если у нас проперти в формате .yaml .yml
//@ContextConfiguration(classes = ApplicationRunner.class, initializers = ConfigDataApplicationContextInitializer.class)

//ЛИБО (equal)
//@ActiveProfiles("test")
//@SpringBootTest

@IT
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class CompanyServiceIT {
    private static final Integer COMPANY_ID = 1;

//    @Autowired
    private final CompanyService companyService;

    @Test
    void findById() {
        Optional<CompanyReadDto> actualResult = companyService.findById(COMPANY_ID);

        assertTrue(actualResult.isPresent());

        CompanyReadDto expectedResult = new CompanyReadDto(COMPANY_ID);
        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));

//        verify(eventPublisher).publishEvent(any(EntityEvent.class));
    }
}
