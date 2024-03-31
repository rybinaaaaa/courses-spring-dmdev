package core.rybina.config;

import core.rybina.database.pool.ConnectionPool;
import core.rybina.database.repository.CompanyRepository;
//import core.rybina.database.repository.CrudRepository;
import core.web.config.ConfigurationWebApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.context.annotation.ComponentScan.*;

//@ImportResource("classpath:application.xml")
@Configuration
@Import(ConfigurationWebApplication.class)
//@PropertySource("classpath:application.properties")
//@ComponentScan(basePackages = "core.rybina", useDefaultFilters = false, includeFilters = {
//        @Filter(type = FilterType.ANNOTATION, value = Component.class),
//        @Filter(type = FilterType.ASSIGNABLE_TYPE, value = CrudRepository.class),
//        @Filter(type = FilterType.REGEX, pattern = "core\\..+Repository")
//})
public class ConfigurationApplication {

    @Bean
    public ConnectionPool pool2(@Value("${db.username}") String username) {
        return new ConnectionPool(username, 20);
    }

    @Bean
    @Profile("!prod")
    public ConnectionPool pool3(@Value("${db.pool.size}") Integer size){
        return new ConnectionPool("test-pool", size);
    }
}
