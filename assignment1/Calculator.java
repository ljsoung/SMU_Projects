package basicProjectII.assignment1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends CalculatorInterface implements ActionListener{

    //숫자 저장용 변수
    private double firstValue;
    private double secondValue;
    private double result;
    private String operator;



    Calculator() {
        super();
        addActionListeners();
    }

    public void addActionListeners(){ // 버튼에 ActionListener 추가
        for (JButton numberBnt : numberBnts) {
            numberBnt.addActionListener(this);
        }
        clearBnt.addActionListener(this);
        enterBnt.addActionListener(this);
        dotBnt.addActionListener(this);
        rootBnt.addActionListener(this);
        addBnt.addActionListener(this);
        subBnt.addActionListener(this);
        mulBnt.addActionListener(this);
        divBnt.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton pressButton = (JButton)e.getSource(); // 버튼의 소스를 가져옴
        String buttonText = pressButton.getText(); // 버튼의 텍스트를 가져옴

        if (buttonText.matches("[0-9]")) { // buttonText가 "0"에서 "9" 사이의 숫자인지 확인
            tf.setText(tf.getText() + buttonText);
        } else if (buttonText.equals("Clear")) {
            tf.setText("");
        } else if (buttonText.equals("Enter")) {
            calculate();
        } else if (buttonText.equals(".")){
            if (!tf.getText().contains(".")) { // tf Text에 .이 없을 때만 가능
                tf.setText(tf.getText() + ".");
            }
        } else if (buttonText.equals("\u221A")){
            firstValue = Double.parseDouble(tf.getText());
            result = Math.sqrt(firstValue);
            tf.setText("" + result);
        } else if (buttonText.equals("+")){
            setFirstValue("+");
        } else if (buttonText.equals("-")){
            setFirstValue("-");
        } else if (buttonText.equals("*")){
            setFirstValue("*");
        } else if (buttonText.equals("/")){
            setFirstValue("/");
        }
    }

    // firstValue과 operator 저장
    private void setFirstValue(String operator){
            firstValue = Double.parseDouble(tf.getText());
            tf.setText("");
            this.operator = operator;
    }

    // 계산 메서드
    private void calculate() {
        secondValue = Double.parseDouble(tf.getText());
        if(operator.equals("+")){ // 더하기 연산
            result = firstValue + secondValue;
        } else if (operator.equals("-")) { // 빼기 연산
            result = firstValue - secondValue;
        } else if (operator.equals("*")) { // 곱셈 연산
            result = firstValue * secondValue;
        } else if (operator.equals("/")){ // 나누기 연산
            if(secondValue == 0){
                tf.setText("Error");
            } else {
                result = firstValue / secondValue;
            }
        }

        // 정수인지 실수인지에 따라 출력 형식 결정
        if(result == Math.floor(result)) { // 정수이면
            tf.setText(String.format("%.0f", result));
        } else { // 실수이면
            tf.setText(String.valueOf(result));
        }
    }
}
