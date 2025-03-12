import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {
    JFrame frame;
    JTextField textField;
    JButton[] numberButton = new JButton[10];
    JButton[] operatorButton = new JButton[9];
    Font font = new Font("Serif", Font.BOLD, 30);
    Font font2 = new Font("Plain", Font.BOLD, 20);
    JPanel panel = new JPanel();
    JButton addButton, subtractButton, multiplyButton, divideButton;
    JButton decimalButton, equalsButton, deleteButton, clearButton;
    JButton negButton;
    double num1 = 0, num2 = 0;
    Boolean isFirst = true;
    char operator, perviousOperator;

    Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);

        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setFont(font);
        textField.setEditable(false);

        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");
        decimalButton = new JButton(".");
        equalsButton = new JButton("=");
        deleteButton = new JButton("Delete");
        clearButton = new JButton("Clear");
        negButton = new JButton("(-)");

        operatorButton[0] = addButton;
        operatorButton[1] = subtractButton;
        operatorButton[2] = multiplyButton;
        operatorButton[3] = divideButton;
        operatorButton[4] = decimalButton;
        operatorButton[5] = negButton;
        operatorButton[6] = equalsButton;
        operatorButton[7] = deleteButton;
        operatorButton[8] = clearButton;

        for (int i = 0; i < 6; i++) {
            operatorButton[i].addActionListener(this);
            operatorButton[i].setFont(font);
            operatorButton[i].setFocusable(false);
        }
        for (int i = 6; i < 9; i++) {
            operatorButton[i].addActionListener(this);
            operatorButton[i].setFont(font2);
            operatorButton[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            numberButton[i] = new JButton(String.valueOf(i));
            numberButton[i].addActionListener(this);
            numberButton[i].setFont(font);
            numberButton[i].setFocusable(false);
        }

        deleteButton.setBounds(50, 430, 100, 50);
        clearButton.setBounds(150, 430, 100, 50);
        equalsButton.setBounds(250, 430, 100, 50);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        panel.add(numberButton[1]);
        panel.add(numberButton[2]);
        panel.add(numberButton[3]);
        panel.add(addButton);
        panel.add(numberButton[4]);
        panel.add(numberButton[5]);
        panel.add(numberButton[6]);
        panel.add(subtractButton);
        panel.add(numberButton[7]);
        panel.add(numberButton[8]);
        panel.add(numberButton[9]);
        panel.add(multiplyButton);
        panel.add(numberButton[0]);
        panel.add(decimalButton);
        panel.add(negButton);
        panel.add(divideButton);

        frame.add(panel);
        frame.add(deleteButton);
        frame.add(clearButton);
        frame.add(equalsButton);
        frame.add(textField);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButton[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == clearButton) {
            textField.setText("");
            num1 = 0;
            num2 = 0;
            isFirst = true;
        }
        if (e.getSource() == deleteButton) {
            String string = textField.getText();
            if (!string.isEmpty()) {
                textField.setText(string.substring(0, string.length() - 1));
            }
        }
        if (e.getSource() == decimalButton) {
            if (!textField.getText().contains(".")) {
                textField.setText(textField.getText().concat(String.valueOf(".")));
            }
        }
        if (e.getSource() == addButton || e.getSource() == subtractButton || e.getSource() == multiplyButton || e.getSource() == divideButton) {
            if (isFirst) {
                num1 = Double.parseDouble(textField.getText());
                isFirst = false;
            }
            if (!isFirst) {
                num2 = Double.parseDouble(textField.getText());
                mathOperation(operator);
            }

            if (e.getSource() == addButton) operator = '+';
            if (e.getSource() == subtractButton) operator = '-';
            if (e.getSource() == multiplyButton) operator = '*';
            if (e.getSource() == divideButton) operator = '/';

            textField.setText("");
        }
        if (e.getSource() == equalsButton) {
            if (operator == '=') {
                mathOperation(perviousOperator);
                textField.setText(String.valueOf(num1));
            } else {
                perviousOperator = operator;
                operator = '=';
                num2 = Double.parseDouble(textField.getText());
                mathOperation(perviousOperator);
                textField.setText(Double.toString(num1));
            }
        }
    }
    private void mathOperation(char operator) {
        if (operator == '+') {
            num1 += num2;
        }
        if (operator == '-') {
            num1 -= num2;
        }
        if (operator == '*') {
            num1 *= num2;
        }
        if (operator == '/') {
            if (num2 != 0) {
                num1 /= num2;
            } else {
                textField.setText("Błąd");
            }
        }
    }
}
