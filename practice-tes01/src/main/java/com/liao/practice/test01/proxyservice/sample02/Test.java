package com.liao.practice.test01.proxyservice.sample02;

import com.liao.practice.test01.proxyservice.ApacheHttpClientUtils;
import org.apache.http.Header;
import org.apache.http.HttpHost;

import java.util.ArrayList;
import java.util.List;

/**测试一下代理服务
 * 他这个用了线程池队列来执行任务
 * Created by liaowenqiang on 2017/12/26.
 */
public class Test {
    public static void main(String[] args) {
        List<Header> headers = new ArrayList<>();
        String url="";
        try {
            //url="http://localhost:8080/lol/v5/player?_dc=1514188798467&page=1&start=0&limit=25";
            url="http://gdc.hc.dev/kog/actuator/health";
            String str=ApacheHttpClientUtils.httpGet(url,headers,new HttpHost("localhost",8002));
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
