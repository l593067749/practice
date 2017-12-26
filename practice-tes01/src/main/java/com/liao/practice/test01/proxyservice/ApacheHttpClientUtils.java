package com.liao.practice.test01.proxyservice;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.security.KeyStore;
import java.util.List;

/**
 * Created by liaowenqiang on 2017/4/14.
 */
public class ApacheHttpClientUtils {
    private static final Logger logger = LoggerFactory.getLogger(ApacheHttpClientUtils.class);
    public static String sslHttpPostTLSv1Pro(String url, KeyStore keyStore, String keyPassword, String paramStr) throws Exception {
        CloseableHttpClient client = null;
        String responseStr = null;
        CloseableHttpResponse response = null;
        try {
            // Trust own CA and all self-signed certs
            SSLContext sslcontext = new SSLContextBuilder().loadKeyMaterial(keyStore, keyPassword.toCharArray()).build();
            // Allow TLSv1 protocol only
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslcontext, new String[]{"TLSv1"}, null,
                    SSLConnectionSocketFactory.getDefaultHostnameVerifier());
            client = HttpClients.custom()
                    .setSSLSocketFactory(sslsf).build();
            HttpPost httpPost = new HttpPost(url);
            HttpEntity requestEntity = new StringEntity(paramStr);
            httpPost.setEntity(requestEntity);
            response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseStr = EntityUtils.toString(entity, "UTF-8");
            }
            EntityUtils.consume(entity);
        } catch (Exception e) {
            throw e;
        } finally {
            try{
                if(response!=null)response.close();
                if(client!=null)client.close();
            }catch (Exception e){
                logger.error("http response close or client close fail ",e);
            }
        }
        return responseStr;
    }

    public static String httpGet(String url) throws Exception {
        HttpEntity entity = null;
        String responseStr = null;
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        try {
            client = HttpClients.custom().setDefaultRequestConfig(RequestConfig.custom()
                    .setConnectionRequestTimeout(5000).setConnectTimeout(5000).setSocketTimeout(5000).build()).build();
            HttpGet httpGet = new HttpGet(url);
            response = client.execute(httpGet);
            entity = response.getEntity();
            if (entity != null) {
                responseStr = EntityUtils.toString(entity, "UTF-8");
            }
            EntityUtils.consume(entity);
        } catch (Exception e) {
            throw e;
        } finally {
            try{
                if(response!=null)response.close();
                if(client!=null)client.close();
            }catch (Exception e){
                logger.error("http response close or client close fail ",e);
            }
        }
        return responseStr;
    }
    public static String httpGet(String url, List<Header> heades) throws Exception {
        HttpEntity entity = null;
        String responseStr = null;
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        try {

            client = HttpClients.custom().setDefaultRequestConfig(RequestConfig.custom()
                    .setConnectionRequestTimeout(5000).setConnectTimeout(5000).setSocketTimeout(5000)
                    .build()).build();
            HttpGet httpGet = new HttpGet(url);
            if(heades!=null){
                for(Header header:heades){
                    httpGet.addHeader(header);
                }
            }
            response = client.execute(httpGet);
            entity = response.getEntity();
            if (entity != null) {
                responseStr = EntityUtils.toString(entity, "UTF-8");
            }
            EntityUtils.consume(entity);
        } catch (Exception e) {
            throw e;
        } finally {
            try{
                if(response!=null)response.close();
                if(client!=null)client.close();
            }catch (Exception e){
                logger.error("http response close or client close fail ",e);
            }
        }
        return responseStr;
    }
    public static String httpGet(String url, List<Header> heades,HttpHost proxy) throws Exception {
        HttpEntity entity = null;
        String responseStr = null;
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        try {

            client = HttpClients.custom().setDefaultRequestConfig(RequestConfig.custom()
                    .setProxy(proxy)
                    .setConnectionRequestTimeout(5000).setConnectTimeout(5000).setSocketTimeout(5000)
                    .build()).build();
            HttpGet httpGet = new HttpGet(url);
            if(heades!=null){
                for(Header header:heades){
                    httpGet.addHeader(header);
                }
            }
            response = client.execute(httpGet);
            entity = response.getEntity();
            if (entity != null) {
                responseStr = EntityUtils.toString(entity, "UTF-8");
            }
            EntityUtils.consume(entity);
        } catch (Exception e) {
            throw e;
        } finally {
            try{
                if(response!=null)response.close();
                if(client!=null)client.close();
            }catch (Exception e){
                logger.error("http response close or client close fail ",e);
            }
        }
        return responseStr;
    }
    public static String httpPost(String url, String paramStr) throws Exception {
        HttpEntity entity = null;
        String responseStr = null;
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        try {
            client = HttpClients.custom().setDefaultRequestConfig(RequestConfig.custom()
                    .setConnectionRequestTimeout(5000).setConnectTimeout(5000).setSocketTimeout(5000).build()).build();
            HttpPost httpPost = new HttpPost(url);
            if(paramStr!=null){
                StringEntity stringEntity = new StringEntity(paramStr, "UTF-8");
                httpPost.setEntity(stringEntity);
            }
            response = client.execute(httpPost);
            entity = response.getEntity();
            if (entity != null) {
                responseStr = EntityUtils.toString(entity, "UTF-8");
            }
            EntityUtils.consume(entity);
        } catch (Exception e) {
            throw e;
        } finally {
            try{
                if(response!=null)response.close();
                if(client!=null)client.close();
            }catch (Exception e){
                logger.error("http response close or client close fail ",e);
            }
        }
        return responseStr;
    }
    public static String httpPost(String url, String paramStr, List<Header> heades) throws Exception {
        HttpEntity entity = null;
        String responseStr = null;
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        try {
            client = HttpClients.custom().setDefaultRequestConfig(RequestConfig.custom()
                    .setConnectionRequestTimeout(5000).setConnectTimeout(5000).setSocketTimeout(5000).build()).build();
            HttpPost httpPost = new HttpPost(url);
            if(paramStr!=null){
                StringEntity stringEntity = new StringEntity(paramStr, "UTF-8");
                httpPost.setEntity(stringEntity);
            }
            if(heades!=null){
                for(Header header:heades){
                    httpPost.addHeader(header);
                }
            }
            response = client.execute(httpPost);
            entity = response.getEntity();
            if (entity != null) {
                responseStr = EntityUtils.toString(entity, "UTF-8");
            }
            EntityUtils.consume(entity);
        } catch (Exception e) {
            throw e;
        } finally {
            try{
                if(response!=null)response.close();
                if(client!=null)client.close();
            }catch (Exception e){
                logger.error("http response close or client close fail ",e);
            }
        }
        return responseStr;
    }



}
