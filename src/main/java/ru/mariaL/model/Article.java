package ru.mariaL.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Article implements Serializable, Comparable<Article> {
    @Id
    @GeneratedValue
    private String id;
    @Column
    private String title;
    @Column(length = 1000000)
    @Lob
    private String content;
    @Column
    private long creationTimestamp;

    public Article() {
        this.creationTimestamp = System.currentTimeMillis();
    }

    public Article(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Override
    public int compareTo(Article that) {
        return Long.compare(this.creationTimestamp, that.creationTimestamp);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }
}
