package com.multi.dto;

import java.sql.Timestamp;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private long id;
    private String name;
    private String email;
    private String dept;
    private String studentNo;
    private Timestamp createdAt;
}
