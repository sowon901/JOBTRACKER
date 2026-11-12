package com.jobtracker.collector.dto;

import java.time.LocalDateTime;

/** 수집기와 JPA 엔티티 사이의 의존성을 분리해 전달하는 채용 공고 데이터다. */
public record CollectedJobDto(
        String companyName,
        String title,
        String jobCategory,
        String source,
        String sourceUrl,
        LocalDateTime deadline) {
}
