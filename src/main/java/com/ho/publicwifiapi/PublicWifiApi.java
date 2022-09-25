package com.ho.publicwifiapi;

import com.ho.dto.MainDto;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;

public class PublicWifiApi {

    public String requestPublicWifiApi(int start, int end) throws IOException {
        String url = "http://openapi.seoul.go.kr:8088/5165546c7a617364353455674d4f71/json/TbPublicWifiInfo/"
                + start + "/" + end;
        OkHttpClient okHttpClient = new OkHttpClient();

        // java.net.URL
        URL requestURL = new URL(url);
        Request request = new Request.Builder().url(requestURL).get().build();

        Response response = okHttpClient.newCall(request).execute();
        String json = response.body().string();

        return json;
    }

    public MainDto getWifiInfos(String json) {

        JsonParser jsonParser = new JsonParser();

        return jsonParser.parse(json);
    }
}
