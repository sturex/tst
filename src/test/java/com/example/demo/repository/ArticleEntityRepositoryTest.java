package com.example.demo.repository;

import com.example.demo.entity.ArticleEntity;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class ArticleEntityRepositoryTest {

    @SuppressWarnings("resource")
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.datasource.driver-class-name", postgres::getDriverClassName);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
    }

    @Autowired
    private ArticleEntityRepository repository;

    @Test
    @DisplayName("Data is inserted and extracted properly")
    void shouldReturnDailyCounts() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);

        repository.save(createArticle("Article 1", today));
        repository.save(createArticle("Article 2", today));
        repository.save(createArticle("Article 3", yesterday));

        List<Object[]> results = repository.findDailyArticleCountsLast7Days();

        assertEquals(2, results.size());
        assertEquals(yesterday, ((Date) results.get(0)[0]).toLocalDate());
    }

    private ArticleEntity createArticle(String title, LocalDate date) {
        return ArticleEntity.builder()
                .title(title)
                .author("Test Author")
                .content("Content")
                .publishingDate(Date.valueOf(date))
                .build();
    }
}
