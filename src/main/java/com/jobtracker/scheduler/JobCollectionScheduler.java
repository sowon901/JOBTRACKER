package com.jobtracker.scheduler;

import com.jobtracker.service.JobCollectionService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/** 정해진 시각에 채용 공고 수집 서비스를 호출한다. */
@Component
public class JobCollectionScheduler {

    private final JobCollectionService jobCollectionService;

    /** 수집 서비스를 주입받는다.
     * @param jobCollectionService 채용 공고 수집 서비스
     */
    public JobCollectionScheduler(JobCollectionService jobCollectionService) {
        this.jobCollectionService = jobCollectionService;
    }

    /** 매일 한국 시간 오전 9시에 공고 수집을 실행한다. */
    @Scheduled(cron = "0 0 9 * * *", zone = "Asia/Seoul")
    public void collectJobs() {
        jobCollectionService.collectJobs();
    }
}
