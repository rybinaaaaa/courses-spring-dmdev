package core.rybina.database.integration.service;

import core.rybina.database.integration.IntegrationTestBase;
import core.rybina.database.integration.annotation.IT;
import core.rybina.database.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

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
//Для использования testContainer
public class CompanyServiceIT extends IntegrationTestBase {
    private static final Integer COMPANY_ID = 1;

//    @Autowired
    private final CompanyService companyService;

    @Test
    void findById() {
//        Optional<CompanyReadDto> actualResult = companyService.findById(COMPANY_ID);
//
//        assertTrue(actualResult.isPresent());
//
//        CompanyReadDto expectedResult = new CompanyReadDto(COMPANY_ID);
//        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));
//
//        verify(eventPublisher).publishEvent(any(EntityEvent.class));
    }
}
