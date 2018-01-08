package com.liao.practice.test01.algorithm;

import java.util.Arrays;

/**
 * 假设有一个n个元素的数组，那么需要进行n-1轮排序，每一轮排序都确定一个数字的最终位置。
 * 对于一组需要排序的数字，依次将个位置上的数字与逐一与其之后的数字进行比较，如果他们的顺序错误就把他们交换过来。
 * 这个算法的名字由来是因为越大的元素会经由交换慢慢“浮”到数列的顶端，故名
 */
public class BubbleSort {
    /**
     * @param arr 需要排序的数组
     * @param asc 是否升序
     * @return 排序后的数组
     */
    public static void sort(int[] arr,boolean asc){
        for (int i = 0; i < arr.length; i++) {
            for (int j=i+1;j<arr.length;j++){
                if(asc){
                    if(arr[i]>arr[j]){//升序
                        swap(arr, i, j);
                    }
                }else{
                    if(arr[i]<arr[j]){//降序
                        swap(arr, i, j);
                    }
                }
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
        int[] arr=new int[]{1,5,6,8,9,4,3};
        System.out.println("排序数组："+ Arrays.toString(arr));
        sort(arr,true);
        System.out.println("升序排列："+Arrays.toString(arr));
        sort(arr,false);
        System.out.println("降序排列："+Arrays.toString(arr));
    }
}