package com.liao.practice.test01.webmagictest;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.utils.FilePersistentBase;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by liaowenqiang on 2017/11/1.
 */
public class FeiJianPipeline extends FilePersistentBase implements Pipeline {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    public FeiJianPipeline(String path) {
        this.setPath(path);
    }

    public void process(ResultItems resultItems, Task task) {
        String fileName=resultItems.get("title");
        String path = this.path + PATH_SEPERATOR;
        String context=resultItems.get("context");
        try {
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(this.getFile(path + fileName + ".txt")), "UTF-8"));
            printWriter.println(context);
            printWriter.close();
        } catch (IOException var10) {
            this.logger.warn("write file error", var10);
        }

    }
}
