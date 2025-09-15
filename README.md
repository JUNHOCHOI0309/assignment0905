# 📘 University CRUD Project

## 📌 프로젝트 개요

이 프로젝트는 **학생(Student) - 강좌(Course) - 수강신청(Enrollment)** 관리 시스템을 구축하는 것을 목표로 합니다.
기본적인 CRUD(Create, Read, Update, Delete) 기능을 제공하며, JSP/Servlet 기반 MVC 구조로 설계되었습니다.

* **Frontend (View):** JSP + JSTL + Bootstrap
* **Backend (Controller/Service/DAO):** Java Servlet, MyBatis
* **Database:** MySQL
* **기타:** CSRF/XSS 보안 필터, Logger 유틸리티 적용

---

## 👥 팀원 및 역할 분담

* **최준호** → Student 영역 담당

    * 학생 등록/수정/삭제/조회
    * StudentController, StudentService, DAO, JSP(Form, Detail)

* **박태란** → Course 영역 담당

    * 강좌 등록/수정/삭제/조회
    * CourseController, CourseService, DAO, JSP(Write, Edit, Detail, List)

* **홍예린** → Enrollment 영역 담당

    * 수강 신청/취소/조회
    * EnrollmentController, EnrollmentService, DAO, JSP(List, Detail)

---

## 📂 주요 디렉토리 구조

```
multi/
 ├── controller/      # Servlet Controller (Student, Course, Enrollment)
 ├── service/         # 비즈니스 로직 (Service Interface & Impl)
 ├── dao/             # DB 접근 계층 (DAO, MyBatis Mapper)
 ├── dto/             # 데이터 전달 객체 (Student, Course, Enrollment 등)
 ├── filter/          # 보안 필터 (CsrfFilter, XssFilter, RequestWrapper)
 ├── exception/       # 예외 처리 클래스 (AppException, ValidationException 등)
 ├── util/            # LoggerUtil, ValidationUtil, SqlSessionFactoryProvider
 └── views/           # JSP View (common, student, course, enrollment)
```

---
## 🌐 엔드포인트 정리

### 📌 StudentController


| HTTP Method | Endpoint                  | 설명          |
| ----------- | ------------------------- | ----------- |
| GET         | `/student/list`           | 학생 목록 조회    |
| GET         | `/student/detail?id={id}` | 특정 학생 상세 조회 |
| GET         | `/student/form`           | 학생 등록/수정 폼  |
| POST        | `/student/add`            | 학생 등록       |
| POST        | `/student/update`         | 학생 수정       |
| POST        | `/student/delete`         | 학생 삭제       |


### 📌 CourseController

| HTTP Method | Endpoint                 | 설명          |
| ----------- | ------------------------ | ----------- |
| GET         | `/course/list`           | 강좌 목록 조회    |
| GET         | `/course/detail?id={id}` | 특정 강좌 상세 조회 |
| GET         | `/course/write`          | 강좌 등록 폼     |
| GET         | `/course/edit?id={id}`   | 강좌 수정 폼     |
| POST        | `/course/add`            | 강좌 등록       |
| POST        | `/course/update`         | 강좌 수정       |
| POST        | `/course/delete`         | 강좌 삭제       |

### 📌 EnrollmentController

| HTTP Method | Endpoint                     | 설명          |
| ----------- | ---------------------------- | ----------- |
| GET         | `/enrollment/list`           | 수강 신청 목록 조회 |
| GET         | `/enrollment/detail?id={id}` | 특정 신청 상세 조회 |
| POST        | `/enrollment/add`            | 수강 신청       |
| POST        | `/enrollment/cancel`         | 수강 신청 취소    |

### 📌 FrontController

* **/ → 메인 엔트리 포인트, 요청을 서브 컨트롤러에 분배**

---

## 🔒 보안 적용

* **CSRF 방어**

    * 모든 POST 요청에 대해 `CsrfFilter` 적용
    * JSP `<form>` 태그에 hidden input 삽입:

      ```jsp
      <input type="hidden" name="_csrf" value="${sessionScope.csrfToken}">
      ```
* **XSS 방어**

    * 모든 요청에 대해 `XssFilter` 적용
    * 사용자 입력 출력 시 HTML 이스케이프 처리

---

## 📝 로그 처리

    * `util/LoggerUtil` 을 통해 공통 로그 처리
    * `LoggerUtil.info()` → 주요 이벤트 기록
    * `LoggerUtil.warn()` → 유효성/중복 처리 경고
    * `LoggerUtil.error()` → 예외 발생 로그 기록

Controller, Service, Exception 처리 구간에 적용됨.

---

## 📆 개발 일정

* **(9/10)수요일까지:** 각자 담당 영역 기능 개발
* **(9/12)금요일까지:** 통합 및 최종 완성, 테스트 진행
