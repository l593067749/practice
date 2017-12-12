package com.liao.practice.test01.test01;


/**
 * Created by liaowenqiang on 2016/10/27.
 */
public class Cat extends Animal {
    public Cat(String name){
        super(name);
        System.out.println("------------------------"+"cat");
    }
    public static void main(String[] args) {
        Cat cat=new Cat("zhangsan");
    }
}
