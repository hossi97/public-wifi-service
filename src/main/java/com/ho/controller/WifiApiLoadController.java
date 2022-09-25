package com.ho.controller;

import com.ho.dto.MainDto;
import com.ho.dao.WifiDao;
import com.ho.publicwifiapi.PublicWifiApi;
import com.ho.publicwifiapi.TotalCnt;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/WifiList")
public class WifiApiLoadController extends HttpServlet {

    private final PublicWifiApi publicWifiApi = new PublicWifiApi();
    private final WifiDao wifiDAO = new WifiDao();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        TotalCnt totalCnt = new TotalCnt();

        try {
            wifiDAO.deleteAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        int num = totalCnt.getNum();
        int start = 0, end = 999;

        for (int i = 0; i < num; i++) {
            String json = publicWifiApi.requestPublicWifiApi(start, end);
            MainDto mainDTO = publicWifiApi.getWifiInfos(json);

            try {
                wifiDAO.insertWifi(mainDTO.getWifiDtos());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            start += 1000;
            end += 1000;
        }

        String json = publicWifiApi.requestPublicWifiApi(0, 1);
        req.setAttribute("totalCount", publicWifiApi.getWifiInfos(json).getTotalcount());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/wifiLoad.jsp");
        requestDispatcher.forward(req, res);
    }


}