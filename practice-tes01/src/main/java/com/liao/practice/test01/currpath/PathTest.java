package com.liao.practice.test01.currpath;

import java.io.File;

/**
 * Created by liaowenqiang on 2018/1/2.
 */
public class PathTest {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        File directory = new File("");//设定为当前文件夹
        try{
            System.out.println(directory.getCanonicalPath());//获取标准的路径
            System.out.println(directory.getAbsolutePath());//获取绝对路径
        }catch(Exception e){

        }
         directory = new File("abc");//设定为当前文件夹
        try{
            System.out.println(directory.getCanonicalPath());//获取标准的路径
            System.out.println(directory.getAbsolutePath());//获取绝对路径
        }catch(Exception e){

        }
    }
}
