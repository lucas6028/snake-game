package com.snake;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Stone extends Fruit{
    private int x, y;

    public Stone() {
        // img = ImageLoader.loadImageIconFromResource(ImageLoader.bombImage);    
        img = null;
        this.x = (int) (Math.floor(Math.random() * ContainerPanel.column) * ContainerPanel.CELL_SIZE + ContainerPanel.leftBorder);
        this.y = (int) (Math.floor(Math.random() * ContainerPanel.row) * ContainerPanel.CELL_SIZE + ContainerPanel.topBorder); 
    }

    @Override
    public void drawFruit(Graphics g) {
        if (img != null) {
            img.paintIcon(null, g, this.x, this.y);
        }
        else {
            g.setColor(Color.GRAY);
            g.fillOval(this.x, this.y, ContainerPanel.CELL_SIZE, ContainerPanel.CELL_SIZE);
        }
    }
}
