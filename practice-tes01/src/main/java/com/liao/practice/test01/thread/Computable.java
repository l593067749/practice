package com.liao.practice.test01.thread;

import java.util.concurrent.ExecutionException;

/**
 * Created by liaowenqiang on 2016/9/7.
 */
public interface Computable<A,V> {
    V compute(A arg) throws ExecutionException, InterruptedException;
}
