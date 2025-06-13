package com.example.demo.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.util.Date;

public record CreateArticleRequestBody(
    @NotBlank @Size(max = 100) String title,
    @NotBlank String author,
    @NotBlank String content,
    @NotNull @DateTimeFormat(iso = ISO.DATE_TIME) Date publishingDate
) {}
