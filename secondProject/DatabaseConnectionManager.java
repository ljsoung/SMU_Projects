package basicProjectII.secondProject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnectionManager {
    Connection con = null;
    PreparedStatement stmt = null;

    // DB 정보 기입
    static final String DB_URL = "jdbc:mysql://localhost:3306/weather?serverTimezone=Asia/Seoul&useSSL=false&allowPublicKeyRetrieval=true";
    static final String DB_ID = "root";
    static final String DB_PASSWORD = "0000";

    public Connection getConnection() {
        // JDBC 드라이브 연결
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("드라이브 로딩 오류 .....");
        }

        try {
            con = DriverManager.getConnection(DB_URL, DB_ID, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

    // 데이터베이스에서 level1 데이터를 가져오는 메서드
    public static List<String> getLevel1Data() {
        List<String> level1Data = new ArrayList<>();
        String query = "SELECT DISTINCT level1 FROM locals";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_ID, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // 결과 데이터를 리스트에 추가
            while (rs.next()) {
                level1Data.add(rs.getString("level1"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return level1Data;
    }

    // level1 선택에 따른 level2값 가져오기
    public static List<String> getLevel2Data(String level1) {
        List<String> level2Data = new ArrayList<>();
        String query = "SELECT level2 FROM locals WHERE level1 = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_ID, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, level1); // level1 파라미터 바인딩
            ResultSet rs = stmt.executeQuery();

            // 결과 데이터를 리스트에 추가
            while (rs.next()) {
                level2Data.add(rs.getString("level2"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return level2Data;
    }

}