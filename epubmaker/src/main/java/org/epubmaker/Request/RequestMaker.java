package org.epubmaker.Request;


import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.Request;

import java.io.IOException;

public class RequestMaker {

    private static final OkHttpClient client = new OkHttpClient().newBuilder().build();

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

    public static String getJPChapter(long chapter) throws IOException{
        return getJPChapter(String.valueOf(chapter));
    }
}
