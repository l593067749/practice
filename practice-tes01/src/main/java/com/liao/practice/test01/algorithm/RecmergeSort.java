package com.liao.practice.test01.algorithm;

import java.util.Arrays;

/**
 * 递归的归并排序
 * 将一个数组拆分成两半，分别对每一半进行排序，然后使用合并(merge)操作，把两个有序的子数组合并成一个整体的有序数组。
 * 我们可以把一个数组刚开始先分成两，也就是2个1/2，之后再将每一半分成两半，也就是4个1/4，以此类推，反复的分隔数组，直到得到的子数组中只包含一个数据项，
 * 这就是基值条件，只有一个数据项的子数组肯定是有序的
 * Created by liaowenqiang on 2018/1/5.
 */
public class RecmergeSort {
    public static void main(String[] args) {
        int[] data={543,23,45,65,76,1,456,7,77,88,3,9};
        mergeSort(data);
        System.out.println(Arrays.toString(data));
    }
    public static void mergeSort(int[] arr){
        int[] temp =new int[arr.length];
        internalMergeSort(arr, temp, 0, arr.length-1);
    }
    private static void internalMergeSort(int[] a, int[] b, int left, int right){
        //当left==right的时，已经不需要再划分了
        if (left<right){
            int middle = (left+right)/2;
            internalMergeSort(a, b, left, middle);          //左子数组
            internalMergeSort(a, b, middle+1, right);       //右子数组
            mergeSortedArray(a, b, left, middle, right);    //合并两个子数组
        }
    }
    // 合并两个有序子序列 arr[left, ..., middle] 和 arr[middle+1, ..., right]。temp是辅助数组。
    private static void mergeSortedArray(int arr[], int temp[], int left, int middle, int right){
        int i=left;
        int j=middle+1;
        int k=0;
        /**
         * 如将A[m]与B[n]进行比较，将较小的数字放入C中下一个可以存放的位置， 假设A[m]<B[n]，那么就是将A[m]放入C中，那么下一次比较时，
         * 就需要使用A[m+1]与B[n]比较；反之则用A[m]与B[n+1]进行比较，不断的循环次过程。
         */

        while ( i<=middle && j<=right){
            if (arr[i] <=arr[j]){
                temp[k++] = arr[i++];
            }
            else{
                temp[k++] = arr[j++];
            }
        }
        while (i <=middle){//前半部分多
            temp[k++] = arr[i++];
        }
        while ( j<=right){//后半部分多
            temp[k++] = arr[j++];
        }
        //把数据复制回原数组
        for (i=0; i<k; ++i){//只针对涉及到的数据集
            arr[left+i] = temp[i];
        }
    }
}
