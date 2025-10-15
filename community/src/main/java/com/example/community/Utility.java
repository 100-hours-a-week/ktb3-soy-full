package com.example.community;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utility {
    public String getCreatedAt(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
