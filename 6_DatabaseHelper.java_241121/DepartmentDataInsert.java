package dataInsertProgram;

import java.sql.DriverManager;

public class DepartmentDataInsert extends DatabaseHelper {

    String sql; // INSERT 구문을 사용하기 위한 문자열 변수
    String[] departments = {"컴퓨터공학부", "전자공학과", "기계공학과", "건축공학과", "간호학과", "재료공학과", "경영학과", "일어일문학과",
    "산업경영공학과", "체육학과", "교육학과"};

    @Override
    public void insertData() {
        try{
            //DB 연결
            con = DriverManager.getConnection(DB_URL, DB_ID, DB_PASSWORD);

            for(int i = 0; i <= departments.length; i++){
                sql = "INSERT INTO departments (dname) VALUES (?)";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, departments[i]);

                // 삽입 성공 여부 확인용
                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new user was inserted successfully!");
                }

                stmt.close();
                con.close();
            }


        }catch (Exception e){
            System.out.println("삽입 오류...");
        }
    }
}
