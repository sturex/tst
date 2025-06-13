package com.example.demo.service;

import com.example.demo.domain.Article;
import com.example.demo.domain.DailyArticleStatistics;
import com.example.demo.domain.DailyArticleStatisticsRow;
import com.example.demo.entity.ArticleEntity;
import com.example.demo.repository.ArticleEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleEntityRepository articleEntityRepository;

    @Autowired
    public ArticleServiceImpl(ArticleEntityRepository articleEntityRepository) {
        this.articleEntityRepository = articleEntityRepository;
    }

    @Override
    public Article create(String author, String title, String content, Date publishingDate) {
        ArticleEntity articleEntity = ArticleEntity.builder()
                .content(content)
                .author(author)
                .title(title)
                .publishingDate(publishingDate)
                .build();
        return articleEntityRepository.save(articleEntity).convert();
    }

    @Override
    public Page<Article> getArticles(int page, int size) {
        return articleEntityRepository.findAll(PageRequest.of(page, size))
                .map(ArticleEntity::convert);
    }

    @Override
    public DailyArticleStatistics getDailyArticleStatistics() {
        Map<LocalDate, Integer> map = articleEntityRepository.findDailyArticleCountsLast7Days().stream()
                .collect(Collectors.toMap(result -> ((java.sql.Date) result[0]).toLocalDate(),
                        result -> ((Long) result[1]).intValue(),
                        (integer, integer2) -> {
                            throw new RuntimeException("Duplicates are not expected");
                        }, LinkedHashMap::new));
        return new DailyArticleStatistics(map);
    }
}
