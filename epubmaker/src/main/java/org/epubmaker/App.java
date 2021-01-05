package org.epubmaker;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Metadata;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubWriter;
import nl.siegmann.epublib.service.MediatypeService;
import org.epubmaker.JPMTL.Chapter;
import org.epubmaker.JPMTL.JPUtil.JPSearch;
import org.epubmaker.JPMTL.JPUtil.NovelResponse;
import org.epubmaker.JPMTL.JPUtil.SearchResponse;
import org.epubmaker.Request.RequestMaker;
import org.json.JSONObject;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

public class App {
    public static void main(String[] args) {
//        try {

//            ByteBuffer bb = ByteBuffer.wrap(chp.getFullChapter().getBytes());
//            byte[] bytes;
//            while(true){
//                try {
//                        bytes = new byte[400];
//                        bb.get(bytes);
//                        System.out.println(new String(bytes));
//                        System.out.println(bb.remaining());
//
//                }catch (BufferUnderflowException e){
//                    bytes = new byte[bb.remaining()];
//                    bb.get(bytes);
//                    System.out.println(new String(bytes));
//                    break;
//                }
//            }
        try {
            Book book = new Book();
            Metadata metadata = new Metadata();
            metadata.addTitle("Test");
            String s = RequestMaker.getJPChapter("1191926");
            Chapter chp = new ObjectMapper().readValue(s, Chapter.class);
//            book.addSection("test", new Resource(chp.getHtml().getBytes(), MediatypeService.XHTML));

            EpubWriter writer = new EpubWriter();
            try {
                writer.write(book, new FileOutputStream("test1.epub"));

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }


    }
}
