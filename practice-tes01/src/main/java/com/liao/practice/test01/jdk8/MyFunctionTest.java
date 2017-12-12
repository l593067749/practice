package com.liao.practice.test01.jdk8;

/**
 * Created by liaowenqiang on 2017/9/26.
 */
public class MyFunctionTest {
    public void testMyFunction(){
        MyFunction<String> myFunction=(str)-> System.out.println(str);
        myFunction.test("hello");
    }
    public void testMyFunction2(){
        MyFunction2<String,String> myFunction=(str)->  "----"+str+"----";
        System.out.println(myFunction.test("hello"));
    }
    public static void main(String[] args) {
        MyFunctionTest test=new MyFunctionTest();
        test.testMyFunction();
        test.testMyFunction2();

    }
}
