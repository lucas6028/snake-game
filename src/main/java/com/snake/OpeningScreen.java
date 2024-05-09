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
        // openingScreen.setLayout(new BoxLayout(openingScreen, BoxLayout.Y_AXIS));
        buttonSingle.setIcon(singleImage);
        buttonSingle.setPreferredSize(new Dimension(150, 150));
        buttonTwo.setIcon(twoImage);
        buttonTwo.setPreferredSize(new Dimension(150, 150));
        buttonSingle.setHorizontalTextPosition(JButton.CENTER);
        buttonTwo.setHorizontalTextPosition(JButton.CENTER);
        // buttonSingle.setBounds(300, 300, 300, 300);
        this.setBackground(Color.BLACK);
        this.add(buttonSingle);
        this.add(buttonTwo);
    }
}
