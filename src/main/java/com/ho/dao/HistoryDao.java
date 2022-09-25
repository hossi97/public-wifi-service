package com.ho.dao;

import com.ho.repository.HistoryRepository;
import com.ho.service.HistoryService;
import com.ho.vo.HistoryVo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoryDao {
    HistoryRepository historyService = HistoryService.getHistoryService();

    public int insertHistory(Double lat, Double lnt) throws SQLException {
        String sql = "insert into history(lat, lnt, date) values(?, ?, now())";

        return historyService.insertHistoryInfos(sql, lat, lnt);
    }

    public int deleteHistory(int historyId) throws SQLException {
        String sql = "delete from history where id = ?";

        return historyService.DeleteHistoryInfos(sql, historyId);
    }

    public List<HistoryVo> selectHistoryList() {
        String sql = "select * from history order by id desc";

        List<HistoryVo> list = new ArrayList<>();
        historyService.getHistoryInfos(sql, list);

        return list;
    }

}