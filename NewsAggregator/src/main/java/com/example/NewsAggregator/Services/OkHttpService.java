package com.example.NewsAggregator.Services;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OkHttpService {
    private final OkHttpClient okHttpClient;

    public OkHttpService() {
        this.okHttpClient = new OkHttpClient();
    }

    public String fetchData(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful())
                throw new IOException("Unexpected Code :" + response.code());

            return response.body() != null ? response.body().string() : "";

        }
    }
}
