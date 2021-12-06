package com.br.panacademy.devcompilers.bluebank.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.br.panacademy.devcompilers.bluebank.controller"))              
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(metaInfo())
          .useDefaultResponseMessages(false);
    }
    
    private ApiInfo metaInfo() {
        return new ApiInfo(
                "Spring Boot Api Blue Bank",
                "API da Blue Bank.",
                "1.0",
                null,
                new Contact("Squad 8", "aqui - email",
                        "outro - email aqui"),
                "Tipo License",
                "https://opensource.org/licenses/MIT",
                new ArrayList<>()
        );
    }

}
