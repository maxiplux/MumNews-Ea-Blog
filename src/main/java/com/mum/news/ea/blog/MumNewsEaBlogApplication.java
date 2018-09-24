package com.mum.news.ea.blog;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class MumNewsEaBlogApplication {

    public static void main(String[] args) {

        //new File(UploadController.UPLOADED_FOLDER).mkdir();
        SpringApplication.run(MumNewsEaBlogApplication.class, args);
    }
}
