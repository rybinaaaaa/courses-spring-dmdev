package core.rybina.database.integration;

import core.rybina.database.integration.annotation.IT;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;

@IT
@Sql({"classpath:sql/data.sql"})
public class IntegrationTestBase {

    private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:14.1");

    @BeforeAll
    static void runContainer() {
        container.start();
    }

//    мы это добавляем потому, что сами тест контейнеры запускаются по умолчанию с test паролем и юзернеймом
//    но мы не знаем адрес где он запускается
    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
    }
}
