package com.ho.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class MainDto {

    @SerializedName("list_total_count")
    private int totalcount;

    @SerializedName("RESULT")
    private ResultDto result;

    @SerializedName("row")
    private List<WifiDto> wifiDtos;

    public List<WifiDto> getWifiDtos() {
        return wifiDtos;
    }
}