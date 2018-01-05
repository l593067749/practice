package com.liao.practice.test01.mystructure;


import java.util.Iterator;
import java.util.Objects;

/**
 * Created by liaowenqiang on 2018/1/4.
 */
public class MyLinkList<T> {
    private MyNode currNode=null;
    private MyNode firstNode=null;
    private int size;
    public void add(T element){
        MyNode node=new MyNode();
        node.setData(element);
        if(firstNode==null){
            firstNode=node;
            currNode=node;
        }else {
            node.setTop(currNode);
            currNode.setNext(node);
            currNode=node;
        }
        size++;
    }
    public T remove(T element){
        MyNode preNode=firstNode;
        MyNode currFindNode=firstNode.getNext();
        while (currFindNode!=null){
            if(Objects.equals(currFindNode.getData(),element)){
                preNode.setNext(currFindNode.getNext());
                size--;
                return (T)currFindNode.getData();
            }else{
                preNode=currFindNode;
                currFindNode=currFindNode.getNext();
            }
        }
        return null;
    }
    public T get(T element){
        MyNode currFindNode=firstNode.getNext();
        while (currFindNode!=null){
            if(Objects.equals(currFindNode.getData(),element)){
                return (T)currFindNode.getData();
            }else{
                currFindNode=currFindNode.getNext();
            }
        }
        return null;
    }

    public T removeFirst(){
        MyNode currFirstNode=firstNode;
        firstNode=firstNode.getNext();
        firstNode.setTop(null);
        size--;
        return (T)currFirstNode.getData();
    }
    public T removeLaster(){
        MyNode curNode=currNode;
        currNode=currNode.getTop();
        currNode.setNext(null);
        size--;
        return (T)curNode.getData();
    }
    public void display(){
        if(size!=0){
            MyNode curr=firstNode;
            while (curr!=null){
                System.out.print(curr.getData()+"\t");
                curr=curr.getNext();
            }
        }
        System.out.println();
    }
    public MyIterable iterable(){
        return new MyIterable(firstNode);
    }
    public class MyIterable implements Iterator<T> {

        private MyNode node;
        private MyNode lastNode;
        public MyIterable(MyNode node){
            this.node=node;
        }
        @Override
        public boolean hasNext() {
            return node!=null;
        }

        @Override
        public T next() {
            T t=(T)node.getData();
            lastNode=node;
            node=node.getNext();
            return t;
        }
        public void remove(){
            if(lastNode==null){
                throw new IllegalStateException();
            }
            MyLinkList.this.remove((T)lastNode.getData());
            lastNode=null;
        }
    }

}
