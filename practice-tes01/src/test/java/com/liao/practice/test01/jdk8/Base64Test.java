package com.liao.practice.test01.jdk8;


import java.util.Base64;
import java.util.Objects;

/**
 * Created by liaowenqiang on 2018/1/9.
 */
public class Base64Test {


    public static void main(String[] args) throws Exception {
         final Base64.Decoder m_decoder = Base64.getDecoder();
         final Base64.Encoder m_encoder = Base64.getEncoder();
        String str="http://www.baidu.com/trade?id=12";
        String encode01=m_encoder.encodeToString(str.getBytes("utf-8"));
        String encode02=com.sun.org.apache.xerces.internal.impl.dv.util.Base64.encode(str.getBytes("utf-8"));
        System.out.println(Objects.equals(encode01,encode02));
        System.out.println(new String(m_decoder.decode(encode02)).toString());
        System.out.println(new String(com.sun.org.apache.xerces.internal.impl.dv.util.Base64.decode(encode01)).toString());
    }

}
