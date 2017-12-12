package com.liao.practice.test01.extjsutil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liaowenqiang on 2017/8/24.
 */
public class BaseUtils {
    static final String filePath="D:\\work\\ideaWorkSpace\\practice\\practice-tes01\\src\\main\\java\\com\\liao\\practice\\test01\\extjsutil\\";
    public static String dbTableName="gdc_dict";
    public static String bigName="kog";
    public static String smallName="dict";
    public static String toUpperCaseFirstOne(String s){
        if(Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
    public static  <T> List<List<T>> splitList(List<T> list, int pageSize) {

        List<List<T>> listArray = new ArrayList<List<T>>(); // 创建list数组

        if(list.size()!=0){
            //零售客户数量
            int len = list.size();
            //一个线程的零售客户数量
            int limit = pageSize;

            //线程个数
            int pages =0;

            if(len%limit==0){
                pages = len/limit;
            }else{
                pages = len/limit+1;
            }

            for(int page = 0;page<pages;page++){
                int max = (page+1)*limit;
                int start = page*limit;
                if(max>len){
                    List<T> list1 = list.subList(start, len);
                    listArray.add(list1);
                }else{
                    List<T> list1 = list.subList(start, max);
                    listArray.add(list1);
                }
            }

        }

        return listArray;
    }
}
