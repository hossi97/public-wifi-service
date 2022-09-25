package com.ho.controller;

import com.ho.dao.HistoryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

//history-remove.do
@WebServlet("/RemoveHistory")
public class RemoveHistoryController extends HttpServlet {
    private final HistoryDao historyDao;

    public RemoveHistoryController() {
        this.historyDao = new HistoryDao();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int historyId = Integer.parseInt(req.getParameter("historyId"));

        try {
            int count = historyDao.deleteHistory(historyId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}