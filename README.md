🗓️ Todo Planner (일정 관리 앱)
Spring Boot와 JPA를 이용해 만든 개인 일정 관리 백엔드 서버입니다. MySQL 데이터베이스를 연동하여 일정을 생성, 조회, 수정, 삭제(CRUD)할 수 있습니다.

1. 🛠️ 기술 스택
Framework: Spring Boot 3.x

Language: Java 17

DB: MySQL

ORM: Spring Data JPA

Tool: Postman (API Test), IntelliJ IDEA


기능,Method,URL,Request Body / Param,설명
일정 생성,POST,/api/schedules,"JSON (title, content, author, password)",새로운 일정을 등록합니다.
일정 단건 조회,GET,/api/schedules/{id},Path Variable (id),선택한 일정의 상세 정보를 조회합니다.
일정 목록 조회,GET,/api/schedules,-,전체 일정을 수정일 내림차순으로 조회합니다.
일정 수정,PUT,/api/schedules/{id},"JSON (title, content, author, password)",비밀번호 일치 시 일정을 수정합니다.
일정 삭제,DELETE,/api/schedules/{id},Query Param (password),비밀번호 일치 시 일정을 삭제합니다.
