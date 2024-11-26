package basicProjectII.assignment1;

import javax.swing.*;
import java.awt.*;

public class CalculatorInterface extends JFrame { // 계산기 인터페이스
    JTextField tf = new JTextField();
    JButton[] numberBnts = new JButton[10];
    JButton clearBnt;
    JButton enterBnt;
    JButton dotBnt;
    JButton rootBnt;
    JButton addBnt;
    JButton subBnt;
    JButton mulBnt;
    JButton divBnt;
    Container firstContainer;
    JPanel secondContainer;
    JPanel thirdContainer;

    CalculatorInterface(){
        setButtons(); // 사용되는 버튼들의 생성
        setContainer(); // 컨테이너들의 배치 및 배치관리자 추가 작업
        setComponents(); // 컴포넌트들을 컨테이너에 올려주는 작업, 기타 세부 세팅

        setTitle("임지성의 계산기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setVisible(true);
    }

    public void setButtons(){
        for(int i = 0; i < 10; i++){
            numberBnts[i] = new JButton("" + i);
             // 객체를 이벤트 리스너로 추가
        }
        // Button 생성
        clearBnt = new JButton("Clear"); //Backspace
        enterBnt = new JButton("Enter");
        dotBnt = new JButton(".");
        rootBnt = new JButton("\u221A");
        addBnt = new JButton("+");
        subBnt = new JButton("-");
        mulBnt = new JButton("*");
        divBnt = new JButton("/");

        // 버튼들에게 Calculator 객체를 이벤트 리스너로 추가

    }

    public void setContainer(){
        //컨테이너 객체 확보 작업
        firstContainer = getContentPane();
        secondContainer = new JPanel();
        thirdContainer = new JPanel();

        //배치관리자 추가 작업
        firstContainer.setLayout(new BorderLayout(5, 5));
        secondContainer.setLayout(new BorderLayout(5, 5));
        thirdContainer.setLayout(new GridLayout(4, 4, 5, 5));

        //컨테이너 적층 작업
        firstContainer.add(secondContainer, BorderLayout.CENTER);
        secondContainer.add(thirdContainer, BorderLayout.CENTER);

        //적층 확인용 배경색 추가
        firstContainer.setBackground(Color.GRAY);
    }

    public void setComponents(){
        firstContainer.add(tf, BorderLayout.NORTH);
        firstContainer.add(enterBnt, BorderLayout.SOUTH);
        secondContainer.add(clearBnt, BorderLayout.NORTH);
        thirdContainer.add(numberBnts[1]);
        thirdContainer.add(numberBnts[2]);
        thirdContainer.add(numberBnts[3]);
        thirdContainer.add(addBnt);
        thirdContainer.add(numberBnts[4]);
        thirdContainer.add(numberBnts[5]);
        thirdContainer.add(numberBnts[6]);
        thirdContainer.add(subBnt);
        thirdContainer.add(numberBnts[7]);
        thirdContainer.add(numberBnts[8]);
        thirdContainer.add(numberBnts[9]);
        thirdContainer.add(mulBnt);
        thirdContainer.add(numberBnts[0]);
        thirdContainer.add(dotBnt);
        thirdContainer.add(rootBnt);
        thirdContainer.add(divBnt);

        //텍스트필드의 옵션 추가 : 폰트 Arial, 볼드체, 크기 30, 우측 정렬
        tf.setFont(new Font("Arial", Font.BOLD, 30));
        tf.setHorizontalAlignment(JTextField.RIGHT);


    }
}
