package com.example.demo.domain;

import java.time.LocalDate;
import java.util.Map;

public record DailyArticleStatistics(Map<LocalDate, Integer> dailyStatisticsMap) {
}
