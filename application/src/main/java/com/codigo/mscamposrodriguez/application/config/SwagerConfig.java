package com.codigo.mscamposrodriguez.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwagerConfig {
    @Bean
    public OpenAPI CustonOpenAPI(){
        return new OpenAPI().info(new Info().title("Apis del MS-CAMPOSRODRIGUEZ").version("1.0"));
    }
}
