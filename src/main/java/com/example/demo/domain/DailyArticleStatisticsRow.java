package com.example.demo.domain;

import java.time.LocalDate;

public record DailyArticleStatisticsRow(LocalDate localDate, int count) {
}
