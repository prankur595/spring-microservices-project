package com.prankur.microservices.inventory.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig
{
    @Bean
    public OpenAPI inventoryServiceAPI()
    {
        return new OpenAPI().info(new Info()
                                          .title("Inventory Service API")
                                          .description("This is the REST API Documentation for Inventory Service")
                                          .version("v0.0.1")
                                          .license(new License().name("Licensed to Prankur Bishnoi")))
                            .externalDocs(new ExternalDocumentation()
                                                  .description("Click to refer to the Product Service documentation")
                                                  .url("http://localhost:8080/swagger-ui.html"));
    }

}
