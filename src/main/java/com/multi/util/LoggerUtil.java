package com.multi.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerUtil {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void info(String message){
        System.out.println("[INFO] " + timestamp() +"-" + message);
    }

    public static void warn(String message){
        System.out.println("[WARN] " + timestamp() +"-" + message);
    }

    public static void error(String message, Throwable t){
        System.out.println("[ERROR] " + timestamp() +"-" + message);
        if(t != null){
            t.printStackTrace(System.err);
        }
    }

    private static String timestamp(){
        return sdf.format(new Date());
    }
}
