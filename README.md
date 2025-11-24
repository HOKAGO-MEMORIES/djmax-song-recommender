# DJMAX SONG RECOMMENDER (Backend)

[V-ARCHIVE](https://v-archive.net/)에 저장되어 있는 [OPEN API](https://v-archive.net/info/api)를 활용해 사용자 정보를 가져와 실력 지표인 **DJ
CLASS**를 정밀하게 계산하고 점수 향상을 위한 **맞춤형 곡을 추천**해주는 REST
API 서버입니다.

> [📝 개발 회고 및 상세 과정 보러가기 (Notion)](https://exultant-piper-0b9.notion.site/2a3f6cf893c5805eac55e2cc6335c5a3?source=copy_link)

## ✨ 프로젝트 소개

이 프로젝트는 "**프레임워크(Spring) 없는 순수 Java 백엔드 개발**"을 목표로 진행되었습니다.
리듬 게임 유저들의 성장을 돕기 위해, 현재 실력을 분석하고 가장 효율적인 연습 곡을 제안합니다.

- **V-ARCHIVE 연동:** OpenAPI를 활용하여 유저의 티어 및 전체 플레이 기록 조회
- **DJ CLASS 시뮬레이션:** 공식 공식을 역산하여 정확도별 획득 점수 예측
- **맞춤 추천 알고리즘:**
    1. **기록 갱신 추천:** 기존 플레이 곡 중 점수 상승폭이 큰 곡 추천
    2. **미기록 노래 추천:** 유저의 평균 실력(Average Floor)을 분석하여 Top 100 진입 가능한 미플레이 곡 추천

## 🛠️ 기술 스택 (Tech Stack)

- **Language:** Java 21
- **Web Server:** Javalin (Lightweight Web Framework)
- **Database:** PostgreSQL (Production), H2 (Test/Local)
- **ORM:** Hibernate (Pure JPA)
- **Build:** Gradle (ShadowJar for Fat Jar)
- **Deploy:** Docker, Render

## 💡 핵심 기술적 도전

- **No Spring:** 의존성 주입(DI) 컨테이너와 트랜잭션 관리를 직접 구현하여 프레임워크의 동작 원리 체득
- **TDD & OOP:** 복잡한 계산 로직을 단위 테스트로 검증하고, 계층형 아키텍처(Layered Architecture)로 역할 분리
- **Optimization:** 13개의 API 호출을 병렬 스트림(Parallel Stream)으로 최적화

## 🚀 실행 방법

```bash
# 1. 프로젝트 클론
git clone https://github.com/HOKAGO-MEMORIES/djmax-song-recommender.git

# 2. 빌드 및 실행
./gradlew run