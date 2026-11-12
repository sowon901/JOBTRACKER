package com.jobtracker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

/** 채용 공고의 회사명, 제목, 출처, 마감일 등 저장 필드를 나타낸다. */
@Entity
@Table(name = "job_postings")
public class JobPosting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name", length = 100, nullable = false)
    private String companyName;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(name = "job_category", length = 100)
    private String jobCategory;

    @Column(nullable = false, length = 50)
    private String source;

    @Column(name = "source_url", length = 2048, nullable = false)
    private String sourceUrl;

    @Column
    private LocalDateTime deadline;

    @Column(name = "collected_at", nullable = false)
    private LocalDateTime collectedAt;

    @Column(name = "recommendation_reason", columnDefinition = "TEXT")
    private String recommendationReason;

    protected JobPosting() {
    }

    public Long getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getTitle() {
        return title;
    }

    public String getJobCategory() {
        return jobCategory;
    }

    public String getSource() {
        return source;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public LocalDateTime getCollectedAt() {
        return collectedAt;
    }

    public String getRecommendationReason() {
        return recommendationReason;
    }
}
