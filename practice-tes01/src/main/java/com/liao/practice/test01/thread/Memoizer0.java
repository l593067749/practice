package com.liao.practice.test01.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * Created by liaowenqiang on 2016/9/7.
 */
public class Memoizer0<A,V> implements Computable<A,V> {
    private final Map<A,V> cache=new HashMap<A, V>();
    private final Computable<A,V> c;
    public Memoizer0(Computable<A,V> c){
        this.c=c;
    }
    public  V compute(A arg) throws ExecutionException, InterruptedException {
        V result=cache.get(arg);
        if(result==null){
            result=c.compute(arg);
            cache.put(arg,result);
        }
        return result;
    }
    public String toString(){
        String str="";
        Set<A> keys= cache.keySet();
        for(A a:keys){
            if(!a.equals(cache.get(a)+"")){
                str+=",["+a+":"+cache.get(a)+"]";
            }
        }
        if(str=="")str=cache.toString();
        return str;
    }
}
