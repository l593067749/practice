package com.liao.practice.test01.algorithm;

import java.util.Arrays;

/**
 * Created by liaowenqiang on 2018/1/5.
 */
public class InsertSort {
    public static void sort(int[] arr,boolean asc){
        int orderedLastIndex=0;//有序结束位置
        for(int i=orderedLastIndex+1;i<arr.length;i++){//无序区迭代
            int temp=arr[i];
            int inserIndex=i;//要插入的位置
            for(int j=orderedLastIndex;j>=0;j--){
                if(asc){//升序
                    if(arr[j]>temp){
                        arr[j+1]=arr[j];//往右边复制一个新的
                        inserIndex=j;
                    }else {
                        break;
                    }
                }else{//降序
                    if(arr[j]<temp){
                        arr[j+1]=arr[j];//往右边复制一个新的
                        inserIndex=j;
                    }else {
                        break;
                    }
                }
            }
            arr[inserIndex]=temp;
            orderedLastIndex++;
        }
    }
    public static void main(String[] args) {
        int[] arr=new int[]{1,5,6,2,8,9,4,3,3};
        System.out.println("排序数组："+ Arrays.toString(arr));
        sort(arr,true);
        System.out.println("升序排列："+Arrays.toString(arr));
        sort(arr,false);
        System.out.println("降序排列："+Arrays.toString(arr));
    }
}
