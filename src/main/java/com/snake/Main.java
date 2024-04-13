package com.snake;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;

// Graphic UI
public class Main extends JPanel implements KeyListener {
    private CardLayout cardLayout;
    private JPanel openingScreen = new JPanel();

    // Size of frame variables
    public static final int CELL_SIZE = 15;
    // public static final int width = 624;
    public static final int width = 600;
    // public static final int boundaryWidth = 10;
    public static final int height = 600;
    // public static final int height = 480;
    // public static final int boundaryHeight = 220;
    public static int row = height / CELL_SIZE;
    public static int column = width / CELL_SIZE;

    // Setting speed variables
    private Timer t;
    private int speed = 50;
    
    public static boolean allowKeyPress = true;
    public static boolean enableB = false;
    public static boolean enableCrossBorder = false;

    // can be moved to the body of paintComponent()
    public static Fruit fruit = new Fruit();
    public static Snake snakeA = new Snake(200);
    public static Snake snakeB = new Snake(300);

    public static ScoreFile file = new ScoreFile();

    private Node headA;
    private Node headB;

    private ImageIcon backgroundImage;

    public Main() {
        // Load the background image
        backgroundImage = ImageLoader.loadImageIconFromResource(ImageLoader.background);

        // Set JPanel properties
        setPreferredSize(new Dimension(backgroundImage.getIconWidth(), backgroundImage.getIconHeight()));

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
        openingScreen.setBackground(Color.BLACK);
        openingScreen.add(buttonSingle);
        openingScreen.add(buttonTwo);
        openingScreen.add(new JLabel("Opening Screen"));

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
                g.fillRect(0, 0, width, height);
                g.drawImage(backgroundImage.getImage(), 0, 0, null);

                // super.paintComponent(g);
                // Graphics2D g2 = (Graphics2D) g;

                // for (int i = 0; i < 17; i++) {
                //     // Adjust the position of each rectangle
                //     double x = 227.0 - i * 2; // Shift x-coordinate by 2 units per iteration
                //     double y = 127.0 + i * 2; // Shift y-coordinate downwards by 2 units per iteration

                //     Rectangle2D.Double rect = new Rectangle2D.Double(x, y, 624, 480);

                //     g2.setColor(Color.BLUE);
                //     g2.draw(rect);
                // }

                // Snake A
                snakeA.drawSnake(g, true);
                snakeA.checkEatFruit(fruit, g);
                headA = snakeA.getSnakeBody().get(0);
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
                    snakeB.checkEatFruit(fruit, g);
                    headB = snakeB.getSnakeBody().get(0);
                    for(int i = 3 ; i < snakeB.getSnakeBody().size();i++) {
                        if (snakeB.getSnakeBody().get(i).x == headB.x && snakeB.getSnakeBody().get(i).y == headB.y){
                            allowKeyPress = false; //game over keyborad can't use
                
                            t.cancel(); //game over timer t stop
                            t.purge();
            
                            resetUI();
                        }
                    }
                    if (snakeB.isCrossBorder() == true && !enableCrossBorder) {
                        allowKeyPress = false; //game over keyborad can't use
                        
                        t.cancel(); //game over timer t stop
                        t.purge();
                        
                        resetUI();
                    }
                    snakeB.moveSnake(CELL_SIZE);
                }
                fruit.drawFruit(g);
            }
        };
        gameScreen.setBackground(Color.BLACK);
        gameScreen.add(new JLabel("Game"));

        // Add screens to the container panel
        this.add(openingScreen, "Opening Screen");
        this.add(gameScreen, "Game");

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
        if (enableB) {
            snakeB = new Snake(100);
        }
        setTimer();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
        // Graphic UI
        JFrame window = new JFrame("Snake Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // window.setBounds(100, 5, 1100, 700);
        // window.setSize(width, height);
        // window.setContentPane(new Main());
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
        if (enableB) {
            snakeB.changeDirection(e, allowKeyPress, false);
        }
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
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }

    public void switchScreen(String screenName) {
        cardLayout.show(this, screenName); // have to determine whether this !!!
    }
}