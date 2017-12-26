package com.liao.practice.test01.proxyservice.sample01;

import com.liao.practice.test01.proxyservice.ApacheHttpClientUtils;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;

import java.util.ArrayList;
import java.util.List;

/**
 * 多线程
 * Created by liaowenqiang on 2017/12/26.
 */
public class Test {
    public static void main(String[] args) {
        List<Header> headers = new ArrayList<>();
        String url="";
        try {
            //url="http://localhost:8080/lol/v5/player?_dc=1514188798467&page=1&start=0&limit=25";
            url="http://gdc.hc.dev/kog/actuator/health";
            String str= ApacheHttpClientUtils.httpGet(url,headers,new HttpHost("localhost",888));
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
