package org.epubmaker.JPMTL.JPUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class JPSearch {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static SearchResponse doSearch(String query) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("https://jpmtl.com/v2/book/show/browse?query=%s&categories=&content_type=0&direction=0&page=1&limit=20&type=5&status=all&language=3", query))
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        return mapper.readValue(response.body().string(), SearchResponse.class);
    }

}
