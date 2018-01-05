package com.liao.practice.test01.algorithm;

import java.util.Arrays;

public class PigeonholeSort {
    /**
     * 第一种情况：所有元素都>=0
     */
    public static void sort(int[] arr) {

        //第一步，确定数组中最大的元素
        int max = arr[0];
        for (int i : arr) {
            if (i > max) {
                max = i;
            }
        }

        //第二步：创建辅助数组，大小为max+1，注意这里是Integer，所以默认每个元素的值都是null
        Integer[] bucketArray = new Integer[max + 1];

        //第三步：将数组中的分配到bucket数组中
        for (int i = 0; i < arr.length; i++) {
            bucketArray[arr[i]] = arr[i];
        }

        //第四步：迭代辅助数组，将不是null的元素依次拷贝到待排序数组中
        int index = -1;
        for (int i = 0; i < bucketArray.length; i++) {
            if (bucketArray[i] != null) {
                arr[++index] = bucketArray[i];
            }
        }
    }

    /**
     *  数组中没有重复的元素，数据元素有正有负
     * @param array
     */
    public static void sort2(int[] array) {

        //第一步，确定数组中最大的元素和最小元素
        int maxNum = array[0];
        int minNum = array[0];
        for (int i : array) {
            if (i > maxNum) {
                maxNum = i;
            }
            if (i < minNum) {
                minNum = i;
            }
        }

        //第二步：创建辅助数组，大小为max-min+1，注意这里是Integer，所以默认每个元素的值都是null
        int bucketArrayLength = maxNum - minNum + 1;
        Integer[] bucketArray = new Integer[bucketArrayLength];

        //第三步：将数组中的分配到bucket数组中
        for (int i = 0; i < array.length; i++) {
            bucketArray[array[i] - minNum] = array[i];
        }

        //第四步：迭代辅助数组，将不是null的元素依次拷贝到待排序数组中
        int index = -1;
        for (int i = 0; i < bucketArray.length; i++) {
            if (bucketArray[i] != null) {
                array[++index] = bucketArray[i];
            }
        }
    }



    public static void main(String[] args) {
        int[] arr = {11, 13, 56, 23, 63, 98, 87};
        System.out.println("排序前：" + Arrays.toString(arr));
        sort(arr);
        System.out.println("排序后：" + Arrays.toString(arr));

        int[] arr2 = {11, 13, 56, -23, 63, 98, 87};//注意这个数组中存在一个负数
        System.out.println("排序前：" + Arrays.toString(arr2));
        sort2(arr2);
        System.out.println("排序后：" + Arrays.toString(arr2));



    }
}