package dataInsertProgram;

import java.sql.*;
import java.io.*;
import java.util.StringTokenizer;

public class UserDataInsert extends DatabaseHelper {

    String sql; // INSERT 구문을 사용하기 위한 문자열 변수
    BufferedReader br;
    File userFile = new File("C:/Users/super/Downloads/users.txt"); // users.txt 파일의 경로를 나타내는 File 객체 생성

    // 한 줄씩 읽은 데이터를 저장할 변수
    String readData;
    StringTokenizer st;

    @Override
    public void insertData() {
        if (!userFile.exists()) {
            System.out.println("파일이 존재하지 않습니다: " + userFile.getAbsolutePath());
            return;
        }

        if (!userFile.canRead()) {
            System.out.println("파일 읽기 권한이 없습니다: " + userFile.getAbsolutePath());
            return;
        }
        try {
            //DB 연결
            con = DriverManager.getConnection(DB_URL, DB_ID, DB_PASSWORD);
            br = new BufferedReader(new FileReader(userFile));

            //파일을 한 줄씩 읽어 들이기
            while ((readData = br.readLine()) != null) {

                //을 기준으로 데이터를 분리
                st = new StringTokenizer(readData, "//");

                // 첫번째 토큰을 id로 저장
                String id = st.nextToken();
                // id 20자 넘을 시 오류 메시지 출력 및 삽입 중지
                if (id.length() > 20) {
                    System.out.println("ID 길이 20자가 넘는 데이터가 있습니다.");
                    continue;
                }
                // 두번째 토큰을 password로 저장
                String password = st.nextToken();
                // password 20자 넘을 시 오류 메시지 출력 및 삽입 중지
                if (password.length() > 20) {
                    System.out.println("Password 길이 20자가 넘는 데이터가 있습니다.");
                    continue;
                }

                // SQL 쿼리 준비 및 실행
                sql = "INSERT INTO user (id, password) VALUES (?, ?)";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, id);
                stmt.setString(2, password);

                // 삽입 성공 여부 확인용
                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new user was inserted successfully!");
                }

                stmt.close();
            }
        }catch (IOException e) {
            System.out.println("파일 읽기 오류: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("삽입 오류: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println("리소스를 닫는 중 오류 발생: " + e.getMessage());
            }
        }
    }
}