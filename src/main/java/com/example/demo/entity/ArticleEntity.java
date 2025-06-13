package com.example.demo.entity;

import com.example.demo.domain.Article;
import com.example.demo.entity.base.BaseInstanceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "article", schema = "demo")
public class ArticleEntity extends BaseInstanceEntity<Article> {

    @NotNull
    @Column(name = "title", nullable = false, updatable = false, length = 100)
    private String title;

    @NotNull
    @Column(name = "author", nullable = false, updatable = false)
    private String author;

    @NotNull
    @Column(name = "content", nullable = false, updatable = false)
    private String content;

    @Column(name = "publishing_date", nullable = false, updatable = false)
    private Date publishingDate;

    @Override
    public Article convert() {
        return new Article(getId(),
                author,
                title,
                content,
                publishingDate);
    }
}