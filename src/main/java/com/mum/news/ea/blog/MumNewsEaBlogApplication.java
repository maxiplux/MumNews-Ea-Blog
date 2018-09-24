package com.mum.news.ea.blog;

import com.mum.news.ea.blog.storage.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)

public class MumNewsEaBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(MumNewsEaBlogApplication.class, args);
    }
}
