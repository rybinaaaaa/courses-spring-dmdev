package core.rybina.config;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Conditional(JpaCondition.class)
@Configuration
public class JpaConfiguration {

//    @ConfigurationProperties(prefix = "db")
//    @Bean
//    public DbProperties dbProperties() {
//        return new DbProperties();
//    }

    @PostConstruct
    void init() {
        System.out.println("JPA configuration is enabled");
    }
}
