package com.mum.news.ea.blog.controllers;

import com.mum.news.ea.blog.models.Article;
import com.mum.news.ea.blog.repositories.ArticleDao;
import com.mum.news.ea.blog.repositories.CategoryDao;
import com.mum.news.ea.blog.repositories.UserDao;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.hibernate.Hibernate;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;

/**
 *Created by IntelliJ IDEA.
 *User: Theodros
 *Date: 9/21/2018
 *Time: 5:25 PM
 */
@Controller
public class CreateArticleController {

    //private static String filePath = "C:\\Users\\hp\\Documents\\images\\";
    //private String filePath="D:\\EA\\Project\\MUM-Blog\\MumNews-Ea-Blog\\src\\main\\webapp\\META-INF\\images\\uploads\\";
    // private Environment environment;
    public static String filePath=System.getProperty("user.dir");

    @Autowired
    ArticleDao articleDao;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    UserDao userDao;

    @Autowired
    private Environment environment;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String newForm(Model model) {
        model.addAttribute("authors",userDao.findAll());
        model.addAttribute("cateogories", categoryDao.findAll());
        return "addArticle";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createArticleUpload(@Valid @ModelAttribute Article article,
                                   @RequestParam("file") MultipartFile file, BindingResult result, Model model,
                                   RedirectAttributes redirectAttributes) {
        try {
            // Get the file and save it somewhere
            String imageName = System.currentTimeMillis()+"."+ file.getOriginalFilename().split("\\.")[1];
            byte[] bytes = file.getBytes();
            //String path = environment.getProperty("image.path");
            String filePathDb = environment.getProperty("image.path");
            Path path = Paths.get(String.format("%s\\%s\\%s",filePath,filePathDb,imageName));



            Files.write(path,bytes);

            article.setImage(String.format("/uploads/%s",imageName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        articleDao.save(article);
        return "redirect:/";
    }


    /*@ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(FileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }*/


}



