package org.epubmaker.JPMTL.JPUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.PropertyResourceBundle;

public class JPSearch {

    private static ObjectMapper mapper;
    private static OkHttpClient client;

    public static void init(){
        mapper = new ObjectMapper();
        client = new OkHttpClient().newBuilder().build();
    }

    public static SearchResponse doSearch(String query) throws IOException {
        
        Request request = new Request.Builder()
                .url(String.format("https://jpmtl.com/v2/book/show/browse?query=%s&categories=&content_type=0&direction=0&page=1&limit=20&type=5&status=all&language=3", query))
                .method("GET", null)
                .build();

        Response response = client.newCall(request).execute();

        String s = response.body().string();

        JSONObject jsonObject = new JSONObject("{\"novels\":" + s + "}");

        return mapper.readValue(jsonObject.toString(), SearchResponse.class);
    }

}
