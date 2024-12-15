package studentInformationManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginFrame extends JFrame {

    //로그인 창에 필요한 컴포넌트 생성
    JLabel titleLabel = new JLabel("학생정보관리시스템", SwingConstants.CENTER);
    JLabel idLabel = new JLabel("아이디:");
    JLabel passwordLabel = new JLabel("비밀번호:");
    JTextField idTextField = new JTextField(5);
    JPasswordField passwordField = new JPasswordField(5);
    JButton loginButton = new JButton("로그인");
    JButton signUpButton = new JButton("회원가입");

    JPanel centerPanel = new JPanel();
    JPanel southPanel = new JPanel();

    LoginFrame(){
        setTitle("학생정보관리시스템_임지성");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null); // 프레임을 화면 중앙에 배치
        setLayout(new BorderLayout());


        //최상단 title 부분
        titleLabel.setFont(new Font("굴림", Font.BOLD, 30));
        titleLabel.setSize(200, 30);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 0));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        //centerPanel Layout 설정
        centerPanel.setLayout(null);

        //idLabel
        idLabel.setFont(new Font("굴림", Font.BOLD, 20));
        idLabel.setSize(100, 20);
        idLabel.setLocation(80, 70);
        idLabel.setHorizontalAlignment(JLabel.CENTER);
        centerPanel.add(idLabel);

        //idTextField
        idTextField.setSize(150, 30);
        idTextField.setLocation(170, 65);
        centerPanel.add(idTextField);

        //passwordLabel
        passwordLabel.setFont(new Font("굴림", Font.BOLD, 20));
        passwordLabel.setSize(100, 20);
        passwordLabel.setLocation(69, 100);
        passwordLabel.setHorizontalAlignment(JLabel.CENTER);
        centerPanel.add(passwordLabel);

        //passwordField
        passwordField.setSize(150, 30);
        passwordField.setLocation(170, 95);
        centerPanel.add(passwordField);

        //centerPanel 추가
        add(centerPanel, BorderLayout.CENTER);

        //southPanel Layout 설정
        southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        //loginButton
        loginButton.setFont(new Font("굴림", Font.PLAIN, 20));
        southPanel.add(loginButton);

        //signUpButton
        signUpButton.setFont(new Font("굴림", Font.PLAIN, 20));
        southPanel.add(signUpButton);

        //southPanel 추가
        add(southPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
class Login extends LoginFrame implements ActionListener {

    private String sql;

    // db연결용
    DatabaseConnectionManager db = new DatabaseConnectionManager();
    Connection con = null;
    PreparedStatement stmt = null;

    Login(){
        super();
        addActionListeners();
    }

    public void addActionListeners(){
        loginButton.addActionListener(this);
        signUpButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton pressButton = (JButton)e.getSource(); // 버튼의 소스를 가져옴
        String buttonText = pressButton.getText(); // 버튼의 텍스트를 가져옴

        if(buttonText.equals("로그인")){

            //id와 password 저장할 변수
            String id = idTextField.getText();
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);
            boolean isLoggedIn = false;

            db.getConnection(con);
            try {
                //SQL 쿼리 준비 및 실행
                sql = "SELECT * FROM user WHERE id = ? AND password = ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, id);
                stmt.setString(2, password);

                //로그인 성공 여부 확인
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    isLoggedIn = true;
                }

                if(isLoggedIn){ // 성공 시
                    JOptionPane.showMessageDialog(null, "로그인 성공!", "알림", JOptionPane.INFORMATION_MESSAGE);

                }
                else{ // 실패 시
                    JOptionPane.showMessageDialog(null, "ID 또는 비밀번호가 틀렸습니다.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
                    idTextField = null;
                    passwordField = null;
                }
            } catch (SQLException ex) {
                System.out.println("SQL 오류");
            }
        }
    }
}
