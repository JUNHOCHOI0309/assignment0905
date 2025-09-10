package com.multi.util;

public class ValidationUtil {
    public static void requireNonBlank(String v, String fieldName){
        if(v == null || v.trim().isEmpty()){
            throw new IllegalArgumentException(fieldName + " is required.");
        }
    }
    public static void requireLength(String v, int max, String fieldName){
        if(v != null && v.length() > max){
            throw new IllegalArgumentException(fieldName + " is too long.");
        }
    }
}
