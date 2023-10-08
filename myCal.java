package RegestrationForm.calculator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class myCal extends JFrame implements ActionListener {
    JTextField displayArea;
    JButton[] numButtons = new JButton[10];
    JButton[] funcButtons = new JButton[9];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, negButton, equButton, dltButton, clrButton;
    Font myFont = new Font("Ink Free", Font.BOLD, 20);
    double num = 0, result = 0;
    char operator = ' ';
    int opcount = 0;
    double repeq = 0;
    JPanel panel;

    public myCal() {
        setSize(370, 575);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("My Calculator");
        setLayout(null);
        setResizable(false);

        displayArea = new JTextField();
        displayArea.setBounds(25, 25, 300, 50);
        displayArea.setFont(myFont);
        displayArea.setEditable(false);
        add(displayArea);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        negButton = new JButton("+/-");
        equButton = new JButton("=");
        clrButton = new JButton("clr");
        dltButton = new JButton("dlt");

        funcButtons[0] = addButton;
        funcButtons[1] = subButton;
        funcButtons[2] = mulButton;
        funcButtons[3] = divButton;
        funcButtons[4] = decButton;
        funcButtons[5] = negButton;
        funcButtons[6] = equButton;
        funcButtons[7] = dltButton;
        funcButtons[8] = clrButton;

        for (int i = 0; i < funcButtons.length; i++) {
            funcButtons[i].setFont(myFont);
            funcButtons[i].setFocusable(false);
            funcButtons[i].addActionListener(this);
        }
        for (int i = 0; i < numButtons.length; i++) {
            numButtons[i] = new JButton(Integer.toString(i));
            numButtons[i].setFont(myFont);
            numButtons[i].setFocusable(false);
            numButtons[i].addActionListener(this);
            numButtons[i].setBackground(new Color(169, 169, 169));
        }

        panel = new JPanel(new GridLayout(4, 4, 10, 10));
        panel.setBounds(25, 145, 300, 300);
        panel.add(numButtons[1]);
        panel.add(numButtons[2]);
        panel.add(numButtons[3]);
        panel.add(addButton);
        panel.add(numButtons[4]);
        panel.add(numButtons[5]);
        panel.add(numButtons[6]);
        panel.add(subButton);
        panel.add(numButtons[7]);
        panel.add(numButtons[8]);
        panel.add(numButtons[9]);
        panel.add(mulButton);
        panel.add(negButton);
        panel.add(numButtons[0]);
        panel.add(decButton);
        panel.add(divButton);
        add(panel);

        clrButton.setBounds(25, 85, 150, 50);
        // clrButton.setBackground(new Color(242, 93, 82));
        add(clrButton);
        dltButton.setBounds(175, 85, 150, 50);
        add(dltButton);
        equButton.setBounds(25, 455, 300, 50);
        add(equButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (JButton num : numButtons) {
            if (e.getSource() == num) {
                displayArea.setText(displayArea.getText().concat(num.getText()));
                break;
            }
        }
        if (e.getSource() == clrButton) {
            displayArea.setText("");
            num = 0;
            result = 0;
            operator = ' ';
            opcount = 0;
            setButtonStates(true);
        }
        if (e.getSource() == dltButton) {
            String str = displayArea.getText();
            if (str.length() == 0) {
                return;
            }
            displayArea.setText("");
            for (int i = 0; i < str.length() - 1; i++) {
                displayArea.setText(displayArea.getText() + str.charAt(i));
            }
        }
        if (e.getSource() == decButton) {
            String str = displayArea.getText();
            if (str.length() == 0) {
                return;
            }
            if (str.contains(".")) {
                if (str.charAt(str.length() - 1) == '.') {
                    displayArea.setText(str.substring(0, str.lastIndexOf('.')));
                }
                // else{
                // displayArea.setText(str.substring(0,
                // str.lastIndexOf('.'))+str.substring(str.lastIndexOf('.')+1, str.length()));
                // }
            } else {
                displayArea.setText(str.concat("."));
            }
        }
        if (e.getSource() == negButton) {
            String str = displayArea.getText();
            if (str.length() == 0) {
                return;
            }
            double temp = Double.parseDouble(displayArea.getText());
            temp *= -1;
            displayArea.setText(String.valueOf(temp));
        }
        if (e.getSource() == addButton) {
            String str = displayArea.getText();
            if (str.length() == 0) {
                return;
            }
            opcount++;
            if (opcount > 1) {
                performOperation();
            } else {
                num = Double.parseDouble(str);
            }
            operator = '+';
            displayArea.setText("");
        }
        if (e.getSource() == subButton) {
            String str = displayArea.getText();
            if (str.length() == 0) {
                return;
            }
            opcount++;
            if (opcount > 1) {
                performOperation();
            } else {
                num = Double.parseDouble(str);
            }
            operator = '-';
            displayArea.setText("");
        }
        if (e.getSource() == mulButton) {
            String str = displayArea.getText();
            if (str.length() == 0) {
                return;
            }
            opcount++;
            if (opcount > 1) {
                performOperation();
            } else {
                num = Double.parseDouble(str);
            }
            operator = '*';
            displayArea.setText("");
        }
        if (e.getSource() == divButton) {
            String str = displayArea.getText();
            if (str.length() == 0) {
                return;
            }
            opcount++;
            if (opcount > 1) {
                performOperation();
            } else {
                num = Double.parseDouble(str);
            }
            operator = '/';
            displayArea.setText("");
        }
        if (e.getSource() == equButton) {
            String str = displayArea.getText();
            if (str.length() == 0) {
                return;
            }
            if (opcount == 0) {
                switch (operator) {
                    case '+':
                        result = num + repeq;
                        break;
                    case '-':
                        result = num - repeq;
                        break;
                    case '*':
                        result = num * repeq;
                        break;
                    case '/':
                        result = num / repeq;
                        break;
                    case ' ':
                        result = repeq;
                        break;
                }
            } else {
                double tempnum = Double.parseDouble(str);
                repeq = tempnum;
                switch (operator) {
                    case '+':
                        result = num + tempnum;
                        break;
                    case '-':
                        result = num - tempnum;
                        break;
                    case '*':
                        result = num * tempnum;
                        break;
                    case '/':
                        if (tempnum == 0) {
                            displayArea.setText("Error... Please click 'clr'");
                            setButtonStates(false);
                            return;
                        } else {
                            result = num / tempnum;
                        }
                        break;
                    case ' ':
                        result = tempnum;
                        break;
                }
            }
            num = result;
            opcount = 0;
            displayArea.setText(String.valueOf(result));
        }
    }

    public void performOperation() {
        String str = displayArea.getText();
        switch (operator) {
            case '+':
                num = num + Double.parseDouble(str);
                break;
            case '-':
                num = num - Double.parseDouble(str);
                break;
            case '*':
                num = num * Double.parseDouble(str);
                break;
            case '/':
                if (Double.parseDouble(str) == 0) {
                    displayArea.setText("Error... Please click 'clr'");
                    setButtonStates(false);
                    return;
                } else {
                    result = num / Double.parseDouble(str);
                }
                break;
        }
    }

    public void setButtonStates(boolean state) {
        for (JButton jButton : funcButtons) {
            jButton.setEnabled(state);
        }
        for (JButton jButton : numButtons) {
            jButton.setEnabled(state);
        }
        clrButton.setEnabled(true);
    }

    public static void main(String[] args) {
        new myCal();
    }
}
