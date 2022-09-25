package com.ho.dao;

import com.ho.dto.WifiDto;
import com.ho.vo.HistoryVo;
import com.ho.vo.WifiVo;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.*;

public class DbConnection {
    private static final DbConnection dbConnection = new DbConnection();
    public static DbConnection getInstance() {
        return dbConnection;
    }

    public Connection getConnection(Connection conn) {
        String URL = "jdbc:mysql://localhost/wifi_service";
        String id = "ho";
        String pass = "ho";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, id, pass);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return conn;
    }

    public void close(Connection conn, PreparedStatement ps, ResultSet rs) {

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

//    public void insertWifiInfos(String sql, List<WifiDto> list) throws SQLException {
//        conn.setAutoCommit(false);
//
//        try {
//            ps = conn.prepareStatement(sql);
//            int batchSize = 1000;
//            int cnt = 0;
//
//            for (int i = 0; i < list.size(); i++) {
//                WifiDto wifiDTO = list.get(i);
//
//                ps.setString(1, wifiDTO.getMgrNo());
//                ps.setString(2, wifiDTO.getWrdofc());
//                ps.setString(3, wifiDTO.getMainNm());
//                ps.setString(4, wifiDTO.getAdres1());
//                ps.setString(5, wifiDTO.getAdres2());
//                ps.setString(6, wifiDTO.getFloor());
//                ps.setString(7, wifiDTO.getTy());
//                ps.setString(8, wifiDTO.getMby());
//                ps.setString(9, wifiDTO.getSvcSe());
//                ps.setString(10, wifiDTO.getCmcwr());
//                ps.setString(11, wifiDTO.getYear());
//                ps.setString(12, wifiDTO.getDoor());
//                ps.setString(13, wifiDTO.getRemars3());
//                ps.setString(14, wifiDTO.getLat());
//                ps.setString(15, wifiDTO.getLnt());
//                ps.setString(16, wifiDTO.getDttm());
//
//                ps.addBatch();
//                cnt++;
//
//                if (batchSize == cnt) {
//                    ps.executeBatch();
//                    ps.clearBatch();
//                    cnt = 0;
//                }
//            }
//
//            ps.executeBatch();
//            conn.commit();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            conn.rollback();
//        } finally {
//            conn.setAutoCommit(true);
//            dbConnection.close();
//        }
//    }
//
//    public void deleteAllwifiInfos(String sql) throws SQLException {
//        conn.setAutoCommit(false);
//
//        try {
//            ps = conn.prepareStatement(sql);
//            ps.executeUpdate();
//            conn.commit();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            dbConnection.close();
//        }
//    }
//
//    public void getWifiList(String sql, List<WifiVo> list) {
//        try {
//            ps = conn.prepareStatement(sql);
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//
//                list.add(new WifiVo(
//
//                        rs.getString("distance"),
//                        rs.getString("X_SWIFI_MGR_NO"),
//                        rs.getString("X_SWIFI_WRDOFC"),
//                        rs.getString("X_SWIFI_MAIN_NM"),
//                        rs.getString("X_SWIFI_ADRES1"),
//                        rs.getString("X_SWIFI_ADRES2"),
//                        rs.getString("X_SWIFI_INSTL_FLOOR"),
//                        rs.getString("X_SWIFI_INSTL_TY"),
//                        rs.getString("X_SWIFI_INSTL_MBY"),
//                        rs.getString("X_SWIFI_SVC_SE"),
//                        rs.getString("X_SWIFI_CMCWR"),
//                        rs.getString("X_SWIFI_CNSTC_YEAR"),
//                        rs.getString("X_SWIFI_INOUT_DOOR"),
//                        rs.getString("X_SWIFI_REMARS3"),
//                        rs.getString("LAT"),
//                        rs.getString("LNT"),
//                        rs.getString("WORK_DTTM")
//
//                ));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            dbConnection.close();
//        }
//    }

//    public int insertHistoryInfos(String sql, Double lat, Double lnt) throws SQLException {
//        int count = 0;
//
//        try {
//            ps = conn.prepareStatement(sql);
//
//            conn.setAutoCommit(false);
//
//            ps.setDouble(1, lat);
//            ps.setDouble(2, lnt);
//
//            count = ps.executeUpdate();
//
//            conn.commit();
//
//        } catch (Exception e) {
//            conn.rollback();
//            conn.setAutoCommit(true);
//            throw new RuntimeException(e);
//        } finally {
//            dbConnection.close();
//        }
//
//        return count;
//    }
//
//
//
//    public int DeleteHistoryInfos(String sql, int historyId) throws SQLException {
//        int count = 0;
//        try {
//            conn.setAutoCommit(false);
//            ps = conn.prepareStatement(sql);
//
//            ps.setInt(1, historyId);
//
//            count = ps.executeUpdate();
//
//            conn.commit();
//        } catch (Exception e) {
//            conn.rollback();
//            conn.setAutoCommit(true);
//            throw new RuntimeException(e);
//        } finally {
//            dbConnection.close();
//        }
//
//        return count;
//    }
//
//    public void getHistoryInfos(String sql, List<HistoryVo> list) {
//        try{
//            ps = conn.prepareStatement(sql);
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                list.add(new HistoryVo(
//                        rs.getString("id"),
//                        rs.getDouble("lat"),
//                        rs.getDouble("lnt"),
//                        rs.getString("date")
//                ));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            dbConnection.close();
//        }
//    }
}
