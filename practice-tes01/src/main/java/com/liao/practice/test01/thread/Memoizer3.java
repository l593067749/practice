package com.liao.practice.test01.thread;

import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by liaowenqiang on 2016/9/7.
 */
public class Memoizer3<A,V> implements Computable<A,V> {
    private final Map<A,Future<V>> cache=new ConcurrentHashMap<A, Future<V>>();
    private final Computable<A,V> c;
    public Memoizer3(Computable<A,V> c){
        this.c=c;
    }
    public  V compute(final A arg) throws ExecutionException, InterruptedException {
        Future<V> f=cache.get(arg);
        if(f==null){
            Callable<V> eval=new Callable<V>() {
                public V call() throws Exception {
                    return c.compute(arg);
                }
            };
            FutureTask<V> ft=new FutureTask<V>(eval);
            f=ft;
            cache.put(arg,ft);
            ft.run();
        }

            return f.get();
    }
    public String toString(){
        return cache.toString();
    }
}
