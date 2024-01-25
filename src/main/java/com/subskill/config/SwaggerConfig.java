package com.subskill.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
@OpenAPIDefinition(info = @Info(title = "Your API", version = "v1"))
public class SwaggerConfig {

}
