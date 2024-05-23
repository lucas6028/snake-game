package com.snake;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Graphics2D;

public class OpeningScreen extends JPanel {
    ImageIcon singleImage = ImageLoader.loadImageIconFromResource(ImageLoader.singleButtonImage);
    ImageIcon twoImage = ImageLoader.loadImageIconFromResource(ImageLoader.twoButtonImage);
    // JButton buttonSingle = new TransparentButton();
    // JButton buttonTwo = new TransparentButton();
    JButton buttonSingle = new JButton();
    JButton buttonTwo = new JButton();
    ImageIcon backgroundImage = ImageLoader.loadImageIconFromResource(ImageLoader.background);

    public OpeningScreen() {
        // Use null layout for absolute positioning (not recommended)
        this.setLayout(null);

        buttonSingle.setIcon(singleImage);
        // buttonSingle.setIcon(twoImage);
        buttonSingle.setBorder(null);
        buttonSingle.setPreferredSize(new Dimension(150, 150));
        buttonTwo.setIcon(twoImage);
        buttonTwo.setPreferredSize(new Dimension(150, 150));
        buttonTwo.setBorder(null);;
        buttonSingle.setHorizontalTextPosition(JButton.CENTER);
        buttonTwo.setHorizontalTextPosition(JButton.CENTER);

        this.setBackground(Color.BLACK);

        // Set positions and sizes using setBounds
        buttonSingle.setBounds(300, 250, 150, 150); 
        buttonTwo.setBounds(600, 250, 150, 150);

        this.add(buttonSingle);
        this.add(buttonTwo);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(backgroundImage.getImage(), 0, 0, null);
        g2.setFont(new Font("Comic Sans MS", Font.BOLD, 60));
        g2.setColor(Color.ORANGE);
        g2.drawString("Snake Game", 350, 100);
    }
}
