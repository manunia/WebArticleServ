package ru.mariaL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mariaL.model.Article;
import ru.mariaL.service.ArticleService;

import java.util.Map;


@Controller
@RequestMapping("/blog")
public class MainController {

    @Autowired
    private ArticleService service;
    //главная страница
    @RequestMapping
    public String mainPage(Model model) {
        model.addAttribute("articles", service.getAll());
        return "main";
    }
    //страница создания статьи
    @RequestMapping(value = "/editor")
    public String editorPage(Model model) {
        model.addAttribute("article",new Article());
        return "editor";
    }
    //обрабочик формы создания статьи
    @RequestMapping(value = "/editor/submit", method = RequestMethod.POST)
    public String submitArticle(@ModelAttribute Article article) {
        service.save(article);
        return "redirect:../";
    }
    //метод поиска по названию
    @RequestMapping(value = "/editor/filter", method = RequestMethod.POST)
    public String findArticleByTitle(Map<String,Object> model, @RequestParam String title){
        Iterable<Article> articles;
        if (title != null && !title.isEmpty()) {
            articles = service.getArticleByTitle(title);
        } else {
            articles = service.getAll();
        }
        model.put("articles",articles);
        return "main";
    }
    //метод поиска по названию
    @RequestMapping(value = "/editor/filterSubstr", method = RequestMethod.POST)
    public String findArticleByContent(Model model, @RequestParam String string){
        Iterable<Article> articles;
        if (string != null && !string.isEmpty()) {
            articles = service.getArticleByContentContaining(string);
        } else {
            articles = service.getAll();
        }
        model.addAttribute("articles",articles);
        return "main";
    }
    //удаление статьи
    @RequestMapping(value = "/editor/delete", method = RequestMethod.GET)
    public String deletedArticle(@ModelAttribute("article") Article article){
        service.deleteArticle(article);
        return "redirect:../";
    }
}
