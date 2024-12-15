package basicProjectII.secondProject;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ClientGui extends JFrame {

    public CardLayout cardLayout;
    public JPanel cardPanel;

    public ClientGui() {
        setTitle("프로젝트2_임지성");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setResizable(false);
        setLocationRelativeTo(null); // 프레임을 화면 중앙에 배치

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        SelectLocalPanel selectLocalPanel = new SelectLocalPanel();
        ProvideWeatherPanel provideWeatherPanel = new ProvideWeatherPanel();

        cardPanel.add(selectLocalPanel, "SelectLocalPanel");
        cardPanel.add(provideWeatherPanel, "ProvideWeatherPanel");

        add(cardPanel);
        cardLayout.show(cardPanel, "SelectLocalPanel");

        setVisible(true);
    }
}

class SelectLocalPanel extends JPanel {

    //SelectLocalPanel에 올라갈 컴포넌트들
    JLabel label = new JLabel("실시간 날씨 제공 시스템");
    JComboBox<String> level1ComboBox = new JComboBox<>();
    JComboBox<String> level2ComboBox = new JComboBox<>();
    JButton inquiryButton = new JButton("날씨 조회");
    JButton exitButton = new JButton("프로그램 종료");
    public SelectLocalPanel() {
        setLayout(null);
        // JLabel 설정
        label.setBounds(50, 50, 300, 50); // (x, y, width, height)
        label.setFont(new Font("굴림", Font.BOLD, 18));

        // 첫 번째 JCheckBox 설정
        level1ComboBox.setBounds(100, 150, 200, 40);
        level1ComboBox.addItem("지역을 선택해주세요"); // 초기 값 추가

        // 데이터베이스에서 level1 데이터를 가져와 JComboBox에 추가
        List<String> level1Data = DatabaseConnectionManager.getLevel1Data();
        for (String data : level1Data) {
            level1ComboBox.addItem(data); // 가져온 데이터 추가
        }

        // 두 번째 JCheckBox 설정
        level2ComboBox.setBounds(100, 220, 200, 40);

        // level1ComboBox 액션 리스너 추가
        level1ComboBox.addActionListener(e -> {
            String selectedLevel1 = (String) level1ComboBox.getSelectedItem();
            if (!"지역을 선택해주세요".equals(selectedLevel1)) {
                // 선택된 level1에 따른 level2 데이터 가져오기
                List<String> level2Data = DatabaseConnectionManager.getLevel2Data(selectedLevel1);

                // level2ComboBox 초기화 후 새 데이터 추가
                level2ComboBox.removeAllItems();
                level2ComboBox.addItem("세부 지역을 선택해주세요");
                for (String data : level2Data) {
                    level2ComboBox.addItem(data);
                }
            } else {
                // 초기 상태로 되돌리기
                level2ComboBox.removeAllItems();
                level2ComboBox.addItem("세부 지역을 선택해주세요");
            }
        });

        // 조회 버튼 설정
        inquiryButton.setBounds(100, 290, 200, 40);

        // 종료 버튼 설정
        exitButton.setBounds(100, 360, 200, 40);

        // 패널에 컴포넌트 추가
        add(label);
        add(level1ComboBox);
        add(level2ComboBox);
        add(inquiryButton);
        add(exitButton);
    }
}

class ProvideWeatherPanel extends JPanel {
    public ProvideWeatherPanel() {
        setLayout(null);
    }
}