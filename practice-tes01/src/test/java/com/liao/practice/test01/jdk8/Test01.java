package com.liao.practice.test01.jdk8;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * Created by liaowenqiang on 2018/1/3.
 */
public class Test01 {
    public static void main(String[] args) {
        /*Integer team1_win_count=null;
        Integer team2_win_count=null;
        String oldWinCount= Optional.ofNullable(team1_win_count).map(val->val.intValue()).orElse(0)+"_"+
                Optional.ofNullable(team2_win_count).map(val->val.intValue()).orElse(0);
        System.out.println(oldWinCount);
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        System.out.println(year);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate=simpleDateFormat.format(new Date(2017,11,31,12,23,55));
                System.out.println(strDate);


        */
        /**
         *  value << num
            num 指定要移位值value 移动的位数。
            左移的规则只记住一点：丢弃最高位，0补最低位
            ------------------------------------------------------------
             右移运算符<<使指定值的所有位都右移规定的次数。
             1）它的通用格式如下所示：
             value >> num
             num 指定要移位值value 移动的位数。
             右移的规则只记住一点：符号位不变，左边补上符号位
         */
        int oldCapacity = 13;//1101=2^3+2^2+2^0=
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        System.out.println(newCapacity);
        System.out.println(oldCapacity >> 1);
        System.out.println((oldCapacity >> 1) <<1);


        Ten2One(13);
        Ten2One(255);
        Ten2One(789);
        Ten2One(7498746464649879879L);
        Ten2One(74987464646498L);

        int size=10;
        int size2=10;
        int a=++size;
        int b=size2++;
        System.out.println(a);
        System.out.println(b);

        //110100000010000111001100010110111110110001011000010000000000000
        //10001000011001101100001001100010101111101100010
    }
    public static void Ten2One(long number){
       long a=number,b=0;
        List<Long> list=new ArrayList<>();
       while (a!=0){
           b=a%2;
           a=a/2;
           list.add(b);
       }
        Collections.reverse(list);
       list.stream().forEach(System.out::print);
       System.out.println();
       System.out.println(Long.toBinaryString(number));
       ;
    }
}
