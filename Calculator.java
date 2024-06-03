import java.awt.event.*;
import java.applet.*;
import java.awt.*;

public class Calculator extends Applet implements ActionListener {
    int currentValue = 0, input = 0;
    char lastOperator = ' ';
    TextField t;
    Button[] b = new Button[10];
    Button bPlus, bMinus, bMultiply, bDivide, bEquals, bClear;

    public void init() {
        setLayout(new GridLayout(5, 4));
        t = new TextField(10);
        for (int i = 0; i < 10; i++) {
            b[i] = new Button("" + i);
            b[i].addActionListener(this);
        }
        bPlus = new Button("+");
        bMinus = new Button("-");
        bMultiply = new Button("*");
        bDivide = new Button("/");
        bEquals = new Button("=");
        bClear = new Button("CE");

        bPlus.addActionListener(this);
        bMinus.addActionListener(this);
        bMultiply.addActionListener(this);
        bDivide.addActionListener(this);
        bEquals.addActionListener(this);
        bClear.addActionListener(this);

        add(t);
        for (int i = 0; i < 10; i++) {
            add(b[i]);
        }
        add(bPlus);
        add(bMinus);
        add(bMultiply);
        add(bDivide);
        add(bEquals);
        add(bClear);
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if (Character.isDigit(s.charAt(0))) {
            t.setText(t.getText() + s);
        } else if (s.equals("CE")) {
            t.setText("");
            currentValue = 0;
            lastOperator = ' ';
        } else if (s.equals("=")) {
            calculate(Integer.parseInt(t.getText()));
            t.setText("" + currentValue);
            lastOperator = ' ';
        } else {
            if (!t.getText().equals("")) {
                if (lastOperator != ' ') {
                    calculate(Integer.parseInt(t.getText()));
                } else {
                    currentValue = Integer.parseInt(t.getText());
                }
                t.setText("");
                lastOperator = s.charAt(0);
            }
        }
    }

    private void calculate(int newValue) {
        switch (lastOperator) {
            case '+':
                currentValue += newValue;
                break;
            case '-':
                currentValue -= newValue;
                break;
            case '*':
                currentValue *= newValue;
                break;
            case '/':
                if (newValue != 0) {
                    currentValue /= newValue;
                } else {
                    t.setText("Error");
                    currentValue = 0;
                    lastOperator = ' ';
                }
                break;
        }
    }
}

/*
<applet code="Calc.class" width=300 height=300>
</applet>
*/
