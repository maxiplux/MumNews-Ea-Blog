package com.mum.news.ea.blog.controllers;

import com.mum.news.ea.blog.repositories.ArticleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController implements CreateArticleController, DetailArticleController {

    @Autowired
    private ArticleDao articleDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("articles", this.articleDao.findAll());
        return "index";
    }

}
