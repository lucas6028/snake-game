package com.snake;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Fruit {
    private int x, y;
    public ImageIcon img;
    
    public Fruit() {
        img = ImageLoader.loadImageIconFromResource(ImageLoader.fruitImage);    
        // img = null;
        this.x = (int) (Math.floor(Math.random() * ContainerPanel.column) * ContainerPanel.CELL_SIZE + ContainerPanel.leftBorder);
        this.y = (int) (Math.floor(Math.random() * ContainerPanel.row) * ContainerPanel.CELL_SIZE + ContainerPanel.topBorder); 
    }
    
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void drawFruit(Graphics g) {
        if (img != null) {
            img.paintIcon(null, g, this.x, this.y);
        }
        else {
            g.setColor(Color.GREEN);
            g.fillOval(this.x, this.y, ContainerPanel.CELL_SIZE, ContainerPanel.CELL_SIZE);
        }
    }

    public void setNewLocation(Snake s, Bomb bomb) {
        int new_x = (int) (Math.floor(Math.random() * ContainerPanel.column) * ContainerPanel.CELL_SIZE + ContainerPanel.leftBorder);
        int new_y = (int) (Math.floor(Math.random() * ContainerPanel.row) * ContainerPanel.CELL_SIZE + ContainerPanel.topBorder);
        boolean overlapping = check_overlap(new_x, new_y, s, bomb);

        while (overlapping) { 
            new_x = (int) (Math.floor(Math.random() * ContainerPanel.column) * ContainerPanel.CELL_SIZE + ContainerPanel.leftBorder);
            new_y = (int) (Math.floor(Math.random() * ContainerPanel.row) * ContainerPanel.CELL_SIZE + ContainerPanel.topBorder);
            overlapping = check_overlap(new_x, new_y, s, bomb);
        }

        this.x = new_x;
        this.y = new_y; 
    }

    public boolean check_overlap(int x, int y, Snake s, Bomb bomb) {
        ArrayList<Node> snake_body = s.getSnakeBody();

        for (int j = 0; j < s.getSnakeBody().size(); ++j) {
            if (x == snake_body.get(j).x &&
                y == snake_body.get(j).y) {
                    return true;
                }
            else if (x == bomb.getX() && y == bomb.getY()) {
                return true;
            }
        }
        return false;
    } 
}

