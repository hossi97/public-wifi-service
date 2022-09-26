package test;

import com.ho.dto.MainDto;
import com.ho.dto.WifiDto;
import com.ho.publicwifiapi.PublicWifiApi;

import java.io.IOException;

public class ApiJsonTest {
    public static void main(String[] args) throws IOException {

        PublicWifiApi publicWifiApi = new PublicWifiApi();
        String json = publicWifiApi.requestPublicWifiApi(1, 100);
        MainDto mainDto = publicWifiApi.getWifiInfos(json);

        System.out.println(mainDto.getWifiDtos());

        System.out.println("----");

        for (WifiDto wifiDto : mainDto.getWifiDtos()) {
            System.out.println(wifiDto.getMainNm());
            System.out.println(wifiDto.getDttm());
        }

        System.out.println("----");

        System.out.println(mainDto.getTotalcount());


    }

}