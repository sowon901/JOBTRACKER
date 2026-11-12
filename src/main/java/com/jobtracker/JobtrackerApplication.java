package com.jobtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/** Spring Boot 애플리케이션의 시작점이며 스케줄 작업을 활성화한다. */
@SpringBootApplication
@EnableScheduling
public class JobtrackerApplication {

	/** 애플리케이션을 시작한다.
	 * @param args 애플리케이션 실행 인자
	 */
	public static void main(String[] args) {
		SpringApplication.run(JobtrackerApplication.class, args);
	}

}
