# 🗓️ Todo Planner (일정 관리 앱)

Spring Boot와 JPA를 이용해 만든 개인 일정 관리 백엔드 서버입니다. MySQL 데이터베이스를 연동하여 일정을 생성, 조회, 수정, 삭제(CRUD)할 수 있습니다.

## 🛠️ 기술 스택
- Framework: Spring Boot 3.x
- Language: Java 17
- DB: MySQL
- ORM: Spring Data JPA
- Tool: Postman (API Test), IntelliJ IDEA

---

# 일정 관리 API (Final)

## 📝 API 명세서

| 기능 | Method | URL | Request Body / Param | 설명 |
|------|--------|-----|--------------------|------|
| 일정 생성 | POST | /api/schedules | Body: `title`, `content`, `author`, `password` | 새로운 일정을 DB에 저장 |
| 일정 단건 조회 | GET | /api/schedules/{id} | Path: `id` | 특정 ID 일정 상세 정보 조회 |
| 일정 목록 조회 | GET | /api/schedules | - | 전체 일정 수정일 기준 내림차순 조회 |
| 일정 수정 | PUT | /api/schedules/{id} | Path: `id` <br> Body: `title`, `content`, `author`, `password` | 비밀번호 확인 후 제목/내용/작성자 수정 |
| 일정 삭제 | DELETE | /api/schedules/{id} | Path: `id` <br> Query: `password` | 비밀번호 확인 후 일정 삭제 |

---

## 🗂️ 테이블 구조 (ERD)

| 컬럼명 | 타입 | 제약사항 | 설명 |
|--------|------|---------|------|
| id | Bigint | PK, Auto Increment | 일정 고유 식별자 |
| title | Varchar(200) | Not Null | 일정 제목 |
| content | Varchar(500) | Not Null | 일정 내용 |
| author | Varchar(50) | Not Null | 작성자 이름 |
| password | Varchar(100) | Not Null | 수정/삭제 인증 정보 |
| createdAt | Timestamp | Not Null, Default Current | 최초 생성 시간 |
| updatedAt | Timestamp | Not Null, Default Current | 마지막 수정 시간 |
