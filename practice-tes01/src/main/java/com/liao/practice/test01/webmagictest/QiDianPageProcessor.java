package com.liao.practice.test01.webmagictest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

/**
 * Created by liaowenqiang on 2017/11/1.
 */
public class QiDianPageProcessor implements PageProcessor {
    private Site site = Site.me().setCharset("UTF-8").setRetryTimes(1).setSleepTime(100);
    public final Logger logger = LoggerFactory.getLogger(getClass());
    public void process(Page page) {
        Html html=page.getHtml();
        String title=html.xpath("//h3[@class='j_chapterName']/text()").toString();
        String context=html.xpath("//div[@class='read-content j_readContent']/html()").toString();
        String newPage="https:"+html.xpath("//a[@id='j_chapterNext']/@href").toString();
        logger.warn(title);
        logger.warn(context);
        logger.warn(newPage);
        page.putField("title",title);
        page.putField("context",context);
        page.addTargetRequest(newPage);

    }
    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        Spider.create(new QiDianPageProcessor()).addUrl("https://read.qidian.com/chapter/tcH3TasLtZc6Q5WO_IQttQ2/LUOcipI6gWaaGfXRMrUjdw2")
                .addPipeline(new FeiJianPipeline("d:\\pic"))
                .thread(4).run();
    }
}
