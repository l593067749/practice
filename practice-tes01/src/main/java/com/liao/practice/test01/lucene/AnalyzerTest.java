package com.liao.practice.test01.lucene;

import net.paoding.analysis.analyzer.PaodingAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;

/**
 * Created by liaowenqiang on 2018/1/2.
 */
public class AnalyzerTest {
    public static void test(String text) throws IOException {
        Analyzer analyzer=new StandardAnalyzer();

        TokenStream tokenStream = analyzer.tokenStream("", text);
        tokenStream.reset();
        while (tokenStream.incrementToken()) {
            CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
            System.out.println(charTermAttribute);

        }
    }
    public static void testPaodingAnalyzer(String text) throws IOException {
        Analyzer analyzer = new PaodingAnalyzer("classpath:paoding/paoding-analysis.properties");
        TokenStream tokenStream = analyzer.tokenStream("", text);
        tokenStream.reset();
        while (tokenStream.incrementToken()) {
            CharTermAttribute charTermAttribute = tokenStream
                    .addAttribute(CharTermAttribute.class);
            System.out.println(charTermAttribute);

        }
    }

    public static void main(String[] args) throws IOException {
        String text = "An IndexWriter creates and maintains an index";
        test(text);
        text = "臣本布衣躬耕于南阳，苟全性命于乱世...";
        test(text);
        testPaodingAnalyzer(text);
    }
}
