import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Snake {
    public int snakeX;
    public int snakeY;
    public String direction = "Right";
    private ArrayList<Node> snakeBody;

    public Snake(int y) {
        snakeBody = new ArrayList<>();
        snakeBody.add(new Node(80, y));
        snakeBody.add(new Node(60, y));
        snakeBody.add(new Node(40, y));
        snakeBody.add(new Node(20, y));
    }

    public ArrayList<Node> getSnakeBody() {
        return this.snakeBody;
    }

    public void drawSnake(Graphics g, boolean isA) {
        // The color of the snake
        for (int i = 0; i < snakeBody.size(); i++) {
            if (i == 0) {
                if (isA) {
                    g.setColor(Color.GREEN);
                }
                else {
                    g.setColor(Color.RED);
                }
            }
            else {
                if (isA) {
                    g.setColor(Color.ORANGE);
                }
                else {
                    g.setColor(Color.BLUE);
                }
            }


            // Handle the border
            Node n = snakeBody.get(i);
            // x-axis
            if (n.x >= Main.width) {
                n.x = 0;
            }
            if (n.x < 0) {
                n.x = Main.width - Main.CELL_SIZE;
            }
            // y-axis
            if (n.y >= Main.height) {
                n.y = 0;
            }
            if (n.y < 0) {
                n.y = Main.height - Main.CELL_SIZE;
            }

            g.fillOval(n.x, n.y, Main.CELL_SIZE, Main.CELL_SIZE);
            
        }
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

        Node newHead = new Node(snakeX, snakeY);

        // Snake move
        // Remove the tail and put it in head
        this.getSnakeBody().remove(getSnakeBody().size() - 1);
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

    public void checkEatFruit(Fruit fruit, Graphics g, int score) {
        // Node newHead = new Node(snakeX, snakeY);
        
        if (this.getSnakeBody().get(0).x == fruit.getX() &&
            this.getSnakeBody().get(0).y == fruit.getY()) {
                // set fruit to new location
                fruit.setNewLocation(this);

                // draw fruit
                fruit.drawFruit(g);

                score++;
            }
        else {
            // getSnakeBody().remove(getSnakeBody().size() - 1);
        }

        // Snake move
        // Remove the tail and put it in head
        // getSnakeBody().add(0, newHead);
    }
}
