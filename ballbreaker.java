/*
 * This is BallBreaker class and it creates window that initiats the game in new Frame after clicking
 * on start button
 *
 */
package ballbreaker;

/**
 *
 * @author dinesh salve
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JPanel;

public class BallBreaker extends JFrame implements ActionListener {

    public BallBreaker() {

        //create start game button and add to the frame
        JButton button = new JButton("Start Game");
        button.addActionListener(this);
        button.setBounds(180, 100, 150, 50);
        add(button, BorderLayout.CENTER);
        //create object of Startscreen class and to Frame.
        Startscreen start = new Startscreen();
        start.setBounds(0, 100, 10, 10);
        this.add(start);
        //set Frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 550);
        setLocationRelativeTo(null);
        setTitle("Ball Breaker");
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String str = ae.getActionCommand();
        //Create instance of game when Start Button is clicked
        if (str.equals("Start Game")) {
            JFrame gameWindow = new JFrame();
            gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameWindow.setSize(510, 550);
            gameWindow.setLocationRelativeTo(null);
            gameWindow.setTitle("Ball Breaker");
            gameWindow.setVisible(true);
            gameWindow.setResizable(false);
            gameWindow.add(new Game());
           
            



        }
    }
}

class Startscreen extends JPanel {  //this class displays information and use guide about game

    public Startscreen() {

        //set panel properties
        setFocusable(true);
        setBackground(Color.black);
        setDoubleBuffered(true);
        setSize(500, 400);
        this.setLayout(null);




    }
//Draw all instructions on screen
    public void paint(Graphics g) {

        int x, y;
        g.setColor(Color.black);

        x = 100;
        y = 200;
        //Information label
        g.drawString("Information about game", x + 70, y);
        g.drawLine(x + 70, y + 10, x + 220, y + 10);
        x = 0;

        //draw bricks
        g.drawString("Types Of Bricks", x, y + 50);
        g.setColor(Color.lightGray);
        g.fillRect(x + 5, y + 70, 40, 7);
        g.setColor(Color.black);
        g.drawString("Normal Brick, broken by normal ball in single stroke", x + 70, y + 75);
        g.setColor(Color.gray);
        g.fillRect(x + 5, y + 85, 40, 7);
        g.setColor(Color.black);
        g.drawString("Hard Brick, broken by normal ball in two stroke", x + 70, y + 90);
        g.setColor(Color.black);
        g.fillRect(x + 5, y + 100, 40, 7);
        g.setColor(Color.black);
        g.drawString("Super Brick, can not be broken by any ball", x + 70, y + 105);
        g.drawString("Types Of Powers", x, y + 130);
        //draw power ball
        g.setColor(Color.MAGENTA);
        g.fillArc(x + 5, y + 140, 10, 10, 0, 360);
        g.setColor(Color.black);
        g.drawArc(x + 5, y + 140, 10, 10, 0, 360);
        g.drawString("Super Ball, can break normal and hard brick in single stroke", x + 70, y + 150);
        x = 5;
        y = 360;
        //draw big puddle
        g.setColor(Color.black);
        g.fillRect(x + 5, y + 3, 15, 6);
        g.fillPolygon(new int[]{x + 5, x, x + 5, x + 5}, new int[]{y, y + 6, y + 12, y}, 4);
        g.fillPolygon(new int[]{x + 20, x + 25, x + 20, x + 20}, new int[]{y, y + 6, y + 12, y}, 4);
        g.drawString("Big Puddle", x + 70, y + 10);

        y = 382;
        //draw small puddle
        g.setColor(Color.black);
        g.fillRect(x, y + 3, 5, 6);
        g.fillRect(x + 15, y + 3, 5, 6);
        g.fillPolygon(new int[]{x + 10, x + 5, x + 5, x + 10}, new int[]{y + 6, y + 12, y, y + 6}, 4);
        g.fillPolygon(new int[]{x + 10, x + 15, x + 15, x + 10}, new int[]{y + 6, y + 12, y, y + 6}, 4);
        g.drawString("Small Puddle", x + 70, y + 10);
        y = 400;
        //draw bomb
        g.setColor(Color.gray);
        g.fillArc(x, y + 5, 15, 15, 0, 360);
        g.drawLine(x + 7, y, x + 7, y + 6);
        g.setColor(Color.red);
        g.fillArc(x + 5, y, 4, 4, 0, 360);
        g.setColor(Color.black);
        g.drawString("Bomb", x + 70, y + 10);
        //draw weapon
        y = 427;
        g.setColor(Color.darkGray);
        g.fillRect(x, y + 2, 5, 3);
        g.fillRect(x + 5, y, 20, 7);
        g.fillRect(x + 10, y, 4, 15);
        g.fillRect(x + 20, y, 5, 15);
        g.setColor(Color.black);
        g.drawString("Weapon (use SPACE key)", x + 70, y + 10);




    }
}
