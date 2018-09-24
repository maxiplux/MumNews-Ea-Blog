package com.mum.news.ea.blog.controllers;

import com.mum.news.ea.blog.models.Article;
import com.mum.news.ea.blog.repositories.ArticleDao;
import com.mum.news.ea.blog.repositories.CategoryDao;
import com.mum.news.ea.blog.repositories.UserDao;
import com.mum.news.ea.blog.storage.StorageService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.hibernate.Hibernate;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.core.io.Resource;
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

    private final StorageService storageService;

    @Autowired
    public CreateArticleController(StorageService storageService) {
        this.storageService = storageService;
    }


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


        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");


        article.setImage("/images/"+file.getOriginalFilename());
        articleDao.save(article);
        return "redirect:/";
    }

    @GetMapping("/images/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }


    /*@ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(FileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }*/


}



