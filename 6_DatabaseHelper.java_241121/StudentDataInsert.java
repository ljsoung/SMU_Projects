package dataInsertProgram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.DriverManager;
import java.util.StringTokenizer;

public class StudentDataInsert extends DatabaseHelper {

    String sql; // INSERT 구문을 사용하기 위한 문자열 변수
    BufferedReader br;
    File userFile = new File("students.txt"); // users.txt 파일의 경로를 나타내는 File 객체 생성

    // 한 줄씩 읽은 데이터를 저장할 변수
    String readData;
    StringTokenizer st;

    @Override
    public void insertData() {
        try{
            br = new BufferedReader(new FileReader(userFile));
            //파일을 한 줄씩 읽어 들이기
            while((readData = br.readLine()) != null){
                st = new StringTokenizer(readData, " ");

                // 첫번째 토큰을 department로 저장
                String department = st.nextToken();
                // department 30자 넘을 시 오류 메시지 출력 및 삽입 중지
                if(department.length() > 30){
                    System.out.println("학과명 길이 30자가 넘는 데이터가 있습니다.");
                    break;
                }

                // 두번째 토큰을 year로 저장
                int year = Integer.valueOf(st.nextToken()); // 문자열을 정수형으로 변환하여 저장

                // 세번째 토큰을 sname로 저장
                String sname = st.nextToken();
                // sname 20자 넘을 시 오류 메시지 출력 및 삽입 중지
                if(sname.length() > 20){
                    System.out.println("이름 길이 20자가 넘는 데이터가 있습니다.");
                    break;
                }

                // 네번째 토큰을 grade로 저장
                String grade = st.nextToken();
                // grade 20자 넘을 시 오류 메시지 출력 및 삽입 중지
                if(grade.length() > 20){
                    System.out.println("구분 길이 20자가 넘는 데이터가 있습니다.");
                    break;
                }

                // 다섯번째 토큰을 snum로 저장
                String snum = st.nextToken();
                // snum 20자 넘을 시 오류 메시지 출력 및 삽입 중지
                if(snum.length() > 20){
                    System.out.println("학번 길이 20자가 넘는 데이터가 있습니다.");
                    break;
                }

                //DB 연결
                con = DriverManager.getConnection(DB_URL, DB_ID, DB_PASSWORD);

                // SQL 쿼리 준비 및 실행
                sql = "INSERT INTO students (department, year, sname, grade, snum) VALUES (?, ?, ?, ?, ?)";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, department);
                stmt.setInt(2, year);
                stmt.setString(3, sname);
                stmt.setString(4, grade);
                stmt.setString(5, snum);

                // 삽입 성공 여부 확인용
                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new user was inserted successfully!");
                }

                stmt.close();
                con.close();
                br.close();

            }

        }catch (Exception e){
            System.out.println("삽입 오류...");
        }
    }
}
