// package com.snake;

// import java.awt.*;
// import java.awt.geom.Rectangle2D;

// public class Draw {
//     int CELL_SIZE = ContainerPanel.CELL_SIZE;
//     int level = ContainerPanel.level;

//     public Draw() {

//     }

//     public void drawStatusBar(Graphics2D g2) {
//         g2.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
//         if (ContainerPanel.enableB) {
//             g2.setColor(Color.RED);
//             g2.fillRect(250, 30, CELL_SIZE, CELL_SIZE);
//             g2.setColor(Color.YELLOW);
//             g2.drawString("Player 1", 300, 100);
//             g2.drawLine(250, 30, CELL_SIZE * 10 + 250, 30);
//             return;
//         }
//         g2.setColor(Color.WHITE);
//         g2.drawString("Time: ", 210, 100);
//         // g2.drawString("Level: ", 460, 100);
//         g2.drawString("Your Score: ", 680, 100);
        
//         // Leaderboard
//         g2.drawString("Leaderboard", 900, 300);
//         g2.drawString("---------------", 870, 330);
//         g2.drawString("1.", 900, 360);
//         g2.drawString("2." , 900, 390);
//         g2.drawString("3." , 900, 420);
//         g2.drawString("4." , 900, 450);
//         g2.drawString("5." , 900, 480);
//         g2.setColor(Color.RED);
//         g2.drawString("" + ScoreFile.score, 810, 100);
//         g2.drawString("" + ScoreFile.numbers.get(4), 950, 360);
//         g2.drawString("" + ScoreFile.numbers.get(3), 950, 390);
//         g2.drawString("" + ScoreFile.numbers.get(2), 950, 420);
//         g2.drawString("" + ScoreFile.numbers.get(1), 950, 450);
//         g2.drawString("" + ScoreFile.numbers.get(0), 950, 480);

//         // Level
//         int levelX = 390 + (30 * level);
//         g2.setColor(Color.GRAY);
//         g2.drawString("1", 420, 50);
//         g2.drawString("2", 450, 50);
//         g2.drawString("3", 480, 50);
//         g2.drawString("4", 510, 50);
//         g2.drawString("5", 540, 50);

//         g2.setColor(Color.YELLOW);
//         g2.drawString("" + level, levelX, 50);
//     }

//     public void changeLevel(Graphics g) {
//         int score = ScoreFile.score;
//         if (score < 99) {
//             level = 1;
//         }
//         if (score > 99) {
//             level = 2;
//             if (!enableBomb) {
//                 bomb.setNewLocation(snakeA, fruit);
//                 bomb.drawFruit(g);
//             }
//             ContainerPanel.enableBomb = true;
//         }
//         if (score > 199) {
//             level = 3;
//             enableChangeSpeed = true;
//         }
//         if (score > 299) {
//             level = 4;
//             enableCrossBorder = false;
//         }
//         if (score > 399) {
//             level = 5;
//         }
//     }

//     public void drawBorder(Graphics2D g2) {
//         for (int i = 0; i < 17; i++) {
//             // Adjust the position of each rectangle
//             double x = 227.0 - i * 2; // Shift x-coordinate by 2 units per iteration
//             double y = 127.0 + i * 2; // Shift y-coordinate downwards by 2 units per iteration

//             Rectangle2D.Double rect = new Rectangle2D.Double(x, y, 624, 480);

//             switch(level) {
//                 case 1:
//                     g2.setColor(Color.BLUE);
//                     break;
//                 case 2:
//                     g2.setColor(Color.GREEN);
//                     break;
//                 case 3:
//                     g2.setColor(Color.MAGENTA);
//                     break;
//                 case 4:
//                     g2.setColor(Color.DARK_GRAY);
//                     break;
//                 case 5:
//                     g2.setColor(Color.BLACK);
//                     break;
//                 default:
//                     g2.setColor(Color.BLUE);
//                 }
//             g2.draw(rect);
//         }
//     }
// }
