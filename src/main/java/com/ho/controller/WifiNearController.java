package com.ho.controller;

import com.ho.vo.WifiVo;
import com.ho.dao.HistoryDao;
import com.ho.dao.WifiDao;
import lombok.SneakyThrows;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/NearWifi")
public class WifiNearController extends HttpServlet {

    WifiDao wifiDao = new WifiDao();
    HistoryDao historyDao = new HistoryDao();

    @SneakyThrows
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Double lat = Double.valueOf(request.getParameter("lat"));
        Double lnt = Double.valueOf(request.getParameter("lnt"));

        request.setAttribute("lat", lat);
        request.setAttribute("lnt", lnt);

        List<WifiVo> list = wifiDao.selectSearchWifiList(lat, lnt);
        int count = historyDao.insertHistory(lat, lnt);     // 실행된 개수

        request.setAttribute("list", list);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/");    // index.jsp 는 생략 가능
        requestDispatcher.forward(request, response);
    }
}