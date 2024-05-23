package com.snake;

import java.awt.*;
import javax.swing.JButton;

public class TransparentButton extends JButton {
    public TransparentButton() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(new Color(0, 0, 128, 128)); // Optional: Pressed button effect
            g.fillRect(0, 0, getWidth(), getHeight());
        }
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.SrcOver.derive(0.0f)); // Set transparency
        super.paintComponent(g2d);
        g2d.dispose();
    }
}
