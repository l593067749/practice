package com.liao.practice.test01.mystructure;

import java.util.Iterator;
import java.util.Optional;

import static java.util.Optional.ofNullable;

/**
 * 数据结构
 * Created by liaowenqiang on 2018/1/4.
 */
public class Test {
    public static void main(String[] args) {
        Test test=new Test();
        test.testMyArrayList();
        //test.testMyLinkList();
    }

    private void testMyLinkList(){
        MyLinkList<Integer> list=new MyLinkList<>();
        list.add(null);
        for(int i=0;i<20;i++){
            list.add(i);
        }

        list.display();
        list.removeFirst();
        list.display();
        list.removeLaster();
        list.display();

        Iterator<Integer> iterable= list.iterable();
        while (iterable.hasNext()){
            Integer currNum=iterable.next();
            if(currNum.intValue()==9){
                iterable.remove();
            }
            if(currNum.intValue()==13){
                iterable.remove();
            }
        }
        list.display();
    }

    private void testMyArrayList() {
        MyArrayList list=new MyArrayList();
        list.add(null);
        list.add(16);
        for(int i=0;i<20;i++){
            list.add(i);
        }

        System.out.println("size:" + list.size() + ",array_size:" + list.arraySize());
        for(int i=0;i<list.size();i++){
            System.out.print(list.get(i)+"\t");
        }
        System.out.println("\n"+list.remove(14));
        for(int i=0;i<list.size();i++){
            System.out.print(list.get(i)+"\t");
        }
        System.out.println();
        Iterator<Integer> iterable= list.iterator();
        while (iterable.hasNext()){
            Integer currNum= Optional.ofNullable(iterable.next()).orElse(0) ;
            if(currNum.intValue()==16){
                iterable.remove();
            }
            if(currNum.intValue()==19){
                iterable.remove();
            }
            System.out.print(currNum+"\t");
        }
        System.out.println();
        for(int i=0;i<list.size();i++){
            System.out.print(list.get(i)+"\t");
        }

    }
}
