package org.epubmaker.Request;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.epubmaker.JPMTL.Chapter;
import org.epubmaker.JPMTL.ChapterInfo;
import org.epubmaker.JPMTL.ChapterList;
import org.epubmaker.JPMTL.JPBook;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class RequestMaker {

    private static final OkHttpClient client = new OkHttpClient().newBuilder().build();
    private static final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
            .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
            .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

    private static final Logger logger = LogManager.getLogger(RequestMaker.class);


    public static String doRequest(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String getJPChapter(String chapter) throws IOException {
        Request request = new Request.Builder()
                .url(String.format("https://jpmtl.com/v2/chapter/%s", chapter))
                .method("GET", null)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String getList(String bookID) throws IOException {
        Request request = new Request.Builder()
                .url(String.format("https://jpmtl.com/v2/chapter/%s/list", bookID))
                .method("GET", null)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String getJPChapter(long chapter) throws IOException {
        return getJPChapter(String.valueOf(chapter));
    }

    public static Chapter getChapterObject(long id) throws IOException {
        return mapper.readValue(getJPChapter(String.valueOf(id)), Chapter.class);
    }


    public static JPBook getJPBook(String book) throws IOException {

        Request request = new Request.Builder()
                .url(String.format("https://jpmtl.com/v2/chapter/%s/list?structured=true", book))
                .method("GET", null)
                .build();

        Response res = client.newCall(request).execute();
        String s = res.body().string();
        JSONObject obj = new JSONObject().put("volumes", new JSONArray(s));
        return mapper.readValue(obj.toString(), JPBook.class);
    }

    public static JPBook getChapters(String bookID, String bookTitle) throws IOException {

        logger.info("Retrieving chapters for Book: {}", bookTitle);

        long start = System.currentTimeMillis();

        JSONObject json = new JSONObject().put("chapterList", new JSONArray(getList(bookID)));
        ChapterList cl = mapper.readValue(json.toString(), ChapterList.class);

        logger.info("Chapter list retrieved. Action took: {}ms", (System.currentTimeMillis() - start));

        logger.info("Retrieving chapters. Parallel streams.");
        start = System.currentTimeMillis();

        cl.getChapterInfoList().stream().parallel().forEach(
                ChapterInfo::generateChapter
        );

        logger.info("Chapters retrieved and generated. Action took: {}ms.", (System.currentTimeMillis() - start));

        return new JPBook(cl, bookTitle);

    }


}
