package com.liao.practice.test01.webmagictest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Random;


/**
 * Created by liaowenqiang on 2017/10/31.
 */
public class HupuPageProcessor  implements PageProcessor {
    private Site site = Site.me().setRetryTimes(1).setSleepTime(100);
    public final  Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void process(Page page) {
        logger.warn("asdfsadfasdf");

        Selectable selectable=page.getHtml().xpath("//div[@class='slider-pic']/ul");
        logger.warn(selectable.toString());
        List<String> list=selectable.css("ul li img").xpath("//img/@src").all();
        list.stream().forEach(str->logger.warn(str));
        list.stream().forEach(str->downImage(str.replaceAll("\\//","http://"),"hupu",new Random().nextLong()+""));
    }

    public void downImage(String httpUrl,String filePath,String fileName){
        URL url = null;
        DataInputStream dataInputStream=null;
        FileOutputStream fileOutputStream=null;
        try {
            url = new URL(httpUrl);
            String[] temp=httpUrl.split("\\.");
            String imageSuffix="";
            if(temp.length>0){
                imageSuffix=temp[temp.length-1];
            }
            dataInputStream = new DataInputStream(url.openStream());
            String imageName = fileName + "."+imageSuffix;
            File file=new File("d:\\pic\\"+filePath);    //设置下载路径
            if(!file.isDirectory()){
                file.mkdirs();
            }
             fileOutputStream = new FileOutputStream(new File("d:\\pic\\"+ filePath +"\\"+ imageName.trim()));
            byte[] buffer = new byte[1024];
            int length;
            while ((length = dataInputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, length);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                dataInputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        Spider.create(new HupuPageProcessor()).addUrl("https://www.hupu.com/").thread(1).run();
    }
}
