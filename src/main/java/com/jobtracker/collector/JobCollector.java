package com.jobtracker.collector;

import com.jobtracker.collector.dto.CollectedJobDto;
import java.util.List;

/** 채용 공고 수집기가 공통으로 제공하는 기능을 정의한다. */
public interface JobCollector {

    /** 수집 대상 출처의 이름을 반환한다. */
    String getSourceName();

    /** 수집한 공고를 DTO 목록으로 반환한다. */
    List<CollectedJobDto> collect();
}
