package com.example.demo.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.UUID;

public record Article(
        UUID id,
        @NotBlank String author,
        @NotBlank @Size(max = 100) String title,
        @NotBlank String content,
        @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date publishingDate
) {
}
