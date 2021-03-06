package com.mum.news.ea.blog.controllers;

import com.mum.news.ea.blog.models.Article;
import com.mum.news.ea.blog.models.Category;
import com.mum.news.ea.blog.models.Comment;
import com.mum.news.ea.blog.models.User;
import com.mum.news.ea.blog.repositories.ArticleDao;
import com.mum.news.ea.blog.repositories.CategoryDao;
import com.mum.news.ea.blog.repositories.CommentDao;
import com.mum.news.ea.blog.repositories.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * User: franc
 * Date: 21/09/2018
 * Time: 2:43
 */
@Controller
public class ReadArticleController {


    @Autowired
    private CommentDao commentDao;

    @Autowired
    ArticleDao articleDao;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(@RequestParam(value = "category", required = false, defaultValue = "-1") Long categoryId,
                        @RequestParam(value = "dateOrder", required = true, defaultValue = "asc") String dateOrder,
                        Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userDao.findByEmail(auth.getName());

        Optional<Category> categoryOptional = categoryDao.findById(categoryId);
        List<Article> articles;
        Category categorySelected;
        if (!categoryOptional.isPresent()) {
            if (dateOrder.equals("desc")) {
                articles = articleDao.findAllByOrderByPublicationDateDesc();
            } else {
                articles = articleDao.findAllByOrderByPublicationDateAsc();
            }
            categorySelected = new Category();
        } else {
            if (dateOrder.equals("desc")) {
                articles = articleDao.findAllByCategory_IdOrderByPublicationDateDesc(categoryId);
            } else {
                articles = articleDao.findAllByCategory_IdOrderByPublicationDateAsc(categoryId);
            }
            categorySelected = categoryOptional.get();
        }
        model.addAttribute("articles", articles);
        model.addAttribute("categoryList", categoryDao.findAll());
        model.addAttribute("category", categorySelected);
        model.addAttribute("currentUser", user);
        return "index";
    }

    @RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable long id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userDao.findByEmail(auth.getName());

        model.addAttribute("article", this.articleDao.findById(id).get());
        model.addAttribute("currentUser", user);
        model.addAttribute("comment", new Comment());
        List<Comment> ll = this.commentDao.findAllByArticleId(id);
        model.addAttribute("comments", ll);
        return "articleDetail";
    }


    @RequestMapping(value = {"/createComment/{id}"}, method = RequestMethod.POST)
    public ModelAndView createComment(@PathVariable long id, @Valid Comment comment, BindingResult bindingResult) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userDao.findByEmail(auth.getName());

        comment.setArticleId(id);
        comment.setUser(user);

        this.commentDao.save(comment);

        return new ModelAndView("redirect:/article/" + id);

    }
}
