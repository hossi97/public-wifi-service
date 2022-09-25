package com.ho.service;

import com.ho.dao.DbConnection;
import com.ho.vo.HistoryVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HistoryService {
    private static final HistoryService historyService = new HistoryService();
    private static DbConnection dbConnection = DbConnection.getInstance();
    private static Connection conn = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    public static HistoryService getHistoryService() {
        return historyService;
    }

    public int insertHistoryInfos(String sql, Double lat, Double lnt) throws SQLException {
        int count = 0;

        conn = dbConnection.getConnection(conn);

        try {
            ps = conn.prepareStatement(sql);

            conn.setAutoCommit(false);

            ps.setDouble(1, lat);
            ps.setDouble(2, lnt);

            count = ps.executeUpdate();

            conn.commit();

        } catch (Exception e) {
            conn.rollback();
            conn.setAutoCommit(true);
            throw new RuntimeException(e);
        } finally {
            dbConnection.close(conn, ps, rs);
            System.out.println("=============== 4 ===============");
            System.out.println(conn.isClosed());
            System.out.println(ps.isClosed());
        }

        return count;
    }


    public int DeleteHistoryInfos(String sql, int historyId) throws SQLException {
        int count = 0;

        conn = dbConnection.getConnection(conn);

        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);

            ps.setInt(1, historyId);

            count = ps.executeUpdate();

            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            conn.setAutoCommit(true);
            throw new RuntimeException(e);
        } finally {
            dbConnection.close(conn, ps, rs);
            System.out.println("=============== 5 ===============");
            System.out.println(conn.isClosed());
            System.out.println(ps.isClosed());
        }

        return count;
    }

    public void getHistoryInfos(String sql, List<HistoryVo> list) throws SQLException {

        conn = dbConnection.getConnection(conn);

        try{
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new HistoryVo(
                        rs.getString("id"),
                        rs.getDouble("lat"),
                        rs.getDouble("lnt"),
                        rs.getString("date")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dbConnection.close(conn, ps, rs);
            System.out.println("=============== 6 ===============");
            System.out.println(ps.isClosed());
            System.out.println(rs.isClosed());
        }
    }

}
