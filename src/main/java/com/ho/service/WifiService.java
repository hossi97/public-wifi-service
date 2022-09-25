package com.ho.service;

import com.ho.dao.DbConnection;
import com.ho.dto.WifiDto;
import com.ho.vo.WifiVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class WifiService {
    private static final WifiService wifiService = new WifiService();
    private static DbConnection dbConnection = DbConnection.getInstance();
    private static Connection conn = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    public static WifiService getWifiService() {
        return wifiService;
    }

    public void insertWifiInfos(String sql, List<WifiDto> list) throws SQLException {
        conn = dbConnection.getConnection(conn);

        conn.setAutoCommit(false);

        try {
            ps = conn.prepareStatement(sql);
            int batchSize = 1000;
            int cnt = 0;

            for (int i = 0; i < list.size(); i++) {
                WifiDto wifiDTO = list.get(i);

                ps.setString(1, wifiDTO.getMgrNo());
                ps.setString(2, wifiDTO.getWrdofc());
                ps.setString(3, wifiDTO.getMainNm());
                ps.setString(4, wifiDTO.getAdres1());
                ps.setString(5, wifiDTO.getAdres2());
                ps.setString(6, wifiDTO.getFloor());
                ps.setString(7, wifiDTO.getTy());
                ps.setString(8, wifiDTO.getMby());
                ps.setString(9, wifiDTO.getSvcSe());
                ps.setString(10, wifiDTO.getCmcwr());
                ps.setString(11, wifiDTO.getYear());
                ps.setString(12, wifiDTO.getDoor());
                ps.setString(13, wifiDTO.getRemars3());
                ps.setString(14, wifiDTO.getLat());
                ps.setString(15, wifiDTO.getLnt());
                ps.setString(16, wifiDTO.getDttm());

                ps.addBatch();
                cnt++;

                if (batchSize == cnt) {
                    ps.executeBatch();
                    ps.clearBatch();
                    cnt = 0;
                }
            }

            ps.executeBatch();
            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            conn.setAutoCommit(true);
            dbConnection.close(conn, ps, rs);
        }
    }

    public void deleteAllwifiInfos(String sql) throws SQLException {
        conn = dbConnection.getConnection(conn);
        conn.setAutoCommit(false);

        try {
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dbConnection.close(conn, ps, rs);
        }
    }

    public void getWifiList(String sql, List<WifiVo> list){
        conn = dbConnection.getConnection(conn);

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new WifiVo(

                        rs.getString("distance"),
                        rs.getString("X_SWIFI_MGR_NO"),
                        rs.getString("X_SWIFI_WRDOFC"),
                        rs.getString("X_SWIFI_MAIN_NM"),
                        rs.getString("X_SWIFI_ADRES1"),
                        rs.getString("X_SWIFI_ADRES2"),
                        rs.getString("X_SWIFI_INSTL_FLOOR"),
                        rs.getString("X_SWIFI_INSTL_TY"),
                        rs.getString("X_SWIFI_INSTL_MBY"),
                        rs.getString("X_SWIFI_SVC_SE"),
                        rs.getString("X_SWIFI_CMCWR"),
                        rs.getString("X_SWIFI_CNSTC_YEAR"),
                        rs.getString("X_SWIFI_INOUT_DOOR"),
                        rs.getString("X_SWIFI_REMARS3"),
                        rs.getString("LAT"),
                        rs.getString("LNT"),
                        rs.getString("WORK_DTTM")

                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dbConnection.close(conn, ps, rs);
        }
    }

}
