package com.mum.news.ea.blog.controllers;

import com.mum.news.ea.blog.models.Article;
import com.mum.news.ea.blog.models.Comment;
import com.mum.news.ea.blog.models.User;
import com.mum.news.ea.blog.repositories.ArticleDao;
import com.mum.news.ea.blog.repositories.CategoryDao;
import com.mum.news.ea.blog.repositories.UserDao;
import com.mum.news.ea.blog.storage.StorageService;

import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;


/**
 *Created by IntelliJ IDEA.
 *User: Theodros
 *Date: 9/21/2018
 *Time: 5:25 PM
 */
@Controller
public class CreateArticleController {

    public static String filePath=System.getProperty("user.dir");

    @Autowired
    ArticleDao articleDao;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    UserDao userDao;

    private final StorageService storageService;
    //https://www.baeldung.com/properties-with-spring
    @Value("${image.path}")
    private String imagefolder;

    @Autowired
    public CreateArticleController(StorageService storageService) {
        this.storageService = storageService;
    }


    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String newForm(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userDao.findByEmail(auth.getName());
        model.addAttribute("cateogories", categoryDao.findAll());
        model.addAttribute("currentUser", user);
        return "addArticle";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createArticleUpload(@Valid @ModelAttribute Article article,
                                   @RequestParam("file") MultipartFile file, BindingResult result, Model model,
                                   RedirectAttributes redirectAttributes) {


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = userDao.findByEmail(auth.getName());

        Path imagePath=Paths.get(String.format("%s//%s",Paths.get(this.imagefolder).toFile().getAbsolutePath() , file.getOriginalFilename()) );
        if (!file.isEmpty())
        {


            try {
                byte[] bytes = file.getBytes();

                FileOutputStream outputStream = new FileOutputStream(imagePath.toString());
                outputStream.write(file.getBytes());
                outputStream.close();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        article.setImage(imagePath.toString());

        article.setPublicationDate(LocalDate.now());
        article.setAuthor(user);

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


}



