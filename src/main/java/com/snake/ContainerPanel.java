package com.snake;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ContainerPanel extends JPanel implements KeyListener{
    private CardLayout cardLayout;

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
    private int speed = 50;
    private int level = 1;
    private static int bloodA = 100;
    private static int bloodB = 100;
    public static JLabel scoreLabel = new JLabel();
    public static Sounds sounds = new Sounds();
    
    public static boolean allowKeyPress = false;
    public static boolean enableB = false;
    public static boolean enableCrossBorder = true;
    public static boolean enableBomb = false;
    public static boolean enableWall = true;
    public static boolean enableChangeSpeed = false;
    public boolean shootBulletA = false;
    public boolean shootBulletB = false;
    public static boolean gameover = false;
    public boolean Awin = false;
    public boolean Bwin = false;
    
    private Node headA;
    private Node headB;
    public static Draw draw;
    public static Fruit fruit = new Fruit();
    public static Bomb bomb = new Bomb(column, row, CELL_SIZE, leftBorder, topBorder);
    public static Snake snakeA = new Snake(200);
    public static Snake snakeB = new Snake(300);
    public static ScoreFile file = new ScoreFile();
    public static ArrayList<Bullet> bulletA = new ArrayList<>();
    public static ArrayList<Bullet> bulletB = new ArrayList<>();

    public ContainerPanel () {
        draw = new Draw(level);
        // Set JPanel properties
        // setPreferredSize(new Dimension(backgroundImage.getIconWidth(), backgroundImage.getIconHeight()));
    
        // Switch screen 
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
    
        // openingScreen.setLayout(new BoxLayout(openingScreen, BoxLayout.Y_AXIS));    
        OpeningScreen menu = new OpeningScreen();
        menu.buttonSingle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    sounds.Click();
                } catch (Exception e1) {
                    e1.getMessage();
                }
                enableB = false;
                switchScreen("Game");
            }
        });
        menu.buttonTwo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    sounds.Click();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
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
                draw.drawBorder(g2);

                if (!enableBomb) {
                    bomb.setX(-100);
                    bomb.setY(-100);
                }
    
                // Snake A
                snakeA.drawSnake(g, true);
                headA = snakeA.getSnakeBody().get(0);
                drawGameOver(g2, gameover, Awin, Bwin);
                if (snakeA.checkEatFruit(fruit, bomb, g)) {
                    bloodA += 10;
                    if (bloodA > 100)
                        bloodA = 100;
                    if (enableChangeSpeed)
                        changeSpeed();
                }  
                if (bomb.touchBomb(snakeA)) {
                    if (enableB) {
                        bloodA -= 10;
                        try {
                            sounds.Hurt();
                        } catch (Exception e) {
                            e.getMessage();
                        }
                    }
                    else
                        gameover = true;
                }
                if (snakeA.checkEatItSelf(headA)) {
                    if (enableB) {
                        bloodA -= 20;
                        try {
                            sounds.Hurt();
                        } catch (Exception e) {
                            e.getMessage();
                        }
                    }
                    else{
                        gameover = true;
                    }
                }
                if (snakeA.isCrossBorder() == true && !enableCrossBorder) {
                    gameover = true;
                }
                snakeA.moveSnake(CELL_SIZE);
                
                // Snake B
                if (enableB) {
                    // snakeB = new Snake(100);
                    drawGameOver(g2, gameover, Awin, Bwin);
                    if (snakeB.checkEatFruit(fruit, bomb, g)) {
                        bloodB += 10;
                        if (bloodB > 100)
                            bloodB = 100;
                        // if (enableChangeSpeed) changeSpeed();
                    }  
                    headB = snakeB.getSnakeBody().get(0);
                    if (bomb.touchBomb(snakeB)) {
                        bloodB -= 10;
                        try {
                            sounds.Hurt();
                        } catch (Exception e) {
                            e.getMessage();
                        }
                    }
                    if (snakeB.checkEatItSelf(headB)) {
                        bloodB -= 20;
                        try {
                            sounds.Hurt();
                        } catch (Exception e) {
                            e.getMessage();
                        }
                    }
                    if (snakeB.isCrossBorder() == true && !enableCrossBorder) {
                        gameover = true;
                    }
                    
                    boolean collision = checkCollision(snakeA, snakeB);
                    if (collision) {
                        System.out.println("Collision detected!");
                    }

                    // add the bullet                        
                    if (shootBulletA) {
                        bulletA.add(new Bullet(CELL_SIZE, snakeA.direction, headA));
                        try {
                            sounds.Gun();
                        } catch (Exception e) {
                            e.getMessage();
                        }
                        shootBulletA = false;
                    }
                    if (shootBulletB) {
                        bulletB.add(new Bullet(CELL_SIZE, snakeB.direction, headB));
                        try {
                            sounds.Gun();
                        } catch (Exception e) {
                            e.getMessage();
                        }
                        shootBulletB = false;
                    }
                    
                    // update every bullet
                    for (int i = 0; i < bulletA.size(); ++i) {
                        bulletA.get(i).update(CELL_SIZE);
                        // shot on another snake
                        if (bulletA.get(i).touchSnakeHead(snakeB)) {
                            System.out.println("Shoot at head B");
                            bloodB -= 20;
                        }
                        else if (bulletA.get(i).touchSnakeBody(snakeB)) {
                            System.out.println("Shoot at body B");
                            bloodB -= 10;
                        }
                        // check border
                        if (bulletA.get(i).checkBorder(leftBorder, rightBorder, topBorder, bottomBorder)) {
                            bulletA.remove(i);
                        }
                        else {
                            bulletA.get(i).drawBullet(g2, CELL_SIZE, true);
                        }
                    }
                    for (int i = 0; i < bulletB.size(); ++i) {
                        bulletB.get(i).update(CELL_SIZE);
                        // shot on another snake
                        if (bulletB.get(i).touchSnakeHead(snakeA)) {
                            System.out.println("Shoot at head A");
                            bloodA -= 20;
                        }
                        else if (bulletB.get(i).touchSnakeBody(snakeA)) {
                            System.out.println("Shoot at body A");
                            bloodA -= 10;
                        }
                        // check border
                        if (bulletB.get(i).checkBorder(leftBorder, rightBorder, topBorder, bottomBorder)) {
                            bulletB.remove(i);
                        }
                        else {
                            bulletB.get(i).drawBullet(g2, CELL_SIZE, false);
                        }
                    }
                    snakeB.moveSnake(CELL_SIZE);
                }
                fruit.drawFruit(g);
                draw.drawStatusBar(g2, enableB, bloodA, bloodB);
                if (enableBomb) {
                    bomb.drawFruit(g);
                }
                draw.changeLevel(g2, bomb, snakeA, fruit);

                // no blood
                if (enableB) {
                    if (bloodA <= 0){
                        gameover = true;
                        Bwin = true;
                        Awin = false;

                    }
                    if (bloodB <= 0){
                        gameover = true;
                        Awin = true;
                        Bwin = false;

                    }
                }
            }
        };
        gameScreen.setBackground(Color.BLACK);
        gameScreen.add(scoreLabel);
    
        // Add screens to the container panel
        this.add(menu, "Opening Screen");
        this.add(gameScreen, "Game");
    
        // Detect the keyboard
        setFocusable(true);
        addKeyListener(this);
        setTimer();
        draw.setCountDownTimer();
    
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

    private void changeSpeed() {
        speed = speed - 2;
        System.out.println("Speed: " + speed);
        t.cancel();
        setTimer();
    }

    public void switchScreen(String screenName) {
        cardLayout.show(this, screenName);
    }

    public static boolean checkCollision(Snake snakeA, Snake snakeB) {
        // Check for head collision
        Node headA = snakeA.getSnakeBody().get(0);
        Node headB = snakeB.getSnakeBody().get(0);

        // Head to head
        if (Math.abs(headA.x - headB.x) <= (ContainerPanel.CELL_SIZE / 2) && 
            Math.abs(headA.y - headB.y) <= (ContainerPanel.CELL_SIZE / 2)) {
                System.out.println("Head to Head"); 
                bloodA -= 30;
                bloodB -= 30;
                try {
                    sounds.Hurt();
                } catch (Exception e) {
                    e.getMessage();
                }
                return true;
            }

        // Head B to body A
        for (int i = 1; i < snakeA.getSnakeBody().size(); ++i) {
            Node nodeA = snakeA.getSnakeBody().get(i);
            if (Math.abs(headB.x - nodeA.x) <= (ContainerPanel.CELL_SIZE / 2) && 
                Math.abs(headB.y - nodeA.y) <= (ContainerPanel.CELL_SIZE / 2)) {
                    System.out.println("Head B");
                    bloodB -= 10;
                    try {
                        sounds.Hurt();
                    } catch (Exception e) {
                        e.getMessage();
                    }
                    // winA = true;
                    return true;
                }
        }
        // Head A to body B
        for (int i = 1; i < snakeB.getSnakeBody().size(); ++i) {
            Node nodeB = snakeB.getSnakeBody().get(i);
            if (Math.abs(headA.x - nodeB.x) <= (ContainerPanel.CELL_SIZE / 2) && 
                Math.abs(headA.y - nodeB.y) <= (ContainerPanel.CELL_SIZE / 2)) {
                    System.out.println("Head A");
                    bloodA -= 10;
                    try {
                        sounds.Hurt();
                    } catch (Exception e) {
                        e.getMessage();
                    }
                    // winA = false;
                    return true;
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
        draw.countdown = 180;
        bloodA = 100;
        bloodB = 100;
        Awin = false;
        Bwin = false;
        gameover = false;
        
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
            bomb.setNewLocation(snakeA, fruit, column, row, leftBorder, topBorder);
        }
    }

    public void resetUI() {
        allowKeyPress = false;
        try {
            sounds.GameOver();
        } catch (Exception e) {
            e.getMessage();
        }

        t.cancel();
        t.purge();
            
        //write score
        file.write_a_file(ScoreFile.score);
    }

    void drawGameOver(Graphics g, boolean gameover, boolean Awin, boolean Bwin) {
        if (gameover) {
            if(Awin){
                g.setColor(Color.BLUE);
                g.setFont(new Font("Times New Roman", Font.BOLD,50));
                g.drawString("Player 1 Win!", 400 , 330);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.PLAIN,20));
                g.drawString("Press Enter to try again!", 440 , 380);
                try {
                    sounds.GameOver();
                } catch (Exception e) {
                    e.getMessage();
                }
                t.cancel();
                t.purge();
            }
            else if(Bwin){
                g.setColor(Color.YELLOW);
                g.setFont(new Font("Times New Roman", Font.BOLD,50));
                g.drawString("Player 2 Win!", 400 , 330);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.PLAIN,20));
                g.drawString("Press Enter to try again!", 440 , 380);
                try {
                    sounds.GameOver();
                } catch (Exception e) {
                    e.getMessage();
                }
                t.cancel();
                t.purge();
            }
            else{
                g.setColor(Color.WHITE);
                g.setFont(new Font("Times New Roman", Font.BOLD,50));
                g.drawString("Game over!", 400 , 330);
                g.setFont(new Font("Arial", Font.PLAIN,20));
                g.drawString("Press Enter to try again!", 420 , 380);
                try {
                    sounds.GameOver();
                } catch (Exception e) {
                    e.getMessage();
                }
                t.cancel();
                t.purge();
            }
        }
    }

    // Move the snake by Keyboard 
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            reset();
        }
        allowKeyPress = true;
        // System.out.println("keyPressed");
        snakeA.changeDirection(e, allowKeyPress, true);
        if (enableB) {
            snakeB.changeDirection(e, allowKeyPress, false);
            shootBulletA = snakeA.checkShoot(e, allowKeyPress, true);
            shootBulletB =  snakeB.checkShoot(e, allowKeyPress, false);
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