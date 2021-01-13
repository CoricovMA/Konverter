package org.epubmaker;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Metadata;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubWriter;
import nl.siegmann.epublib.service.MediatypeService;
import org.epubmaker.JPMTL.Chapter;
import org.epubmaker.JPMTL.JPBook;
import org.epubmaker.JPMTL.JPUtil.JPSearch;
import org.epubmaker.JPMTL.JPUtil.NovelResponse;
import org.epubmaker.JPMTL.JPUtil.SearchResponse;
import org.epubmaker.Request.Options.JPRequestOption;
import org.epubmaker.Request.Options.RequestOption;
import org.epubmaker.Request.Request;
import org.epubmaker.Request.RequestMaker;
import org.json.JSONObject;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.LinkedList;

public class App {
    public static void main(String[] args) {

        try {
            RequestMaker.getChapters("295");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("parser", "jpmtl");
//        jsonObject.put("requested", "list");
//
//        JSONObject json = new JSONObject();
//        json.put("url", "something");
//        json.put("request_option", jsonObject);
//        json.put("type", "jp_req");
//        try {
//            Request test = new ObjectMapper().readValue(json.toString(), Request.class);
//            System.out.println(test);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        try {
//            Book book = new Book();
//            Metadata metadata = new Metadata();
//            metadata.addTitle("Test");
//            String s = RequestMaker.getJPChapter("1191926");
//            Chapter chp = new ObjectMapper().readValue(s, Chapter.class);
////            book.addSection("test", new Resource(chp.getHtml().getBytes(), MediatypeService.XHTML));
//pr
//            EpubWriter writer = new EpubWriter();
//            try {
//                writer.write(book, new FileOutputStream("test1.epub"));
//
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        }


    }

}
