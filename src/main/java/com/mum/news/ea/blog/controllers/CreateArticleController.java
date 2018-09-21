package com.mum.news.ea.blog.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User: franc
 * Date: 21/09/2018
 * Time: 2:43
 */

public interface CreateArticleController  {

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    default public String create(Model model) {
        return "index";
    }
}
