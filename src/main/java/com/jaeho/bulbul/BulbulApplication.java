package com.jaeho.bulbul;

import com.jaeho.bulbul.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
// FileStorageProperties를 사용하기 위해서는 @EnableConfigurationProperties를 사용해야 한다.
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class BulbulApplication {

    public static void main(String[] args) {
        SpringApplication.run(BulbulApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:8081")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
                        .allowCredentials(true);
            }
        };
    }
}
