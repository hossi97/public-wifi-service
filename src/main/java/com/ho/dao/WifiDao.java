// dao: db랑 연결하는
// controller: 요청을 받고 반환 / service : 요청 받은 것을 갖고 데이터 조작
// dto: 계층간의 데이터 전달을 위해 사용한다. entity 에서 필요한 것들만 반환하기 위해 사용한다.
// vo: db 컬럼 이름들을 기준으로 변수를 만든다. 컬럼 말고도 클래스를 사용해서 데이터를 만들 수 있다.

package com.ho.dao;

import com.ho.dto.WifiDto;
import com.ho.vo.WifiVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WifiDao {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void getConnection() {
        String URL = "jdbc:mysql://localhost/wifi_service";
        String id = "ho";
        String pass = "ho";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, id, pass);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dbClose() {
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


    public void insertWifi(List<WifiDto> list) throws SQLException {
        String sql = "insert into wifi(x_swifi_mgr_no, x_swifi_wrdofc, x_swifi_main_nm,\n" +
                "                 x_swifi_adres1, x_swifi_adres2, x_swifi_instl_floor,\n" +
                "                 x_swifi_instl_ty, x_swifi_instl_mby, x_swifi_svc_se,\n" +
                "                 x_swifi_cmcwr, x_swifi_cnstc_year, x_swifi_inout_door,\n" +
                "                 x_swifi_remars3, lat, lnt, work_dttm)\n" +
                "   values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        getConnection();
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
            dbClose();
        }
    } //17804개

    public void deleteAll() throws SQLException {
        String sql = "truncate table wifi";

        getConnection();
        conn.setAutoCommit(false);

        try {
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dbClose();
        }
    }

    // 내근처 와이파이 조회
    public List<WifiVo> selectSearchWifiList(Double lat, Double lnt) {
        String sql = "select * " +
                ", format((6371 * acos(cos(radians(" + lat + ")) * cos(radians(lat)) * cos(radians(lnt) - radians(" + lnt + ")) " +
                "+ sin(radians(" + lat + ")) * sin(radians(lat)))), 4) as distance " +
                " from wifi " +
                " order by distance , X_SWIFI_MGR_NO" +
                " limit 20";



        getConnection();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            List<WifiVo> list = new ArrayList<>();

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
            return list;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dbClose();
        }
    }
}