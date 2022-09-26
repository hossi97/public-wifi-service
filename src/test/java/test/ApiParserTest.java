package test;

import com.ho.publicwifiapi.JsonParser;
import com.ho.publicwifiapi.PublicWifiApi;
import com.ho.dto.MainDto;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ApiParserTest {
    @Test
    static void apiParserTest() throws IOException {
        JsonParser jsonParser = new JsonParser();
        PublicWifiApi publicWifiApi = new PublicWifiApi();

        String json = publicWifiApi.requestPublicWifiApi(0, 10);
        System.out.println("json = " + json);

        System.out.println("apiParser.parse(json) = " + jsonParser.parse(json));

        MainDto mainDto = jsonParser.parse(json);
        System.out.println("mainDto = " + mainDto);

        System.out.println(mainDto.getTotalcount());
    }

    public static void main(String[] args) throws IOException {
        apiParserTest();
    }

}

/*
baseDto
    MainDto
        totalCount: 총 데이터 개의 갯수
        result: 요청이 정상적으로 처리됐는지 코드와 함께 제공
            ResultDto
        rowDtos: 각 행의 데이터들
            RowDto
            RowDto
            ...

 */
