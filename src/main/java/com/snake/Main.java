package com.snake;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;

// Graphic UI
public class Main {
    private static ContainerPanel containerPanel;

    public Main() {

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
        // Graphic UI
        JFrame window = new JFrame("Snake Game");
        containerPanel = new ContainerPanel();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(100, 5, 1100, 700);
        // window.setSize(width, height);
        window.setContentPane(containerPanel);
        // window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setResizable(false);
    }
}