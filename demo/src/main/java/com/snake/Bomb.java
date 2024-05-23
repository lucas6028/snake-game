package com.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Bomb extends Fruit {
    private int x, y;
    private int CELL_SIZE = ContainerPanel.CELL_SIZE;
    private Sounds sounds = new Sounds();
    
    public Bomb(int column, int row, int CELL_SIZE, int leftBorder, int topBorder) {
        img = ImageLoader.loadImageIconFromResource(ImageLoader.bombImage);    
        // img = null;
        this.x = (int) (Math.floor(Math.random() * (column - 1)) * CELL_SIZE + leftBorder);
        this.y = (int) (Math.floor(Math.random() * (row - 1)) * CELL_SIZE + topBorder); 
    }

    public boolean touchBomb(Snake s) {
        ArrayList<Node> snake_body = s.getSnakeBody();

        for (int i = 0; i < s.getSnakeBody().size(); ++i) {
            Node snakeNode = snake_body.get(i);
            if (Math.abs(snakeNode.x - (this.x + CELL_SIZE)) <= CELL_SIZE && 
                Math.abs(snakeNode.y - (this.y + CELL_SIZE)) <= CELL_SIZE) {
                     try {
                        sounds.Boom();
                    } catch (Exception e) {
                        e.getMessage();
                    }
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
    
    public void setNewLocation(Snake s, Fruit fruit, int column, int row, int leftBorder, int topBorder) {
        int new_x = (int) (Math.floor(Math.random() * (column - 1)) * CELL_SIZE + leftBorder);
        int new_y = (int) (Math.floor(Math.random() * (row - 1)) * CELL_SIZE + topBorder);
        boolean overlapping = check_overlap(new_x, new_y, s, fruit);

        while (overlapping) { 
            new_x = (int) (Math.floor(Math.random() * (column - 1)) * CELL_SIZE + leftBorder);
            new_y = (int) (Math.floor(Math.random() * (row - 1)) * CELL_SIZE + topBorder);
            overlapping = check_overlap(new_x, new_y, s, fruit);
        }

        this.x = new_x;
        this.y = new_y; 
    }

    public boolean check_overlap(int x, int y, Snake s, Fruit fruit) {
        ArrayList<Node> snake_body = s.getSnakeBody();

        for (int i = 0; i < s.getSnakeBody().size(); ++i) {
            for (int px = 0; px < ContainerPanel.CELL_SIZE * 2; ++px) {
                for (int py = 0; py < ContainerPanel.CELL_SIZE * 2; ++py) {
                    if (x + px == snake_body.get(i).x && y == snake_body.get(i).y )
                        return true;
                    else if (x + px == fruit.getX() && y + py == fruit.getY())
                        return true;
                }
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
