package com.liao.practice.test01.webmagictest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * Created by liaowenqiang on 2017/11/1.
 */
public class HupuBxjPageProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(1).setSleepTime(100);
    public final Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void process(Page page) {
        Selectable selectable= page.getHtml().xpath("//ul[@class='for-list']/li/div/a");
        List<String> list=selectable.all();
        list.stream().forEach(val->logger.warn(val));
    }


    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new HupuBxjPageProcessor()).addUrl("https://bbs.hupu.com/bxj").thread(1).run();
    }
}
