package org.epubmaker;

import org.epubmaker.Parser.Tag;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class App
{
    public static void main( String[] args )
    {
        try {
            Document doc = Jsoup.connect("https://jpmtl.com/books/2237/1191906").get();
//            Elements elems = doc.select(".chapter-content__content");
            Elements test2 = doc.select(".chapter-wrapper__nav");
            System.out.println(test2.get(1).attr("data-icon"));
//            System.out.println(test2);
//            System.out.println(elems.text());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
