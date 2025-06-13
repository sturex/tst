package com.example.demo.service;

import com.example.demo.domain.Article;
import com.example.demo.domain.DailyArticleStatistics;
import org.springframework.data.domain.Page;

import java.util.Date;

public interface ArticleService {

    Article create(String author, String title, String content, Date publishingDate);

    Page<Article> getArticles(int page, int size);
    DailyArticleStatistics getDailyArticleStatistics();
}
