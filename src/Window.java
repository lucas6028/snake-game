import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Window extends JFrame implements KeyListener{
    public static boolean allowKeyPress = true;
    public static String direction = "Right";

    public Window() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new Main());
        this.pack();
        this.setLocationRelativeTo(null);
        this.addKeyListener(this);
        this.setResizable(false);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
        System.out.println("keyTyped");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed");
        
        if (e.getKeyCode() == 37 && !direction.equals("Right")) {
            direction = "Left";
        }
        else if (e.getKeyCode() == 38 && !direction.equals("Down")) {
            direction = "Up";
        }
        else if (e.getKeyCode() == 39 && !direction.equals("Left")) {
            direction = "Right";
        }
        else if (e.getKeyCode() == 40 && !direction.equals("Up")) {
            direction = "Down";
        }
        // allowKeyPress = false;
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
        System.out.println("keyReleased");
    }
}
