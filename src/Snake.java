import java.awt.*;
import java.util.ArrayList;

public class Snake {
    private ArrayList<Node> snakeBody;

    public Snake() {
        snakeBody = new ArrayList<>();
        snakeBody.add(new Node(80, 0));
        snakeBody.add(new Node(60, 0));
        snakeBody.add(new Node(40, 0));
        snakeBody.add(new Node(20, 0));
    }

    public ArrayList<Node> getSnakeBody() {
        return snakeBody;
    }

    public void drawSnake(Graphics g) {
        for (int i = 0; i < snakeBody.size(); i++) {
            if (i == 0) {
                g.setColor(Color.GREEN);
            }
            else {
                g.setColor(Color.ORANGE);
            }
        }
    }
}
