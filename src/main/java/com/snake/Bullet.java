package com.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

public class Bullet {
    private int bulletX = 0;
    private int bulletY = 0;
    private int speed = 10;
    private Timer bt;

    public Bullet(int CELL_SIZE, String direction) {
        if (direction.equals("Left")) {
            bulletX -= CELL_SIZE; // left, x -= CELL_SIZE
        }
        else if (direction.equals("Right")) {
            bulletX += CELL_SIZE; // right, x += CELL_SIZE
        }
        else if (direction.equals("Up")) {
            bulletY -= CELL_SIZE; // up, y -= CELL_SIZE
        }
        else if (direction.equals("Down")) {
            bulletY += CELL_SIZE; // down, y += CELL_SIZE
        }
    }
    public void drawBullet(Graphics g, Node head, int CELL_SIZE) {
        setPosition(head.x, head.y);

        g.setColor(Color.YELLOW);
        g.fillOval(head.x, head.y, CELL_SIZE, CELL_SIZE); 
    }
    public void update(int CELL_SIZE, String direction) {
        // if (direction.equals("Left")) {
        //     bulletX -= CELL_SIZE; // left, x -= CELL_SIZE
        // }
        // else if (direction.equals("Right")) {
        //     bulletX += CELL_SIZE; // right, x += CELL_SIZE
        // }
        // else if (direction.equals("Up")) {
        //     bulletY -= CELL_SIZE; // up, y -= CELL_SIZE
        // }
        // else if (direction.equals("Down")) {
        //     bulletY += CELL_SIZE; // down, y += CELL_SIZE
        // }
    }
    private void setPosition(int x, int y) {
        this.bulletX = x;
        this.bulletY = y;
    }
    public void setBulletTimer() {
        bt = new Timer();
        bt.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

            }
        }, 0, speed);
    }
    
}
