package com.rgb3c.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Panel extends JPanel {



    private Integer plusCell = null;
    private Integer minusCell = null;
    private Integer mulCell = null;
    private Integer divCell = null;

    private final JButton[] numbers = new JButton[10];
    private final Font fontMain = new Font("SanSerif", Font.BOLD, 20);
    private final Font fontCell = new Font("SanSerif", Font.BOLD, 10);
    private final JTextField output = new JTextField("0");
    private final JTextField cell = new JTextField();
    private final JButton backspace = new JButton("C"), equ = new JButton("=");
    private final JButton plus = new JButton("+"), minus = new JButton("-"), mul = new JButton("*"), div = new JButton("/");

    public Panel() {
        setLayout(null);
        setFocusable(true);
        grabFocus();

        //make button

        backspace.setBounds(10, 275, 50, 50);
        backspace.setFont(fontMain);
        add(backspace);

        equ.setBounds(130, 275, 50, 50);
        equ.setFont(fontMain);
        add(equ);

        plus.setBounds(190, 95, 50, 50);
        plus.setFont(fontMain);
        add(plus);

        minus.setBounds(190, 155, 50, 50);
        minus.setFont(fontMain);
        add(minus);

        mul.setBounds(190, 215, 50, 50);
        mul.setFont(fontMain);
        add(mul);

        div.setBounds(190, 275, 50, 50);
        div.setFont(fontMain);
        add(div);

        numbers[0] = new JButton("0");
        numbers[0].setBounds(70, 275, 50, 50);
        numbers[0].setFont(fontMain);
        add(numbers[0]);

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++){
                numbers[y * 3 + x + 1] = new JButton(x * 3 + y + 1 + "");
                numbers[y * 3 + x + 1].setBounds(y * (50 + 10) + 10, (2 - x) * (50 + 10) + 95, 50, 50);
                numbers[y * 3 + x + 1].setFont(fontMain);
                add(numbers[y * 3 + x + 1]);
            }
        }

        output.setBounds(10,35, 230, 50);
        output.setFont(fontMain);
        output.setEditable(false);
        add(output);

        cell.setBounds(10, 10, 230, 20);
        cell.setFont(fontCell);
        cell.setEditable(false);
        add(cell);

        //FocusableFalse
        plus.setFocusable(false);
        minus.setFocusable(false);
        div.setFocusable(false);
        mul.setFocusable(false);
        equ.setFocusable(false);
        backspace.setFocusable(false);
        for (JButton number : numbers) {
            number.setFocusable(false);
        }

        //number
        ActionListener l = (ActionEvent e) -> {
            if (output.getText().length() >= 9) {
                grabFocus();
                return;
            }
            JButton b = (JButton)e.getSource();

            if (Integer.parseInt(output.getText()) == 0) {
                output.setText(b.getText());

            } else {
                output.setText(output.getText() + b.getText());

            }
            output.setFocusable(true);
            grabFocus();
        };

        for (JButton b : numbers) {
            b.addActionListener(l);
        }

        //backspace
        ActionListener backspaceAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                output.setText("0");
                grabFocus();
                cell.setText("");
                setEnabledTrue();
            }
        };
        backspace.addActionListener(backspaceAction);

        //plus
        ActionListener plusAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plusCell = Integer.parseInt(output.getText());
                cell.setText(output.getText() + " +");
                output.setText("0");
                grabFocus();
            }
        };
        plus.addActionListener(plusAction);


        //minus
        ActionListener minusAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                minusCell = Integer.parseInt(output.getText());
                cell.setText(output.getText() + " -");
                output.setText("0");
                grabFocus();
            }
        };
        minus.addActionListener(minusAction);

        //mul
        ActionListener mulAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mulCell = Integer.parseInt(output.getText());
                cell.setText(output.getText() + " *");
                output.setText("0");
                grabFocus();
            }
        };
        mul.addActionListener(mulAction);

        //div
        ActionListener divAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Integer.parseInt(output.getText()) != 0) {
                    divCell = Integer.parseInt(output.getText());
                    cell.setText(output.getText() + " /");
                    output.setText("0");
                    grabFocus();
                }
            }
        };
        div.addActionListener(divAction);

        //equ
        ActionListener equAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (output.getText() == "Overflow") {
                    grabFocus();
                    return;
                }

                if (plusCell == null && minusCell == null && mulCell == null && divCell == null) {
                    grabFocus();
                    return;
                }

                if (plusCell != null) {
                    int first = plusCell;
                    plusCell = null;
                    cell.setText("");
                    int second = Integer.parseInt(output.getText());
                    long result = (long)first + (long)second;
                    if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
                        output.setText("Overflow");
                        grabFocus();
                        setEnabledFalse();

                    } else {
                        output.setText(String.valueOf(result));
                        grabFocus();
                    }
                }

                if (minusCell != null) {
                    int first = minusCell;
                    minusCell = null;
                    cell.setText("");
                    int second = Integer.parseInt(output.getText());
                    long result = (long)first - (long)second;
                    if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
                        output.setText("Overflow");
                        grabFocus();
                        setEnabledFalse();

                    } else {
                        output.setText(String.valueOf(result));
                        grabFocus();
                    }
                }

                if (mulCell != null) {
                    int first = mulCell;
                    mulCell = null;
                    cell.setText("");
                    int second = Integer.parseInt(output.getText());
                    long result = (long)first * (long)second;
                    if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
                        output.setText("Overflow");
                        grabFocus();
                        setEnabledFalse();

                    } else {
                        output.setText(String.valueOf(result));
                        grabFocus();
                    }
                }

                if (divCell != null) {
                    int first = divCell;
                    divCell = null;
                    cell.setText("");
                    int second = Integer.parseInt(output.getText());
                    if (second == 0) {
                        return;
                    }
                    double result = (double)first /(double)second;
                    long result2 = (long)first /(long)second;
                    if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
                        output.setText("Overflow");
                        grabFocus();
                        setEnabledFalse();
                    } else {
                        cell.setText("most correct " + result);
                        output.setText(String.valueOf(result2));
                        grabFocus();
                    }
                }
            }
        };
        equ.addActionListener(equAction);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (output.getText().length() >= 9 || output.getText() == "Overflow") {
                    e.consume();
                    return;
                }

                char symbol = e.getKeyChar();

                if (symbol == (char) 8) {
                    output.setText("0");
                    cell.setText("");
                    setEnabledTrue();
                    return;
                }

                if (symbol == (char) 43) {
                    plus.doClick();
                }

                if (symbol == (char) 45) {
                    minus.doClick();
                }

                if (symbol == (char) 42) {
                    mul.doClick();
                }

                if (symbol == (char) 47) {
                    div.doClick();
                }

                if (symbol == (char) 47) {
                    div.doClick();
                }

                if (symbol == '\n' || symbol == (char) 61) {
                    equ.doClick();
                }

                if (!Character.isDigit(symbol)) {
                    return;
                }

                if (Integer.parseInt(output.getText()) == 0) {
                    output.setText(String.valueOf(symbol));
                } else {
                    output.setText(output.getText() + symbol);
                }
                output.setFocusable(true);
                grabFocus();

            }
        });
    }
    private void setEnabledFalse() {
        plus.setEnabled(false);
        minus.setEnabled(false);
        mul.setEnabled(false);
        div.setEnabled(false);
    }

    private void setEnabledTrue() {
        plus.setEnabled(true);
        minus.setEnabled(true);
        mul.setEnabled(true);
        div.setEnabled(true);
    }
}
