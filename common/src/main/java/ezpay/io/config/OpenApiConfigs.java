package ezpay.io.config;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.security.*;
import io.swagger.v3.oas.models.servers.Server;

@OpenAPIDefinition
@Configuration
public class OpenApiConfigs {
    @Bean
    public OpenAPI customOpenAPI(
            @Value("${openapi.service.title}") String serviceTitle,
            @Value("${openapi.service.version}") String serviceVersion,
            @Value("${openapi.service.url}") String url) {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .servers(List.of(new Server().url(url)))
                //.components(
                //        new Components()
                //                .addSecuritySchemes(
                //                        securitySchemeName,
                //                        new SecurityScheme()
                //                                .type(SecurityScheme.Type.HTTP)
                //                                .scheme("bearer")
                //                                .bearerFormat("JWT")))
                //.security(List.of(new SecurityRequirement().addList(securitySchemeName)))
                .info(new Info().title("EZPay Application API")
                        .version(serviceVersion)
                        .contact(new Contact()
                        .name("Ahmed Amine T.B")
                        .email("aminetbo@gmail.com")
                        .url("https://aminetb2a.github.io/")));
    }
}
