package com.ho.repository;

import com.ho.vo.HistoryVo;

import java.sql.SQLException;
import java.util.List;

public interface HistoryRepository {
    int insertHistoryInfos(String sql, Double lat, Double lnt) throws SQLException;
    int DeleteHistoryInfos(String sql, int historyId) throws SQLException;
    void getHistoryInfos(String sql, List<HistoryVo> list);
}
