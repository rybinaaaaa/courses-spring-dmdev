package core.rybina.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@SecurityScheme(
//        name = "auth",
//        type = SecuritySchemeType.OAUTH2,
//        flows = @OAuthFlows(
//                authorizationCode = @OAuthFlow(
//                        authorizationUrl = "/oauth2/authorization/google",
//                        tokenUrl = "https://www.googleapis.com/oauth2/v4/token"
//                ))
//)
@Configuration
@RequiredArgsConstructor
public class OpenApiConfiguration {

    private final OAuth2Properties oauth2Properties;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("auth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.OAUTH2)
                                        .flows(new OAuthFlows()
                                                .authorizationCode(new OAuthFlow()
                                                        .authorizationUrl(oauth2Properties.getAuthorizationUrl())
                                                        .tokenUrl(oauth2Properties.getTokenUrl())))))
                .info(new Info().title("Default API Title").version("1.0.0"));
    }
}
