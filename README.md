# JOBTRACKER

**AI 기반 채용 공고 자동 탐색 및 추천 서비스**입니다. 사용자가 설정한 기업·지역·직무 조건에 맞는 공고를 모으고, 직무 적합성을 분석해 추천 이유와 함께 제공하는 것을 목표로 합니다.

> 현재는 백엔드 기반과 채용 공고 수집의 공통 구조를 구현하는 단계입니다. 실제 채용 사이트 수집, AI 추천, 공고 저장 API 및 사용자 화면은 아직 구현되지 않았습니다.

## 프로젝트 소개

JOBTRACKER는 여러 채용 사이트의 공고를 한곳에서 탐색하고, 최근 등록되었으며 현재 지원 가능한 공고를 우선 확인할 수 있도록 설계한 개인 프로젝트입니다. AI는 사용자의 개발 경험과 공고의 직무 요건을 비교하고, 추천 근거를 설명하는 역할을 맡을 예정입니다.

서비스는 관심 공고 저장을 지원하는 범위까지 다룹니다. **지원 현황 관리는 서비스 범위에서 제외**합니다.

## 핵심 기능

| 기능 | 계획 | 현재 상태 |
|---|---|---|
| 매일 오전 9시 자동 수집 (Asia/Seoul) | Spring 스케줄러가 수집 서비스 실행 | 스케줄러 및 수집 서비스 연결 구현. 실제 사이트 수집기는 없음 |
| 여러 사이트의 공고 수집 | 사이트별 수집기를 공통 인터페이스로 확장 | `JobCollector` 인터페이스와 `CollectedJobDto` 구현 |
| 공고 선별 및 우선순위 지정 | 최근 7일 이내 게시 및 현재 지원 가능 공고 우선 | 미구현 |
| AI 직무 적합성 분석·추천 이유 생성 | 사용자 경험과 공고 요건 분석 | 미구현 |
| 관심 공고 저장 | 관심 공고 등록·조회·해제 | Entity와 Repository만 구현. 서비스/API는 미구현 |
| 지원 현황 관리 | 서비스 범위에서 제외 | 제공하지 않을 예정 |

## 채용 공고 탐색 조건

| 구분 | 탐색 기준 |
|---|---|
| 기업 | 대기업 및 계열사, 중견기업, 연매출 600억 원 이상 중소기업, 공기업·공공기관·협회 |
| 지역 | 일반 사기업은 서울특별시 또는 경기도 성남시. 공기업·공공기관·협회는 지역 제한 없음 |
| 직무 | Java/Spring Boot 백엔드 개발, 정보기술 응용개발, 관련 IT 개발 및 운영 직무 |
| 게시일 | 최근 7일 이내 게시된 공고 우선 |
| 지원 가능 여부 | 현재 지원 가능한 공고 우선 |

기업 유형, 매출, 지역, 게시일 및 접수 상태는 실제 수집 기능 구현 시 확인 가능한 출처를 바탕으로 판정할 예정입니다. 확인되지 않은 정보는 추정하지 않고 검증이 필요한 값으로 다루는 것을 원칙으로 합니다.

## 기술 스택 및 선정 근거

| 영역 | 현재 프로젝트에서 확인한 기술 | 선정 근거 및 검토한 대안 |
|---|---|---|
| 언어·런타임 | Java 17 | 프로젝트의 Gradle toolchain 기준입니다. 기존 Java/Spring 생태계와 정적 타입을 활용합니다. Python/FastAPI도 대안으로 검토할 수 있습니다. |
| 애플리케이션 | Spring Boot 4.1.2, Spring WebMVC | 백엔드 API와 애플리케이션 구성을 위한 기반입니다. 별도 경량 프레임워크를 직접 구성하는 방식과 비교해 Spring 생태계를 활용합니다. |
| 데이터 접근 | Spring Data JPA | 현재 Entity와 Repository가 JPA 기반입니다. SQL을 직접 작성하는 JDBC 또는 MyBatis를 대안으로 검토할 수 있습니다. |
| 데이터베이스 | MySQL, MySQL Connector/J | 현재 설정과 의존성에 반영되어 있고 공고·관심 데이터를 관계형으로 관리하기 적합합니다. PostgreSQL도 대안입니다. |
| 스케줄링 | Spring Scheduling | 현재 `@EnableScheduling`과 `@Scheduled`로 매일 실행을 연결했습니다. 더 복잡한 재시도·분산 작업이 필요하면 Quartz나 외부 스케줄러를 검토할 수 있습니다. |
| 빌드 | Gradle Wrapper, Groovy DSL | `build.gradle` 및 Wrapper를 사용합니다. Maven은 대안입니다. |
| 유효성 검사 | Spring Boot Validation 의존성 | 현재 의존성에 포함되어 있습니다. 요청 DTO 검증에 사용할 예정입니다. |
| AI | 미선정·미구현 | 로컬 Ollama와 Spring AI 조합을 검토 대상으로 기록했습니다. 직접 모델 API 연동도 대안이며, 품질·비용·운영 환경을 비교한 후 결정합니다. |
| 화면 | 미선정·미구현 | Vue 3를 검토 대상으로 두고 있습니다. 화면 구현 시 요구사항에 따라 다른 프레임워크와 비교합니다. |

Gradle에는 Mail 및 Lombok 관련 의존성도 선언되어 있으나, 현재 소스에서 해당 기능이나 Lombok 어노테이션은 사용하지 않습니다. 외부 크롤링 라이브러리는 아직 추가하지 않았습니다.

## 시스템 아키텍처

### 현재 구현

```text
Spring Boot 애플리케이션
  └── 매일 09:00 (Asia/Seoul)
       └── JobCollectionScheduler
            └── JobCollectionService
                 └── 등록된 JobCollector 순차 실행
                      └── List<CollectedJobDto> 수신 및 결과 로깅
```

현재 등록된 사이트별 `JobCollector` 구현체가 없으므로 스케줄러가 실행되어도 실제 채용 공고를 수집하거나 DB에 저장하지 않습니다. 수집 서비스는 수집기별 시작·성공 건수·실패를 로그로 기록하고, 한 수집기에서 예외가 발생해도 다음 수집기를 실행하도록 구성되어 있습니다.

### 목표 구조

```text
채용 사이트별 수집기
        ↓
JobCollector → CollectedJobDto
        ↓
정규화·중복 제거·기업/지역/게시일/접수 상태 검증
        ↓
AI 직무 적합성 분석 및 추천 이유 생성
        ↓
공고 저장 (MySQL)
        ↓
공고 조회 및 관심 공고 API
        ↓
사용자 화면
```

## 프로젝트 디렉터리 구조

```text
src/
├── main/
│   ├── java/com/jobtracker/
│   │   ├── JobtrackerApplication.java
│   │   ├── collector/
│   │   │   ├── JobCollector.java
│   │   │   └── dto/CollectedJobDto.java
│   │   ├── entity/
│   │   │   ├── Favorite.java
│   │   │   └── JobPosting.java
│   │   ├── repository/
│   │   │   ├── FavoriteRepository.java
│   │   │   └── JobPostingRepository.java
│   │   ├── scheduler/JobCollectionScheduler.java
│   │   └── service/JobCollectionService.java
│   └── resources/application.properties
└── test/java/com/jobtracker/
    └── JobtrackerApplicationTests.java
```

사이트별 Collector 구현체, 추천 서비스, Controller 및 프런트엔드 디렉터리는 아직 없습니다.

## 데이터베이스 구조

현재 JPA Entity 기준으로 `job_postings`와 `favorites` 테이블을 모델링합니다. 실제 스키마 생성·변경은 `application.properties`의 JPA 설정 및 애플리케이션 실행 환경에 따릅니다.

| 테이블 | 현재 모델 필드 | 관계 및 비고 |
|---|---|---|
| `job_postings` | `id`, `company_name`, `title`, `job_category`, `source`, `source_url`, `deadline`, `collected_at`, `recommendation_reason` | 채용 공고. 추천 이유 필드는 Entity에 있으나 AI 생성 로직은 미구현 |
| `favorites` | `id`, `job_posting_id`, `created_at` | `job_postings`를 참조하며 공고 하나의 중복 관심 등록을 막는 unique 제약이 정의되어 있습니다. |

현재 Repository는 기본 CRUD 인터페이스만 제공합니다. 공고 수집 결과 저장, 관심 공고 등록·삭제 API와 지원 현황 데이터 모델은 구현되어 있지 않습니다. 지원 현황 관리용 테이블은 만들 계획이 없습니다.

## 개발 진행 현황

| 항목 | 상태 |
|---|---|
| Spring Boot 프로젝트 및 Java 17 Gradle 설정 | 구현 |
| MySQL 연결 설정 및 JPA Entity/Repository | 구현 |
| `JobCollector`, `CollectedJobDto` 공통 수집 구조 | 구현 |
| 수집 서비스의 순차 실행 및 예외 격리 로깅 | 구현 |
| 매일 오전 9시 (Asia/Seoul) 스케줄러 | 구현 |
| 사이트별 실제 채용 공고 수집 | 미구현 |
| 수집 데이터 저장·중복 제거·기업/접수 조건 판정 | 미구현 |
| AI 직무 적합성 분석과 추천 이유 생성 | 미구현 |
| 관심 공고 관리 API 및 화면 | 미구현 (Entity/Repository 기반만 있음) |
| 지원 현황 관리 | 범위 제외 |

컴파일은 다음 명령으로 확인할 수 있습니다.

```powershell
.\gradlew.bat compileJava
```

수집 스케줄은 애플리케이션이 실행 중일 때 동작합니다.

## 향후 개발 계획

1. 대상 사이트별 공식 API 및 이용약관·접근 정책을 확인하고 첫 수집기를 구현합니다.
2. 수집 공고의 정규화, 중복 제거, 최근 7일 및 접수 가능 여부 판정을 구현합니다.
3. 기업 분류·지역·중소기업 매출 기준을 확인 가능한 정보와 함께 검증합니다.
4. AI 모델과 연동해 직무 적합성을 분석하고 추천 이유를 생성합니다.
5. 수집 공고 저장과 조회 API, 관심 공고 등록·조회·해제 기능을 구현합니다.
6. 사용자 화면을 구현하고 전체 수집·추천 흐름을 검증합니다.

> 채용 사이트 크롤링은 사이트별 정책을 확인한 후 허용된 방식으로만 구현합니다. 현재 실제 사이트 수집과 AI 추천은 제공되지 않습니다.
