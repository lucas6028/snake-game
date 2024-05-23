package com.snake;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Snake {
    public int snakeX;
    public int snakeY;
    private int CELL_SIZE = ContainerPanel.CELL_SIZE;
    public String direction = "Right";
    public Node newHead; 
    public Sounds sounds = new Sounds();
    private ArrayList<Node> snakeBody;
    private boolean eatFruit = false;

    public Snake(int y) {
        snakeBody = new ArrayList<>();
        snakeBody.add(new Node(245 , y));
        snakeBody.add(new Node(230, y));
        snakeBody.add(new Node(215, y));
        snakeBody.add(new Node(200, y));
    }

    public ArrayList<Node> getSnakeBody() {
        return this.snakeBody;
    }

    public void drawSnake(Graphics g, boolean isA) {
        // The color of the snake
        for (int i = 0; i < snakeBody.size(); i++) { 
            // Handle the border
            Node n = snakeBody.get(i);
            if (ContainerPanel.enableCrossBorder) {
                // x-axis
                if (n.x >= ContainerPanel.rightBorder) {
                    n.x = ContainerPanel.leftBorder;
                }
                if (n.x < ContainerPanel.leftBorder) {
                    n.x = ContainerPanel.rightBorder - ContainerPanel.CELL_SIZE;
                }
                // y-axis
                if (n.y >= ContainerPanel.bottomBorder) {
                    n.y = ContainerPanel.topBorder;
                }
                if (n.y < ContainerPanel.topBorder) {
                    n.y = ContainerPanel.bottomBorder - ContainerPanel.CELL_SIZE;
                }
            }

            if (i == 0) {
                if (isA) 
                    g.setColor(Color.RED);
                else 
                    g.setColor(Color.GREEN);
                g.drawOval(n.x, n.y, ContainerPanel.CELL_SIZE, ContainerPanel.CELL_SIZE);
            }
            else {
                if (isA) 
                    g.setColor(Color.BLUE);
                else
                    g.setColor(Color.ORANGE);
                g.fillOval(n.x, n.y, ContainerPanel.CELL_SIZE, ContainerPanel.CELL_SIZE);
            }
        }
    }

    public boolean isCrossBorder () {
        Node n = this.getSnakeBody().get(0);
        // x-axis
        if (n.x >= ContainerPanel.rightBorder) {
            return true;
        }
        if (n.x < ContainerPanel.leftBorder) {
            return true;
        }
        // y-axis
        if (n.y >= ContainerPanel.bottomBorder) {
            return true;
        }
        if (n.y < ContainerPanel.topBorder) {
            return true;
        }
        return false;
    }

    public boolean touchOtherSnakes(Snake snakeB) {
        // snakeA's head
        // snakeB's body
        int headX = this.getSnakeBody().get(0).x;
        int headY = this.getSnakeBody().get(0).y;

        for (int i = 1; i < snakeB.getSnakeBody().size(); ++i) {
            if (headX == snakeB.getSnakeBody().get(i).x && 
                headY == snakeB.getSnakeBody().get(i).y) {
                    System.out.println("Snake head touching another snake's body!");
                    return true;
                }
        }
        return false;
    }

    public void moveSnake(int CELL_SIZE) {
        // Move the snake by Keyboard

        snakeX = this.getSnakeBody().get(0).x;
        snakeY = this.getSnakeBody().get(0).y;

        if (direction.equals("Left")) {
            snakeX -= CELL_SIZE; // left, x -= CELL_SIZE
        }
        else if (direction.equals("Right")) {
            snakeX += CELL_SIZE; // right, x += CELL_SIZE
        }
        else if (direction.equals("Up")) {
            snakeY -= CELL_SIZE; // up, y -= CELL_SIZE
        }
        else if (direction.equals("Down")) {
            snakeY += CELL_SIZE; // down, y += CELL_SIZE
        }

        newHead = new Node(snakeX, snakeY);

        // Snake move
        if (!eatFruit) {
            this.getSnakeBody().remove(getSnakeBody().size() - 1);
            eatFruit = false;
        }
        this.getSnakeBody().add(0, newHead);
    }

    public void changeDirection(KeyEvent e, boolean allowKeyPress, boolean byArrowKey) {
        if (!allowKeyPress) {
            return;
        }
        if (byArrowKey) {
            if (e.getKeyCode() == 37 && !direction.equals("Right")) {
                direction = "Left";
            }
            else if (e.getKeyCode() == 39 && !direction.equals("Left")) {
                direction = "Right";
            }
            else if (e.getKeyCode() == 38 && !direction.equals("Down")) {
                direction = "Up";
            }
            else if (e.getKeyCode() == 40 && !direction.equals("Up")) {
                direction = "Down";
            }
            // allowKeyPress = false;
        }
        else {
            if (e.getKeyCode() == 65 && !direction.equals("Right")) {
                direction = "Left";
            }
            else if (e.getKeyCode() == 68 && !direction.equals("Left")) {
                direction = "Right";
            }
            else if (e.getKeyCode() == 87 && !direction.equals("Down")) {
                direction = "Up";
            }
            else if (e.getKeyCode() == 83 && !direction.equals("Up")) {
                direction = "Down";
            }
        }
    }

    public boolean checkShoot(KeyEvent e, boolean allowKeyPress, boolean byArrowKey) {
        if (!allowKeyPress) {
            return false;
        }
        if (byArrowKey) {
            if (e.getKeyCode() == 110)
                return true;
        }
        else {
            if (e.getKeyCode() == 32)
                return true;
        }
        return false;
    }

    public boolean checkEatFruit(Fruit fruit, Bomb bomb, Graphics g) {
        int snakeHeadX = this.getSnakeBody().get(0).x;
        int snakeHeadY = this.getSnakeBody().get(0).y;
        int fruitX = fruit.getX();
        int fruitY = fruit.getY();
        
        if (Math.abs(snakeHeadX - (fruitX + CELL_SIZE)) <= CELL_SIZE && 
            Math.abs(snakeHeadY - (fruitY + CELL_SIZE)) <= CELL_SIZE) {
            System.out.println("Eat the fruit");
            fruit.setNewLocation(this, bomb); // set fruit to new location
            fruit.drawFruit(g);
            
            ScoreFile.score += 10;
            eatFruit = true;
            try {
                sounds.GetPoints();
            } catch (Exception e) {
                e.getMessage();
            }
            return true;
        } else {
            eatFruit = false;
            return false;
        }
    }

    public boolean checkEatItSelf(Node headA) {
        for(int i = 3 ; i < this.getSnakeBody().size();i++){
            if (this.getSnakeBody().get(i).x == headA.x && this.getSnakeBody().get(i).y == headA.y){
                return true;
            }
        }
        return false;
    }
}
