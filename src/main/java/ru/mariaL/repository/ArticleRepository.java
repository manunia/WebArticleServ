package ru.mariaL.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.mariaL.model.Article;

import java.util.List;

@Repository
public interface ArticleRepository extends CrudRepository<Article,String> {

    @Query("SELECT a FROM Article a WHERE a.title LIKE %?1%")
    List<Article> findByTitle(String title);

    List<Article> findByContentContaining(String string);

    @Query("SELECT a FROM Article a WHERE a.title=:title")
    List<Article> fetchArticles(@Param("title") String title);
}
