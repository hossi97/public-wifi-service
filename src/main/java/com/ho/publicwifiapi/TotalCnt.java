package com.ho.publicwifiapi;

import com.ho.dto.MainDto;

import java.io.IOException;

public class TotalCnt {

    public int getNum() throws IOException {
        PublicWifiApi publicWifiApi = new PublicWifiApi();

        String json = publicWifiApi.requestPublicWifiApi(1, 100);
        MainDto mainDto = publicWifiApi.getWifiInfos(json);

        int totalcount = mainDto.getTotalcount();

        int num = totalcount/1000;

        if(totalcount > num*1000){
            num++;
        }

        return num;
    }
}