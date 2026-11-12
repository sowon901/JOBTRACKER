package com.jobtracker.service;

import com.jobtracker.collector.JobCollector;

import com.jobtracker.collector.dto.CollectedJobDto;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 등록된 수집기를 순회하며 채용 공고 수집을 수행한다. */
@Service
public class JobCollectionService {

    private static final Logger logger = LoggerFactory.getLogger(JobCollectionService.class);

    private final List<JobCollector> collectors;

    /** Spring에 등록된 수집기 목록을 전달받는다.
     * @param collectors 실행할 채용 공고 수집기 목록
     */
    public JobCollectionService(List<JobCollector> collectors) {
        this.collectors = List.copyOf(collectors);
    }

    /** 각 수집기를 실행하고, 한 수집기에서 예외가 발생해도 다음 수집기를 계속 실행한다. */
    public void collectJobs() {
        for (JobCollector collector : collectors) {
            String source = collector.getClass().getSimpleName();
            try {
                source = collector.getSourceName();
                logger.info("Starting job collection for source: {}", source);
                List<CollectedJobDto> jobs = collector.collect();
                int count = jobs == null ? 0 : jobs.size();
                // 현재 단계에서는 수집 결과를 로그로 기록하며 데이터베이스에 저장하지 않는다.
                logger.info("Job collection succeeded for source: {} ({} postings)", source, count);
            } catch (Exception exception) {
                logger.error("Job collection failed for source: {}", source, exception);
            }
        }
    }
}
