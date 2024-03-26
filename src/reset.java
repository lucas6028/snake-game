package Java軟體實務.Snake;

public class reset {
    public static void main(String[] args) throws Exception {

    }
    private void reset() {
        score = 0;
        if (snake != null) {
        snake.getSnakeBody().clear();
        }
        allowKeyPress = true;
        direction = "Right";
        snake = new Snake();
        fruit = newFruit();
        setTimer();
    }
}
