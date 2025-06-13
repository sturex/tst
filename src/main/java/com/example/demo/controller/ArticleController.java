package com.example.demo.controller;

import com.example.demo.config.SecurityConfig;
import com.example.demo.domain.Article;
import com.example.demo.domain.CreateArticleRequestBody;
import com.example.demo.domain.DailyArticleStatistics;
import com.example.demo.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@PreAuthorize("isAuthenticated()")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Operation(summary = "Create article", description = "Create a new article with validation", tags = {"article"})
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Article created"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/article")
    @PreAuthorize("hasRole(" + SecurityConfig.ROLE_EDITOR + ")")
    public ResponseEntity<Article> createArticle(@Validated @RequestBody CreateArticleRequestBody createArticleRequestBody) {
        Article article = articleService.create(
            createArticleRequestBody.author(),
            createArticleRequestBody.title(),
            createArticleRequestBody.content(),
            createArticleRequestBody.publishingDate()
        );
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(article);
    }

    @Operation(summary = "List articles", description = "Get paginated list of articles", tags = {"article"})
    @GetMapping("/articles")
    @PreAuthorize("hasRole(" + SecurityConfig.ROLE_EDITOR + ")")
    public ResponseEntity<Page<Article>> listArticles(
            @Parameter(description = "Page number") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Items per page") @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(articleService.getArticles(page, size));
    }

    @Operation(summary = "Get article statistics", description = "Daily published articles count for last 7 days (Admin only)", tags = {"article"})
    @GetMapping("/articles/statistics")
    @PreAuthorize("hasRole(" + SecurityConfig.ROLE_ADMIN + ")")
    public ResponseEntity<DailyArticleStatistics> getArticleStatistics() {
        return ResponseEntity.ok(articleService.getDailyArticleStatistics());
    }
}
