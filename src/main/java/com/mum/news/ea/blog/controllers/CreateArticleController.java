package com.mum.news.ea.blog.controllers;

import com.mum.news.ea.blog.models.Article;
import com.mum.news.ea.blog.models.Comment;
import com.mum.news.ea.blog.models.User;
import com.mum.news.ea.blog.repositories.ArticleDao;
import com.mum.news.ea.blog.repositories.CategoryDao;
import com.mum.news.ea.blog.repositories.UserDao;
import com.mum.news.ea.blog.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
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

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        article.setImage("/images/"+file.getOriginalFilename());

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

    @RequestMapping(value = {"/createArticle"}, method = RequestMethod.POST)
    public ModelAndView createUser(@Valid Comment comment, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();

        return new ModelAndView("redirect:/");

    }
}



