package com.snake;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Fruit {
    private int x, y;
    public ImageIcon img;
    private int randomFruit = -1;
    
    public Fruit() {
        randomFrutits();
        // img = ImageLoader.loadImageIconFromResource(ImageLoader.fruitImage);    
        // img = null;
        this.x = (int) (Math.floor(Math.random() * (ContainerPanel.column - 1)) * ContainerPanel.CELL_SIZE + ContainerPanel.leftBorder);
        this.y = (int) (Math.floor(Math.random() * (ContainerPanel.row - 1)) * ContainerPanel.CELL_SIZE + ContainerPanel.topBorder); 
    }
    
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void drawFruit(Graphics g) {
        if (img != null) {
            // randomFrutits();
            img.paintIcon(null, g, this.x, this.y);
        }
        else {
            g.setColor(Color.GREEN);
            g.fillOval(this.x, this.y, ContainerPanel.CELL_SIZE, ContainerPanel.CELL_SIZE);
        }
    }

    public void setNewLocation(Snake s, Bomb bomb) {
        int new_x = (int) (Math.floor(Math.random() * (ContainerPanel.column - 1)) * ContainerPanel.CELL_SIZE + ContainerPanel.leftBorder);
        int new_y = (int) (Math.floor(Math.random() * (ContainerPanel.row - 1)) * ContainerPanel.CELL_SIZE + ContainerPanel.topBorder);
        boolean overlapping = check_overlap(new_x, new_y, s, bomb);

        while (overlapping) { 
            new_x = (int) (Math.floor(Math.random() * (ContainerPanel.column - 1)) * ContainerPanel.CELL_SIZE + ContainerPanel.leftBorder);
            new_y = (int) (Math.floor(Math.random() * (ContainerPanel.row - 1)) * ContainerPanel.CELL_SIZE + ContainerPanel.topBorder);
            overlapping = check_overlap(new_x, new_y, s, bomb);
        }

        this.x = new_x;
        this.y = new_y; 
        randomFrutits();
    }

    public boolean check_overlap(int x, int y, Snake s, Bomb bomb) {
        ArrayList<Node> snake_body = s.getSnakeBody();

        for (int i = 0; i < s.getSnakeBody().size(); ++i) {
            for (int px = 0; px < ContainerPanel.CELL_SIZE * 2; ++px) {
                for (int py = 0; py < ContainerPanel.CELL_SIZE * 2; ++py) {
                    if (x + px == snake_body.get(i).x && y == snake_body.get(i).y )
                        return true;
                    else if (x + px == bomb.getX() && y + py == bomb.getY())
                        return true;
                }
            }
        }
        return false;
    } 

    private void randomFrutits() {
        Random rand = new Random();
        int tmp = rand.nextInt(3);
        while (randomFruit == tmp) tmp = rand.nextInt(3);
        randomFruit = tmp;
        
        switch (randomFruit) {
            case 0:
                img = ImageLoader.loadImageIconFromResource(ImageLoader.appleImage);
                break;
            case 1:
                img = ImageLoader.loadImageIconFromResource(ImageLoader.orangeImage);
                break;
            case 2:
                img = ImageLoader.loadImageIconFromResource(ImageLoader.chickenLegImage);
                break;
        }

    }
}

