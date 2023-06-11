package com.jaeho.bulbul;

import com.jaeho.bulbul.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
// FileStorageProperties를 사용하기 위해서는 @EnableConfigurationProperties를 사용해야 한다.
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class BulbulApplication {

    public static void main(String[] args) {
        SpringApplication.run(BulbulApplication.class, args);
    }
}
