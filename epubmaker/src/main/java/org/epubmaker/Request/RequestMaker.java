package org.epubmaker.Request;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.Request;
import org.epubmaker.JPMTL.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class RequestMaker {

    private static final OkHttpClient client = new OkHttpClient().newBuilder().build();
    private static final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
            .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
            .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

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

    public static String getJPChapter(long chapter) throws IOException{
        return getJPChapter(String.valueOf(chapter));
    }

    public static Chapter getChapterObject(long id) throws IOException{
        return mapper.readValue(getJPChapter(String.valueOf(id)), Chapter.class);
    }


    public static JPBook getJPBook(String book) throws IOException{

        Request request = new Request.Builder()
                .url(String.format("https://jpmtl.com/v2/chapter/%s/list?structured=true", book))
                .method("GET", null)
                .build();

        Response res = client.newCall(request).execute();
        String s = res.body().string();
        JSONObject obj = new JSONObject().put("volumes", new JSONArray(s));
        return mapper.readValue(obj.toString(), JPBook.class);
    }

    public static void getChapters(String bookID) throws IOException {
        JSONObject json = new JSONObject().put("chapterList", new JSONArray(getList(bookID)));
        ChapterList cl = mapper.readValue(json.toString(), ChapterList.class);

        cl.getChapterInfoList().stream().parallel().forEach(
                ChapterInfo::generateChapter
        );

        cl.getChapterInfoList().forEach(item ->{
                System.out.println(item.getChapter().getFullChapter());
        });

//        for(ChapterInfo info: cl.getChapterInfoList()){
//            System.out.println(info.getChapter().getFullChapter());
//            break;
//        }

    }


}
