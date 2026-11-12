package com.jobtracker.repository;

import com.jobtracker.entity.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;

/** JobPosting 엔티티의 기본 CRUD 및 페이징 작업을 제공한다. */
public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {
}
