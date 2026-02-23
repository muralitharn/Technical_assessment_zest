package com.zest.technical.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Info;
@Configuration
@SecurityScheme(name = "Bearer Authentication", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
public class SwaggerConfig {

    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI().info(new Info().title("Zest Technical Assessment")
                        .description("Product Assessment APIs")
                        .version("1.0")
                        .license(new License().name("Zest").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation().description("SpringDoc Docs")
                        .url("https://springboot.wiki.github.org/docs"));
    }
}