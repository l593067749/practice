package com.liao.practice.test01.algorithm;

import java.util.Arrays;

/**
 * 二分 归并排序
 * Created by liaowenqiang on 2018/1/5.
 */
public class TwoWayMerge {
    public static int[] merge(int[] oneArray,int[] twoArray,boolean asc){
        int[] resultArray=new int[oneArray.length+twoArray.length];
        int oneIndex=0,twoIndex=0,resultIndex=0;
        while (oneIndex<oneArray.length&&twoIndex<twoArray.length){
            if(oneArray[oneIndex]<twoArray[twoIndex]){
                resultArray[resultIndex++]=oneArray[oneIndex++];
            }else {
                resultArray[resultIndex++]=twoArray[twoIndex++];
            }
        }
        while (oneIndex<oneArray.length){
            resultArray[resultIndex++]=oneArray[oneIndex++];
        }
        while (twoIndex<twoArray.length){
            resultArray[resultIndex++]=twoArray[twoIndex++];
        }
        return resultArray;
    }

    public static void main(String[] args) {
        int[] one={1,2,3,5,6,9};
        int[] two={2,4,6,7,8};
        System.out.println(Arrays.toString(merge(one,two,true)));
    }
}
