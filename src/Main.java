import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Graphic UI
public class Main extends JPanel implements KeyListener {
    // Size of frame variables
    public static final int CELL_SIZE = 20;
    public static int width = 600;
    public static int height = 600;
    public static int row = height / CELL_SIZE;
    public static int column = width / CELL_SIZE;

    // Setting speed variables
    private Timer t;
    private int speed = 100;
    
    public static boolean allowKeyPress = true;

    // can be moved to the body of paintComponent()
    public static Fruit fruit = new Fruit();
    public static Snake snakeA = new Snake(0);
    public static Snake snakeB = new Snake(100);

    public static ScoreFile file = new ScoreFile();

    private Node headA;
    private Node headB;

    private ImageIcon backgroundImage;

    private static final String background = "../images/background/navy_blue.jpg";

    public Main() {
        // Load the background image
        backgroundImage = new ImageIcon(background);

        // Set JPanel properties
        setPreferredSize(new Dimension(backgroundImage.getIconWidth(), backgroundImage.getIconHeight()));

        // Detect the keyboard
        setFocusable(true);
        addKeyListener(this);
        setTimer();

        file.read_hightes_score();
        
        if (!allowKeyPress) {
            resetUI();
        }
    }

    // Setting speed
    private void setTimer() {
        t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        }, 0, speed);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.fillRect(0, 0, width, height);
        g.drawImage(backgroundImage.getImage(), 0, 0, null);

        // fruit.drawFruit(g);
        snakeA.drawSnake(g, true);
        snakeB.drawSnake(g, false);
        fruit.drawFruit(g);
        
        snakeB.checkEatFruit(fruit, g);
        snakeA.checkEatFruit(fruit, g);
        
        headA = snakeA.getSnakeBody().get(0);
        headB = snakeB.getSnakeBody().get(0);

        for(int i = 3 ; i < snakeA.getSnakeBody().size();i++){
            if (snakeA.getSnakeBody().get(i).x == headA.x && snakeA.getSnakeBody().get(i).y == headA.y){
    
                allowKeyPress = false; //game over keyborad can't use
    
                t.cancel(); //game over timer t stop
                t.purge();

                resetUI();
            }
        }
    
        for(int i = 3 ; i < snakeB.getSnakeBody().size();i++) {
            if (snakeB.getSnakeBody().get(i).x == headB.x && snakeB.getSnakeBody().get(i).y == headB.y){
    
                allowKeyPress = false; //game over keyborad can't use
    
                t.cancel(); //game over timer t stop
                t.purge();

                resetUI();
            }
        }
        snakeA.moveSnake(CELL_SIZE);
        snakeB.moveSnake(CELL_SIZE);
        
    }
    
    private void reset() {
        // highest_score = 0;
        ScoreFile.score = 0;
        if (snakeA != null) {
            snakeA.getSnakeBody().clear();
        }
        if (snakeB != null) {
            snakeB.getSnakeBody().clear();
        }
        allowKeyPress = true;
        snakeA = new Snake(0);
        snakeB = new Snake(100);
        setTimer();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    public static void main(String[] args) {
        // Graphic UI
        JFrame window = new JFrame("Snake Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(new Main());
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setResizable(false);
    }
    
    // Move the snake by Keyboard 
    @Override
    public void keyPressed(KeyEvent e) {
        // System.out.println("keyPressed");
        snakeA.changeDirection(e, allowKeyPress, true);
        snakeB.changeDirection(e, allowKeyPress, false);
    }

    public void resetUI() {
        int responese = JOptionPane.showOptionDialog(
            this,//1.parent container component
            "Game Over!! Your score is "
            + ScoreFile.score
            + ". The highest score was "
            + ScoreFile.highest_score
            + ". Would you like to start over?",//2.Set message to display
            
            "Game over",//3.Set message title to display
            JOptionPane.YES_NO_OPTION,//4.Set display option type
            JOptionPane.INFORMATION_MESSAGE,//5.Set message type to display
            null,//6.Customize patterns
            null,//7.Set button text
            JOptionPane.YES_OPTION);  //8.Set default button
            // When snake eat itself
            
        //write score
        file.write_a_file(ScoreFile.score);

        //option execution
        switch (responese){
            case JOptionPane.CLOSED_OPTION:
                System.exit(0);
                break;
            case JOptionPane.NO_OPTION:
                System.exit(0);
                break;
            case JOptionPane.YES_OPTION:
                reset();
                return;
        }
    }

    // Additional Code by VS code
    @Override
    public void keyTyped(KeyEvent e) {
        // System.out.println("keyTyped");
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // System.out.println("keyReleased");
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }
}