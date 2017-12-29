package com.liao.practice.test01.thread;

import java.util.Optional;

/**
 * Created by liaowenqiang on 2017/12/22.
 */
public class Test03 {
    public static void main(String[] args) {
        Integer a = 10;
        Integer b = null;
        Optional<Integer> aOpt = Optional.ofNullable(a);
        Optional<Integer> bOpt = Optional.ofNullable(b);
        System.out.println(aOpt.orElse(0) / bOpt.orElse(0));

    }
}
