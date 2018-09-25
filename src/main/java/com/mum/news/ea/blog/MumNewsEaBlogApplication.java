package com.mum.news.ea.blog;

import com.mum.news.ea.blog.storage.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.File;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class MumNewsEaBlogApplication {
    public static void main(String[] args) {
        setupProject();
        SpringApplication.run(MumNewsEaBlogApplication.class, args);
    }

    private static void setupProject() {
        File file = new File("upload-dir");
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
    }
}
