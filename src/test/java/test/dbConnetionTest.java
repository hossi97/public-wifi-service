package test;


import java.sql.*;

public class dbConnetionTest {

    static Connection conn = null;

    static void getConnection() {
        // 내 DB 정보에 맞춰서 수정 필요
        String URL = "jdbc:mysql://localhost/wifi_service";
        String id = "ho";
        String pass = "ho";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, id, pass);
            System.out.println("연결완료 : " + conn);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("연결 오류");
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        getConnection();
    }
}
