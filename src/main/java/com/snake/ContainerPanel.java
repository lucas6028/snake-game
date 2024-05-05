package com.snake;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ContainerPanel extends JPanel implements KeyListener{
    private CardLayout cardLayout;
    private JPanel openingScreen = new JPanel();

    // Size of frame variables
    public static final int CELL_SIZE = 15;
    public static final int rightBorder = 819;
    public static final int leftBorder = 227;
    public static final int bottomBorder = 607;
    public static final int topBorder = 159;
    public static final int width = rightBorder - leftBorder; // 592
    public static final int height = bottomBorder - topBorder; // 448
    public static final int row = height / CELL_SIZE;
    public static final int column = width / CELL_SIZE;

    // Setting speed variables
    private Timer t;
    private Timer bt;
    // private Timer timerUI;
    private int speed = 50;
    private int level = 1;
    public static JLabel scoreLabel = new JLabel();
    
    public static boolean allowKeyPress = false;
    public static boolean enableB = false;
    public static boolean enableCrossBorder = true;
    public static boolean enableBomb = false;
    public static boolean enableWall = true;
    public boolean enableChangeSpeed = false;
    public boolean shootBulletA = false;
    public boolean shootBulletB = false;
    
    private ImageIcon backgroundImage;
    
    private Node headA;
    private Node headB;
    public static Fruit fruit = new Fruit();
    public static Bomb bomb = new Bomb();
    public static Snake snakeA = new Snake(200);
    public static Snake snakeB = new Snake(300);
    public static ScoreFile file = new ScoreFile();

    public ContainerPanel () {
        // Load the background image
        backgroundImage = ImageLoader.loadImageIconFromResource(ImageLoader.background);
    
        // Set JPanel properties
        // setPreferredSize(new Dimension(backgroundImage.getIconWidth(), backgroundImage.getIconHeight()));
    
        // Switch screen 
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
    
        // openingScreen.setLayout(new BoxLayout(openingScreen, BoxLayout.Y_AXIS));
        JButton buttonSingle = new JButton();
        JButton buttonTwo = new JButton();
        ImageIcon singleImage = ImageLoader.loadImageIconFromResource(ImageLoader.singleButtonImage);
        ImageIcon twoImage = ImageLoader.loadImageIconFromResource(ImageLoader.twoButtonImage);
        buttonSingle.setIcon(singleImage);
        buttonSingle.setPreferredSize(new Dimension(150, 150));
        buttonTwo.setIcon(twoImage);
        buttonTwo.setPreferredSize(new Dimension(150, 150));
        buttonSingle.setHorizontalTextPosition(JButton.CENTER);
        buttonTwo.setHorizontalTextPosition(JButton.CENTER);
        // buttonSingle.setBounds(300, 300, 300, 300);
        openingScreen.setBackground(Color.BLACK);
        openingScreen.add(buttonSingle);
        openingScreen.add(buttonTwo);
    
        buttonSingle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enableB = false;
                switchScreen("Game");
            }
        });
        buttonTwo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enableB = true;
                switchScreen("Game");
            }
        });
    
        // Setting game screen
        JPanel gameScreen = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                // g.fillRect(0, 0, width, height);
                // g.drawImage(backgroundImage.getImage(), 0, 0, null);
    
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
    
                drawBorder(g2);

                if (!enableBomb) {
                    bomb.setX(-100);
                    bomb.setY(-100);
                }
    
                // Snake A
                snakeA.drawSnake(g, true);
                if (snakeA.checkEatFruit(fruit, bomb, g)) {
                        if (enableChangeSpeed) changeSpeed(speed - 3);
                }  
                headA = snakeA.getSnakeBody().get(0);
                if (bomb.touchBomb(snakeA)) {
                    allowKeyPress = false;
                    t.cancel();
                    t.purge();
                    resetUI();
                }
                for(int i = 3 ; i < snakeA.getSnakeBody().size();i++){
                    if (snakeA.getSnakeBody().get(i).x == headA.x && snakeA.getSnakeBody().get(i).y == headA.y){
                        allowKeyPress = false; //game over keyborad can't use
                        t.cancel(); //game over timer t stop
                        t.purge();
                        resetUI();
                    }
                }
                if (snakeA.isCrossBorder() == true && !enableCrossBorder) {
                    allowKeyPress = false; //game over keyborad can't use 
                    t.cancel(); //game over timer t stop
                    t.purge();
                    resetUI();
                }
                snakeA.moveSnake(CELL_SIZE);
                
                // Snake B
                if (enableB) {
                    // snakeB = new Snake(100);
                    snakeB.drawSnake(g, false);
                    if (snakeB.checkEatFruit(fruit, bomb, g)) {
                        if (enableChangeSpeed) changeSpeed(speed - 3);
                    }  
                    
                    headB = snakeB.getSnakeBody().get(0);
                    if (bomb.touchBomb(snakeB)) {
                        allowKeyPress = false;
                        t.cancel();
                        t.purge();
                        resetUI();
                    }
                    for(int i = 3 ; i < snakeB.getSnakeBody().size();i++) {
                        if (snakeB.getSnakeBody().get(i).x == headB.x && snakeB.getSnakeBody().get(i).y == headB.y){
                            allowKeyPress = false; //game over keyborad can't use
                            t.cancel(); //game over timer t stop
                            t.purge();
                            resetUI();
                        }
                        // else if (snakeB.getSnakeBody().get(i).x == headA.x && snakeB.getSnakeBody().get(i).y == headA.y) {
                        //     allowKeyPress = false;
                        //     t.cancel();
                        //     t.purge();
                        //     resetUI();
                        // }
                    }
                    if (snakeB.isCrossBorder() == true && !enableCrossBorder) {
                        allowKeyPress = false; // game over keyborad can't use
                        t.cancel(); //game over timer t stop
                        t.purge();
                        resetUI();
                    }
                    
                    boolean collision = checkCollision(snakeA, snakeB);
                    if (collision) {
                        System.out.println("Collision detected!");
                    }

                    for (int i = 0; i < snakeA.getSnakeBody().size(); ++i) {
                        for (int j = 0; j < snakeB.getSnakeBody().size(); ++j) {
                            if (snakeA.getSnakeBody().get(i).x == snakeB.getSnakeBody().get(j).x &&
                                snakeA.getSnakeBody().get(i).y == snakeB.getSnakeBody().get(j).y) {
                                    System.out.println("touch");
                                    allowKeyPress = false;
                                    t.cancel();
                                    t.purge();
                                    resetUI();
                                }
                        }
                    }

                    if (shootBulletA) {
                        Bullet bullet = new Bullet();
                        bullet.drawBullet(g2, headA, CELL_SIZE);
                        bullet.moveBullet(CELL_SIZE, snakeA.direction);
                        bullet.setBulletTimer();
                        shootBulletA = false;
                    }
                    if (shootBulletB) {
                        Bullet bullet = new Bullet();
                        bullet.drawBullet(g2, headB, CELL_SIZE);
                        bullet.moveBullet(CELL_SIZE, snakeB.direction);
                        bullet.setBulletTimer();
                        shootBulletB = false;
                    }
                    snakeB.moveSnake(CELL_SIZE);
                }
                fruit.drawFruit(g);
                drawStatusBar(g2);
                if (enableBomb) {
                    bomb.drawFruit(g);
                }

                changeLevel(g);
            }
        };
        gameScreen.setBackground(Color.BLACK);

        gameScreen.add(scoreLabel);
    
        // Add screens to the container panel
        this.add(openingScreen, "Opening Screen");
        this.add(gameScreen, "Game");
    
        // Detect the keyboard
        setFocusable(true);
        addKeyListener(this);
        setTimer();
    
        file.read_highest_score();
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

    private void changeSpeed(int newSpeed) {
        speed = newSpeed;
        System.out.println("Speed: " + speed);
        t.cancel();
        setTimer();
    }

    public void switchScreen(String screenName) {
        cardLayout.show(this, screenName);
    }

    public void drawStatusBar(Graphics2D g2) {
        g2.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        if (enableB) {
            g2.setColor(Color.RED);
            g2.fillRect(250, 30, CELL_SIZE, CELL_SIZE);
            g2.setColor(Color.YELLOW);
            g2.drawString("Player 1", 300, 100);
            g2.drawLine(250, 30, CELL_SIZE * 10 + 250, 30);
            return;
        }
        g2.setColor(Color.WHITE);
        g2.drawString("Time: ", 210, 100);
        // g2.drawString("Level: ", 460, 100);
        g2.drawString("Your Score: ", 680, 100);
        
        // Leaderboard
        g2.drawString("Leaderboard", 900, 300);
        g2.drawString("---------------", 870, 330);
        g2.drawString("1.", 900, 360);
        g2.drawString("2." , 900, 390);
        g2.drawString("3." , 900, 420);
        g2.drawString("4." , 900, 450);
        g2.drawString("5." , 900, 480);
        g2.setColor(Color.RED);
        g2.drawString("" + ScoreFile.score, 810, 100);
        g2.drawString("" + ScoreFile.numbers.get(4), 950, 360);
        g2.drawString("" + ScoreFile.numbers.get(3), 950, 390);
        g2.drawString("" + ScoreFile.numbers.get(2), 950, 420);
        g2.drawString("" + ScoreFile.numbers.get(1), 950, 450);
        g2.drawString("" + ScoreFile.numbers.get(0), 950, 480);

        // Level
        int levelX = 390 + (30 * level);
        g2.setColor(Color.GRAY);
        g2.drawString("1", 420, 50);
        g2.drawString("2", 450, 50);
        g2.drawString("3", 480, 50);
        g2.drawString("4", 510, 50);
        g2.drawString("5", 540, 50);

        g2.setColor(Color.YELLOW);
        g2.drawString("" + level, levelX, 50);
    }

    public void changeLevel(Graphics g) {
        int score = ScoreFile.score;
        if (score < 99) {
            level = 1;
        }
        if (score > 99) {
            level = 2;
            if (!enableBomb) {
                bomb.setNewLocation(snakeA, fruit);
                bomb.drawFruit(g);
            }
            enableBomb = true;
        }
        if (score > 199) {
            level = 3;
            enableChangeSpeed = true;
        }
        if (score > 299) {
            level = 4;
            enableCrossBorder = false;
        }
        if (score > 399) {
            level = 5;
        }
    }

    public void drawBorder(Graphics2D g2) {
        for (int i = 0; i < 17; i++) {
            // Adjust the position of each rectangle
            double x = 227.0 - i * 2; // Shift x-coordinate by 2 units per iteration
            double y = 127.0 + i * 2; // Shift y-coordinate downwards by 2 units per iteration

            Rectangle2D.Double rect = new Rectangle2D.Double(x, y, 624, 480);

            switch(level) {
                case 1:
                    g2.setColor(Color.BLUE);
                    break;
                case 2:
                    g2.setColor(Color.GREEN);
                    break;
                case 3:
                    g2.setColor(Color.MAGENTA);
                    break;
                case 4:
                    g2.setColor(Color.DARK_GRAY);
                    break;
                case 5:
                    g2.setColor(Color.BLACK);
                    break;
                default:
                    g2.setColor(Color.BLUE);
                }
            g2.draw(rect);
        }
    }

    public static boolean checkCollision(Snake snakeA, Snake snakeB) {
        // Check for head collision
        Node headA = snakeA.getSnakeBody().get(0);
        Node headB = snakeB.getSnakeBody().get(0);
        if (headA.x == headB.x && headA.y == headB.y) {
            return true; // Collision detected at the heads
        }

        // Check for body collision
        for (int i = 1; i < snakeA.getSnakeBody().size(); i++) {
            Node nodeA = snakeA.getSnakeBody().get(i);
            for (int j = 0; j < snakeB.getSnakeBody().size(); j++) {
                Node nodeB = snakeB.getSnakeBody().get(j);
                if (nodeA.x == nodeB.x && nodeA.y == nodeB.y) {
                    return true; // Collision detected at the bodies
                }
            }
        }

        // No collision detected
        return false;
    }
        
    private void reset() {
        ScoreFile.score = 0;
        enableCrossBorder = true;
        enableBomb = false;
        enableChangeSpeed = false;
        speed = 50;
        if (snakeA != null) {
            snakeA.getSnakeBody().clear();
        }
        if (snakeB != null) {
            snakeB.getSnakeBody().clear();
        }
        allowKeyPress = true;
        snakeA = new Snake(200);
        if (enableB) {
            snakeB = new Snake(300);
        }
        setTimer();
        fruit.setNewLocation(snakeA, bomb);
        if (enableBomb) {
            bomb.setNewLocation(snakeA, fruit);
        }
    }

    public void resetUI() {
        int responese = JOptionPane.showOptionDialog(
            this,//1.parent container component
            "Game Over!! Your score is "
            + (ScoreFile.score)
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

    // Move the snake by Keyboard 
    @Override
    public void keyPressed(KeyEvent e) {
        allowKeyPress = true;
        // System.out.println("keyPressed");
        snakeA.changeDirection(e, allowKeyPress, true);
        if (enableB) {
            snakeB.changeDirection(e, allowKeyPress, false);
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }
}