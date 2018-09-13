package ru.mariaL.service;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mariaL.repository.ArticleRepository;
import ru.mariaL.model.Article;

import java.util.*;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository repository;

    public ArticleRepository getRepository() {
        return repository;
    }

    public void save(Article article) {
        repository.save(article);
    }

    public List<Article> getAll() {
        List<Article> articles = new ArrayList<>();
        repository.findAll().forEach(articles::add);
        return articles;
    }

    public List<Article> getArticleByTitle(String title) {
        List<Article> articles = new ArrayList<>();
        repository.fetchArticles(title).forEach(articles::add);
        return articles;
    }

    public List<Article> getArticleByContentContaining(String string) {
        List<Article> articles = new ArrayList<>();
        repository.findByContentContaining(string).forEach(articles::add);
        return articles;
    }

    @Modifying
    public void deleteArticle(Article article) {

        repository.delete(getArticleByTitle(article.getTitle()));
    }
}
