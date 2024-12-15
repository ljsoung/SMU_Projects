package studentInformationManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {
    // DB 정보 기입
    final String DB_URL = "jdbc:mysql://localhost:3306/university?serverTimezone=Asia/Seoul&useSSL=false&allowPublicKeyRetrieval=true";
    final String DB_ID = "root";
    final String DB_PASSWORD = "0000";

    public void getConnection(Connection con){
        // JDBC 드라이브 연결
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("드라이브 로딩 오류 .....");
        }
        try {
            con = DriverManager.getConnection(DB_URL, DB_ID, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println("DB 연결 오류");
        }

    }

}
