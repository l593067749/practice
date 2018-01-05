package com.liao.practice.test01.algorithm;

import java.util.Arrays;

public class WhileMergeSort {
    public static void main(String args[]){
        int[] array = new int[]{1,5,6,8,9,4,3};
        System.out.println("OriginalArray:" + Arrays.toString(array));
        mergeSort(array);
        System.out.println("SortedArray:" + Arrays.toString(array));
    }
 
    public static void mergeSort(int[] array){
        int len = 1;
        while(len < array.length){
            for(int i = 0; i < array.length; i += 2*len){
                merge(array, i, len);
            }
            len *= 2;
        }
    }
 
    public static void merge(int[] array, int startIndex, int endIndex){
        int leftStartIndex = startIndex;
        int leftHalfLength = startIndex + endIndex;//归并的前半部分数组
        int rightStartIndex = startIndex + endIndex;
        int rightHalfLength = rightStartIndex +endIndex;//归并的后半部分数组
        int[] temp = new int[2*endIndex];
        int count = 0;
        while(startIndex < leftHalfLength && rightStartIndex < rightHalfLength && rightStartIndex < array.length){
            if(array[startIndex] <= array[rightStartIndex]){
                temp[count++] = array[startIndex++];
            }
            else{
                temp[count++] = array[rightStartIndex++];
            }
        }
        while(startIndex < leftHalfLength && startIndex < array.length){//注意：这里i也有可能超过数组长度
            temp[count++] = array[startIndex++];
        }
        while(rightStartIndex < rightHalfLength && rightStartIndex < array.length){
            temp[count++] = array[rightStartIndex++];
        }
        count = 0;
        while(leftStartIndex < rightStartIndex && leftStartIndex < array.length){
            array[leftStartIndex++] = temp[count++];
        }
    }
}