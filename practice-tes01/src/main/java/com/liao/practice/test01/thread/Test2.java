package com.liao.practice.test01.thread;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Created by liaowenqiang on 2017/10/10.
 */
public class Test2 {
    public static void main(String[] args) {



        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss",Locale.US);

        LocalDateTime minCreateDateTime = LocalDateTime.now();
        System.out.println(minCreateDateTime.format(format));
        minCreateDateTime=minCreateDateTime.minus(1, ChronoUnit.DAYS);
        System.out.println(minCreateDateTime.toString());


    }
    public void test01(Double[] doubles){
        Double doubles1=doubles[0];
        Double doubles2=doubles[1];
        double result=doubles1*-0.68+doubles2*1.82-0.4;
        System.out.println(result>=0?1:-1);
    }
}
