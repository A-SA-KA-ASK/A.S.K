package com.example.askBackend.config.Swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {
// http://localhost:7777/swagger-ui/index.html
    @Bean
    public OpenAPI api() {
        return new OpenAPI().info(this.apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("A.S.K")
                .version("1.0")
                .description("아무나 사용할 수 있는 카페")
                ;
    }
}
