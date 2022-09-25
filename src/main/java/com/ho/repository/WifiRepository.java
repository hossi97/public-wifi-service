package com.ho.repository;

import com.ho.dto.WifiDto;
import com.ho.vo.WifiVo;

import java.sql.SQLException;
import java.util.List;

public interface WifiRepository {
    void insertWifiInfos(String sql, List<WifiDto> list) throws SQLException;
    void deleteAllWifiInfos(String sql) throws SQLException;
    void getWifiList(String sql, List<WifiVo> list);
}
