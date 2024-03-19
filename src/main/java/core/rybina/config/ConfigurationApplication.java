package core.rybina.config;

import core.rybina.database.pool.CrudRepository;
import core.web.config.ConfigurationWebApplication;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

import static org.springframework.context.annotation.ComponentScan.*;

//@ImportResource("classpath:application.xml")
@Import(ConfigurationWebApplication.class)
@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "core.rybina", useDefaultFilters = false, includeFilters = {
        @Filter(type = FilterType.ANNOTATION, value = Component.class),
        @Filter(type = FilterType.ASSIGNABLE_TYPE, value = CrudRepository.class),
        @Filter(type = FilterType.REGEX, pattern = "core\\..+Repository")
})
public class ConfigurationApplication {
}
