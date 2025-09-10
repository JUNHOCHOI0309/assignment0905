package com.multi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

/*
 * Course DTO
 * - DB의 course 테이블과 매핑되는 데이터 전송 객체
 * - Controller ↔ Service ↔ DAO ↔ Mapper ↔ DB 사이에서 데이터 전달 역할
 */

public class Course {
    private long id; //PK (자동 증가)
    private String code; // 강좌코드
    private String title; // 강좌명
    private String professor; // 담당 교수
    private int credit; // 학점
    private String createdAt; // 생성일 (DB에서 자동 입렫)
}
