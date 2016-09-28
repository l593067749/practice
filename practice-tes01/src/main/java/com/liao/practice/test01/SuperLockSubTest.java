package com.liao.practice.test01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SuperLockSubTest<E>{
    public List<E> list=Collections.synchronizedList(new ArrayList<E>());
    public synchronized boolean set(E e){
        return this.list.add(e);
    }
}