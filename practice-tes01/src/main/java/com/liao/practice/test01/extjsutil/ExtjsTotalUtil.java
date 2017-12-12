package com.liao.practice.test01.extjsutil;

/**
 * Created by liaowenqiang on 2017/8/25.
 */
public class ExtjsTotalUtil {

    public static void main(String[] args) {
        ExtjsModeUtil.go();
        System.out.println("create mode success!");
        ExtjsStoreUtil.go();
        System.out.println("create store success!");
        ViewController.go();
        System.out.println("create ViewController success!");
        ViewFormUtil.go();
        System.out.println("create ViewForm success!");
        ViewMainUtil.go();
        System.out.println("create ViewMain success!");
        ViewModelUtil.go();
        System.out.println("create ViewModel success!");
    }
}
