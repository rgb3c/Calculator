package com.rgb3c.calculator;

import javax.swing.*;

public class Start {

    private JFrame window;

    public Start() {

        window = new JFrame("Calculator");
        ImageIcon img = new ImageIcon("src/com/rgb3c/calculator/ico/logo.png");
        window.setIconImage(img.getImage());
        window.setSize(265,375);
        window.add(new Panel());
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Start();
            }
        });
    }
}
