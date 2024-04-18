package com.snake;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Bomb extends Fruit {
    private int x, y;
    
    public Bomb() {
        // img = ImageLoader.loadImageIconFromResource(ImageLoader.bombImage);    
        img = null;
        this.x = (int) (Math.floor(Math.random() * ContainerPanel.column) * ContainerPanel.CELL_SIZE + ContainerPanel.leftBorder);
        this.y = (int) (Math.floor(Math.random() * ContainerPanel.row) * ContainerPanel.CELL_SIZE + ContainerPanel.topBorder); 
    }


    public boolean touchBomb(Snake s) {
        ArrayList<Node> snake_body = s.getSnakeBody();

        for (int i = 0; i < s.getSnakeBody().size(); ++i) {
            if (Math.abs(snake_body.get(i).x - this.x) <= (ContainerPanel.CELL_SIZE / 2) && 
                Math.abs(snake_body.get(i).y - this.y) <= (ContainerPanel.CELL_SIZE / 2)) {
                    return true;
                }
        }
        return false;
    }

    @Override
    public void drawFruit(Graphics g) {
        if (img != null) {
            img.paintIcon(null, g, this.x, this.y);
        }
        else {
            g.setColor(Color.RED);
            g.fillOval(this.x, this.y, ContainerPanel.CELL_SIZE, ContainerPanel.CELL_SIZE);
        }
    }
    
    
    public void setNewLocation(Snake s, Fruit fruit) {
        int new_x = (int) (Math.floor(Math.random() * ContainerPanel.column) * ContainerPanel.CELL_SIZE + ContainerPanel.leftBorder);
        int new_y = (int) (Math.floor(Math.random() * ContainerPanel.row) * ContainerPanel.CELL_SIZE + ContainerPanel.topBorder);
        boolean overlapping = check_overlap(new_x, new_y, s, fruit);

        while (overlapping) { 
            new_x = (int) (Math.floor(Math.random() * ContainerPanel.column) * ContainerPanel.CELL_SIZE + ContainerPanel.leftBorder);
            new_y = (int) (Math.floor(Math.random() * ContainerPanel.row) * ContainerPanel.CELL_SIZE + ContainerPanel.topBorder);
            overlapping = check_overlap(new_x, new_y, s, fruit);
        }

        this.x = new_x;
        this.y = new_y; 
    }

    public boolean check_overlap(int x, int y, Snake s, Fruit fruit) {
        ArrayList<Node> snake_body = s.getSnakeBody();

        for (int j = 0; j < s.getSnakeBody().size(); ++j) {
            if (x == snake_body.get(j).x &&
                y == snake_body.get(j).y) {
                    return true;
                }
            else if (x == fruit.getX() && y == fruit.getY()) {
                return true;
            }
        }
        return false;
    }

    public void setX(int n) {
        this.x = n;
    }

    public void setY(int n) {
        this.y = n;
    }
}
