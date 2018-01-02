package com.liao.practice.test01.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;

/**
 * Created by liaowenqiang on 2018/1/2.
 */
public class Indexer {

    public void createIndexer(Article artical) throws Exception {
        // 建立索引
        // 1、把Article转换为Doucement对象
        Document doc = new Document();
        //根据实际情况，使用不同的Field来对原始内容建立索引， Store.YES表示是否存储字段原始内容
        doc.add(new LongField("id", artical.getId(), Field.Store.YES));
        doc.add(new StringField("author", artical.getAuthor(), Field.Store.YES));
        doc.add(new TextField("title", artical.getTitle(), Field.Store.YES));
        doc.add(new TextField("content", artical.getContent(), Field.Store.NO));

        // 2、建立索引
        // 指定索引库的位置，本例为项目根目录下indexDir
        Directory directory = FSDirectory.open(new File("./indexDir/"));
        // 分词器，不同的分词器有不同的规则
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LATEST, analyzer);
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        indexWriter.addDocument(doc);
        indexWriter.close();
    }
    /** 建立索引
     发表过文章过后，不仅数据库中有存储记录 索引库中也必须有一条*/
    public static void main(String args[]) throws Exception {
        Indexer indexer=new Indexer();
        // 模拟一条数据库中的记录
        Article artical = new Article(1, "Lucene全文检索框架",
                "Lucene如果信息检索系统在用户发出了检索请求后再去网上找答案","张三");
        indexer.createIndexer(artical);
         artical = new Article(2, "Lucene2全文检索框架",
                "Lucene2如果信息检索系统在用户发出了检索请求后再去网上找答案","李四");
        indexer.createIndexer(artical);
        artical = new Article(3, "Luc2ene2全文检索框架",
                "Luc2ene2如果信息检索系统在用户发出了检索请求后再去网上找答案","王五");
        indexer.createIndexer(artical);

    }
}
