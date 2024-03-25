import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Scanner;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

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

    public static int highest_score;

    public static boolean allowKeyPress = true;

    // can be moved to the body of paintComponent()
    public static Fruit fruit = new Fruit();
    public static Snake snakeA = new Snake(0);
    public static Snake snakeB = new Snake(350);

    public Main() {
        // Detect the keyboard
        setFocusable(true);
        addKeyListener(this);
        setTimer();
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
        // fruit.drawFruit(g);
        snakeA.drawSnake(g, true);
        snakeB.drawSnake(g, false);
        fruit.drawFruit(g);

        snakeB.checkEatFruit(fruit, g, highest_score);
        snakeA.checkEatFruit(fruit, g, highest_score);

        snakeA.moveSnake(CELL_SIZE);
        snakeB.moveSnake(CELL_SIZE);
    }

    private void reset() {

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
        System.out.println("keyPressed");
        snakeA.changeDirection(e, allowKeyPress, true);
        snakeB.changeDirection(e, allowKeyPress, false);
    }

    public void read_hightes_score() {
        // If the file already exist, read the highest score
        try{
            File myObj = new File("filename.txt");
            Scanner sc = new Scanner(myObj);
            highest_score = sc.nextInt();
            sc.close();
        // If File is not exist, highest score = 0, open new file and save the records
        } catch (FileNotFoundException e){
            highest_score = 0;
            try{
                File myObj = new File("filename.txt");
                if (myObj.createNewFile()){
                    System.out.println("File creater: " + myObj.getName());
                }
                FileWriter fw = new FileWriter(myObj.getName());
                fw.write("" + 0);
                fw.close();
            } catch (IOException err) {
                System.out.println("An error occures");
                err.printStackTrace();
            }
        }
    }
    
    public void write_a_file(int score) {
        // If the score is higher than records, record new score
        try{
            FileWriter fw = new FileWriter("filename.txt");
            if (score > highest_score){
                fw.write("" + score);
                highest_score = score;
        // Keep the original score
            } else {
                fw.write("" + highest_score);
            }
            fw.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        
    }

    // Additional Code by VS code
    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("keyTyped");
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("keyReleased");
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }
}