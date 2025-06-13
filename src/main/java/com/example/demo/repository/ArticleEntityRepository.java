package com.example.demo.repository;

import com.example.demo.domain.DailyArticleStatistics;
import com.example.demo.domain.DailyArticleStatisticsRow;
import com.example.demo.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ArticleEntityRepository extends JpaRepository<ArticleEntity, UUID>, JpaSpecificationExecutor<ArticleEntity> {

    @Query(value = "SELECT DATE(publishing_date) as day, COUNT(*) as count " +
            "FROM demo.article " +
            "WHERE publishing_date >= CURRENT_DATE - INTERVAL '7 days' " +
            "GROUP BY DATE(publishing_date) " +
            "ORDER BY day", nativeQuery = true)
    List<Object[]> findDailyArticleCountsLast7Days();

}
