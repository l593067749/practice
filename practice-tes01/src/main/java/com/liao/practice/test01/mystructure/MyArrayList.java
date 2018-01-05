package com.liao.practice.test01.mystructure;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by liaowenqiang on 2018/1/4.
 */
public class MyArrayList<T>  {
    private static final int DEFAULT_SIZE=16;
    private int element_size=0;
    private int array_size=0;
    private Object[] array=null;
    public MyArrayList(){
        this(DEFAULT_SIZE);
    }
    public MyArrayList(int arraySize)  {
        if(arraySize<=0){
            throw new IllegalArgumentException("arraySize must >0");
        }
        this.array_size=arraySize;
        array=new Object[arraySize];
    }
    public void add(T element){
        if(element_size>=array_size){
          //不够扩充一半
            array_size+=array_size>>2;
            Object[] new_array=new Object[array_size];
            System.arraycopy(array,0,new_array,0,array.length);
            array=new_array;

        }
        array[element_size++]=element;
    }
    public Iterator<T> iterator() {
        return new MyIterable();
    }
    public T get(int index){
        if(index<0||index>element_size-1){
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return (T)array[index];
    }
    public T remove(int index){
        T t=get(index);
        System.arraycopy(array,index+1,array,index,array_size-1-index);
        array[--element_size]=null;
        return t;
    }
    public int size(){
        return element_size;
    }
    public int arraySize(){
        return array_size;
    }
    public class MyIterable implements Iterator<T>{
       int cursor;
       int last;
        @Override
        public boolean hasNext() {
            return cursor!=element_size;
        }

        @Override
        public T next() {
            int i=cursor++;
            if(i>=array_size){
                throw new NoSuchElementException();
            }
            last=i;
            return (T)array[i];
        }
        public void remove(){
            if(last<0){
                throw new IllegalStateException();
            }
            MyArrayList.this.remove(last);
            cursor=last;
            last=-1;
        }
    }
}
