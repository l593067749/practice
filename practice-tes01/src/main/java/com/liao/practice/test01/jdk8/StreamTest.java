package com.liao.practice.test01.jdk8;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by liaowenqiang on 2017/9/26.
 */
public class StreamTest {

    public void test01(){
        Stream<String> stringStream= Stream.of("I","love","you","too","are","you","ok");
       List<String> list= stringStream.filter(str->str.length()>1)
                .map(str->str.toUpperCase())
                .sorted((str1,str2)->str1.length()-str2.length())
                .collect(Collectors.toList())
                ;
       list.stream().forEach(str->System.out.println(str));
       System.out.println("-------------------------------------------");
       list.stream().distinct().forEach(str->System.out.println(str));
        System.out.println("-------------------------------------------");
        String joinStr=list.stream().collect(Collectors.joining("||","[","]"));
        System.out.println(joinStr);
        Optional<String> longest=list.stream().reduce((s1, s2)->s1.length()>=s2.length()?s1:s2);
        System.out.println("-------------------------------------------");
        System.out.println(longest.get()+"--"+list.stream().max((s1,s2)->s1.length()-s2.length()).get());
        System.out.println("------------------------------------------");
        System.out.println(list.stream().mapToInt(str->str.length()).sum());
        System.out.println("------------------------------------------");
        Map<String, Integer> map=list.stream().distinct().collect(Collectors.toMap(x->x, v->v.length()));
        System.out.println(map.toString());
    }
    public void test02(){
        for(int i=0;i<100;i++){
            System.out.println("----------"+i);
        }
    }
    public static void main(String[] args) {
        StreamTest test=new StreamTest();
        test.test01();
    }
}
