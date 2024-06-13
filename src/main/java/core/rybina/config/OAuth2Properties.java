package core.rybina.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Getter
@PropertySource(value = "classpath:auth.yml", factory = YamlPropertySourceFactory.class)
public class OAuth2Properties {

    @Value("${oauth2.authorization-url}")
    private String authorizationUrl;

    @Value("${oauth2.token-url}")
    private String tokenUrl;
}

