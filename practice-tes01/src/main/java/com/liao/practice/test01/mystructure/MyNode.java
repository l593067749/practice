package com.liao.practice.test01.mystructure;

/**
 * Created by liaowenqiang on 2018/1/4.
 */
public class MyNode {
    private Object data;
    private MyNode next;
    private MyNode top;

    public MyNode getTop() {
        return top;
    }

    public void setTop(MyNode top) {
        this.top = top;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public MyNode getNext() {
        return next;
    }

    public void setNext(MyNode next) {
        this.next = next;
    }
}
