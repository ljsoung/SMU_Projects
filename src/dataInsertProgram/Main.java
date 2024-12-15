package dataInsertProgram;

public class Main {
    public static void main(String[] args) {
        StudentDataInsert studentDataInsert = new StudentDataInsert();
        DepartmentDataInsert departmentDataInsert = new DepartmentDataInsert();
        UserDataInsert userDataInsert = new UserDataInsert();

        studentDataInsert.insertData();
        departmentDataInsert.insertData();
        userDataInsert.insertData();
    }
}
