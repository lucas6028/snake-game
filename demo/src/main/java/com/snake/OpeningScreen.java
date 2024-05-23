package com.snake;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

public class OpeningScreen extends JPanel {

  ImageIcon singleImage = ImageLoader.loadImageIconFromResource(ImageLoader.singleButtonImage);
  ImageIcon twoImage = ImageLoader.loadImageIconFromResource(ImageLoader.twoButtonImage);
  JButton buttonSingle = new JButton();
  JButton buttonTwo = new JButton();

  public OpeningScreen() {
    // Use null layout for absolute positioning (not recommended)
    this.setLayout(null);

    buttonSingle.setIcon(singleImage);
    buttonSingle.setPreferredSize(new Dimension(150, 150));
    buttonTwo.setIcon(twoImage);
    buttonTwo.setPreferredSize(new Dimension(150, 150));
    buttonSingle.setHorizontalTextPosition(JButton.CENTER);
    buttonTwo.setHorizontalTextPosition(JButton.CENTER);

    this.setBackground(Color.BLACK);

    // Set positions and sizes using setBounds
    buttonSingle.setBounds(300, 250, 150, 150); 
    buttonTwo.setBounds(600, 250, 150, 150);

    this.add(buttonSingle);
    this.add(buttonTwo);
  }
}
