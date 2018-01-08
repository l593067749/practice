package com.liao.practice.test01.algorithm;

import java.util.Arrays;

/**
 * 选择排序（Selection sort）是一种简单直观的排序算法。它的工作原理是每一次从待排序的数据元素中选出最小（或最大）的一个元素，存放在序列的起始位置，
 * 直到全部待排序的数据元素排完。 选择排序是不稳定的排序方法（比如序列[5， 5， 3]第一次就将第一个[5]与[3]交换，导致第一个5挪动到第二个5后面）。
 * 选择排序只是比冒泡排序优化了一点点，比较的次数没有变，但是减少了交换的次数

 */
public class SelectSort {
    public static void sort(int[] arr,boolean asc){
        for (int i = 0; i < arr.length; i++) {
            int index=i;
            for (int j = i+1; j < arr.length; j++) {
                if(asc){//升序，选择无序区最小的元素
                    if(arr[index]>arr[j]){
                        index=j;
                    }
                }else{//降序，选择无序区最大的元素
                    if(arr[index]<arr[j]){
                        index=j;
                    }
                }
            }
 
           if(index!=i){
              swap(arr,index,i);
           }
        }
    }
    //交换数组中两个元素的位置
    private static void swap(int[] arr, int i, int j) {
        int temp= arr[i];
        arr[i] =arr[j];
        arr[j]=temp;
    }
    public static void main(String[] args) {
        int[] arr=new int[]{1,5,5,6,8,9,4,3,5};
        System.out.println("排序数组："+ Arrays.toString(arr));
        sort(arr,true);
        System.out.println("升序排列："+Arrays.toString(arr));
        sort(arr,false);
        System.out.println("降序排列："+Arrays.toString(arr));
    }
}