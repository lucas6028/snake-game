import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;

// Graphic UI
public class Main extends JPanel {
    public static final int CELL_SIZE = 20;
    public static int width = 400;
    public static int height = 400;
    public static int row = height / CELL_SIZE;
    public static int column = width / CELL_SIZE;

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    public Main() {

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
}