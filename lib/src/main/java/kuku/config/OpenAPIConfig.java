package kuku.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
@OpenAPIDefinition
public class OpenAPIConfig{
	@Bean
    public OpenAPI baseOpenAPI(){
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
            .info(
                new Info()
                    .title("OpenAPI")
                    .version("v0.0.1")
                    .description("SpringBoot 3.0.0 application")
            )
            .addSecurityItem(
                new SecurityRequirement()
                    .addList(securitySchemeName)
            )
            .components(
                new Components()
                .addSecuritySchemes(
                    securitySchemeName,
                    new SecurityScheme()
                        .name(securitySchemeName)
                        .type(SecurityScheme.Type.APIKEY)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                )
            );
    }
}
