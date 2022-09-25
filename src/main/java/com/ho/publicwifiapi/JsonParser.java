package com.ho.publicwifiapi;

import com.google.gson.Gson;
import com.ho.dto.BaseDto;
import com.ho.dto.MainDto;

public class JsonParser {
    private Gson gson = new Gson();

    public MainDto parse(String json){

        BaseDto baseDto = gson.fromJson(json, BaseDto.class);

        return baseDto.getTbPublicWifiInfo();
    }
}