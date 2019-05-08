/*
 * This is Game class where all game logic is implemented.
 *
 */
package ballbreaker;

/**
 *
 * @author dinesh salve
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.Random;

public final class Game extends JPanel implements ActionListener {

    //Declare all game properties and lists and variables
    private Timer timer;  //triggers action
    private int stages = 3; //indicates no of stages in game
    private static String winStr = "you are Winner";
    private int livesLeft = 4;  //count lives for player
    private String livesStr = "Lives Left";
    private String scoreStr = "Score : ";
    private String levelStr = "Level : ";
    private int[][] lives = {{5, 485}, {25, 485}, {45, 485}, {65, 485}}; //indexes for remaining lives left in status bar
    private int B_WIDTH;
    private int B_HEIGHT;
    private boolean gamePlay = true;  //tells if game is over or in action
    private int currentLevel = 1;     //current level player is playing
    private ArrayList<Brick> brickList;   //list of bricks on screen
    private ArrayList<Power> powerList;   //list of special powers on screen
    private int score = 0;                //score of player
    private Pad pad;                      //Object od Class Pad
    private Ball ball;                    //Object of class Ball
    private int[][] stage = { //Array defines positions of bricks on screen for each level
        //level == 1

        {1, 1, 55, 100}, {1, 1, 100, 100}, {1, 1, 145, 100}, {1, 1, 190, 100}, {1, 1, 235, 100}, {1, 1, 280, 100}, {1, 1, 325, 100}, {1, 1, 370, 100},
        {1, 1, 55, 110}, {1, 1, 100, 110}, {1, 1, 145, 110}, {1, 1, 190, 110}, {1, 1, 235, 110}, {1, 1, 280, 110}, {1, 1, 325, 110}, {1, 1, 370, 110},
        {1, 1, 55, 120}, {1, 1, 100, 120}, {1, 1, 145, 120}, {1, 1, 190, 120}, {1, 1, 235, 120}, {1, 1, 280, 120}, {1, 1, 325, 120}, {1, 1, 370, 120},
        {1, 1, 55, 130}, {1, 1, 100, 130}, {1, 1, 145, 130}, {1, 1, 190, 130}, {1, 1, 235, 130}, {1, 1, 280, 130}, {1, 1, 325, 130}, {1, 1, 370, 130},
        {1, 1, 55, 140}, {1, 1, 100, 140}, {1, 1, 145, 140}, {1, 1, 190, 140}, {1, 1, 235, 140}, {1, 1, 280, 140}, {1, 1, 325, 140}, {1, 1, 370, 140},
        {1, 1, 55, 150}, {1, 1, 100, 150}, {1, 1, 145, 150}, {1, 1, 190, 150}, {1, 1, 235, 150}, {1, 1, 280, 150}, {1, 1, 325, 150}, {1, 1, 370, 150},
        //level == 2

        {2, 3, 55, 100}, {2, 1, 145, 100}, {2, 2, 235, 100}, {2, 1, 325, 100}, {2, 1, 415, 100},
        {2, 3, 55, 110}, {2, 1, 145, 110}, {2, 2, 235, 110}, {2, 1, 325, 110}, {2, 1, 415, 110},
        {2, 3, 55, 120}, {2, 1, 145, 120}, {2, 2, 235, 120}, {2, 1, 325, 120}, {2, 1, 415, 120},
        {2, 3, 55, 130}, {2, 1, 145, 130}, {2, 2, 235, 130}, {2, 1, 325, 130}, {2, 1, 415, 130},
        {2, 3, 55, 140}, {2, 1, 145, 140}, {2, 2, 235, 140}, {2, 1, 325, 140}, {2, 1, 415, 140},
        {2, 3, 55, 150}, {2, 1, 145, 150}, {2, 2, 235, 150}, {2, 1, 325, 150}, {2, 1, 415, 150},
        {2, 3, 55, 100}, {2, 1, 145, 100}, {2, 2, 235, 100}, {2, 1, 325, 100}, {2, 1, 415, 100},
        //level == 3

        {3, 1, 55, 100}, {3, 1, 100, 100}, {3, 1, 145, 100}, {3, 1, 190, 100}, {3, 1, 235, 100}, {3, 1, 280, 100}, {3, 1, 325, 100}, {3, 1, 370, 100},
        {3, 2, 55, 110}, {3, 2, 100, 110}, {3, 2, 145, 110}, {3, 2, 190, 110}, {3, 2, 235, 110}, {3, 2, 280, 110}, {3, 2, 325, 110}, {3, 2, 370, 110},
        {3, 1, 55, 120}, {3, 1, 100, 120}, {3, 1, 145, 120}, {3, 1, 190, 120}, {3, 1, 235, 120}, {3, 1, 280, 120}, {3, 1, 325, 120}, {3, 1, 370, 120},
        {3, 2, 55, 130}, {3, 2, 100, 130}, {3, 2, 145, 130}, {3, 2, 190, 130}, {3, 2, 235, 130}, {3, 2, 280, 130}, {3, 2, 325, 130}, {3, 2, 370, 130},
        {3, 1, 55, 140}, {3, 1, 100, 140}, {3, 1, 145, 140}, {3, 1, 190, 140}, {3, 1, 235, 140}, {3, 1, 280, 140}, {3, 1, 325, 140}, {3, 1, 370, 140},
        {3, 3, 55, 150}, {3, 3, 100, 150}, {3, 3, 145, 150}, {3, 3, 190, 150}, {3, 3, 235, 150}, {3, 3, 280, 150}, {3, 3, 325, 150}, {3, 3, 370, 150}
    };

    public Game() {

        //set game screen properties

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.WHITE);
        setDoubleBuffered(true);
        setSize(500, 550);
        pad = new Pad();
        ball = new Ball();
        brickList = new ArrayList<Brick>();
        powerList = new ArrayList<Power>();
        setStage();
        score=0; //set score to zero
        timer = new Timer(5, this); //set timer to generate action  
        timer.start();
    }

    public void addNotify() {
        super.addNotify();
        B_WIDTH = getWidth();
        B_HEIGHT = getHeight();
    }

    public void paint(Graphics g) {
        super.paint(g);

        if (gamePlay) { //check if game is on 


            //Draw Below score card
            g.setColor(Color.black);
            g.fillRect(0, 450, 500, 3);
            g.drawString(livesStr, 5, 475);
            g.drawString(scoreStr + score, 350, 475);
            g.drawString(levelStr + currentLevel, 350, 500);
            for (int i = 0; i < livesLeft - 1; i++) {
                g.fillRect(lives[i][0], lives[i][1], 15, 5);

            }
            //Draw Pad on screen
            pad.draw(g);
            //Draw Ball screen
            ball.drawBall(g);
            //Draw Bricks
            for (int i = 0; i < brickList.size(); i++) {
                Brick b = brickList.get(i);
                g.setColor(b.getColor());
                g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
            }
            g.setColor(Color.black);
            //Draw Powers on screen
            if (powerList.size() > 0) {
                for (int i = 0; i < powerList.size(); i++) {
                    Power power = powerList.get(i);
                    power.draw(g);

                }
            }
            // Draw Bullets
            ArrayList<Bullet> bullets = pad.getBulletList();
            for (int i = 0; i < bullets.size(); i++) {
                bullets.get(i).draw(g);
            }





        } else { //if game is over
            String msg;
            Font small = new Font("Helvetica", Font.BOLD, 14);
            FontMetrics metr = this.getFontMetrics(small);

            g.setColor(Color.black);
            g.setFont(small);

            msg = "Final Score : " + score;  //to display final score
            g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2,
                    (B_HEIGHT + 30) / 2);
            g.drawString(winStr, (B_WIDTH - metr.stringWidth(msg)) / 2,
                    (B_HEIGHT - 30) / 2);


        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void actionPerformed(ActionEvent e) { //moves object's coordinates and removes disabled ones
        //Remove hidden bricks
        if (gamePlay) { //check  if game is on
            for (int i = 0; i < brickList.size(); i++) {
                if (brickList.get(i).getVisible() == false) {
                    brickList.remove(i);
                }
            }

            //Check if need to go to next level ignoring super bricks

            int total = 0;
            for (int i = 0; i < brickList.size(); i++) {
                if (brickList.get(i).getBrickType() != 3) {
                    total++;
                }

            }
            if (total == 0) { //there are no hard or normal bricks
                currentLevel++; //go to next level
                if (currentLevel > stages) { //if no of levels are done
                    gamePlay = false;  //end game
                } else { //go to next level
                    setStage();   //next level set up
                    powerList.clear(); //remove all powers
                    resetPosition(); //reset ball and pad position
                }
            }

            if (brickList.size() > 0) {  //there is atleast a  brick in game


                //Pad move
                pad.move();
                //Ball move
                ball.move(this);
                //Bullet move
                ArrayList<Bullet> bullets = pad.getBulletList();
                for (int i = 0; i < bullets.size(); i++) {
                    Bullet b = bullets.get(i);
                    if (bullets.get(i).isVisible()) {
                        b.move();
                    } else {
                        bullets.remove(i);  //removes bullets out of screen
                    }
                }
                //power move
                for (int i = 0; i < powerList.size(); i++) {
                    if (powerList.get(i).isVisible()) {
                        powerList.get(i).move();
                    } else {
                        powerList.remove(i);  //removes powers out of screen
                    }
                }
                checkCollisions();  //check all possible collisions

            } else {  //there are no bricks
                currentLevel++;
                if (currentLevel > stages) {
                    gamePlay = false;  //end game
                } else {
                    setStage();
                    powerList.clear();
                    resetPosition();
                }
            }

            repaint();  //repaint after movement changes 

        }
    }

    public void checkCollisions() {
        if (gamePlay) {

            //get current Ball position
            Rectangle rb = ball.getBounds();
            //get current Pad position
            Rectangle rp = pad.getBounds();
            //detect Collision of Pad And Ball


            if (rb.intersects(rp)) {
                ball.rotateBall(pad.getX(), pad.getY(), "onPad", pad); //change ball direction

            }
            //detect Collision of Ball and Bricks
            for (int i = 0; i < brickList.size(); i++) {
                Brick b = brickList.get(i);
                Rectangle rbrick = b.getBounds();
                if (rbrick.intersects(rb)) {
                    if (b.getBrickType() == 1) {  //if brick is normal

                        Toolkit.getDefaultToolkit().beep(); //play sample sound
                        score += 10;  //increment score
                        //b.setVisible(false);

                        if (b.getPower() != 0) { //check if brick has hidden power
                            

                            Power power = new Power(b.getPower(), b.getX(), b.getY());
                            powerList.add(power);  //add power to list

                        }
                        if (ball.isPowerBall() == false) {  //check if ball is super ball
                            ball.rotateBall(b.getX(), b.getY(), "onBrick", pad);
                        }
                        brickList.remove(i); //remove brick when broken
                        i--;
                    } else if (b.getBrickType() == 2) {  //if brick is hard

                        score += 10;
                        Toolkit.getDefaultToolkit().beep();
                        if (ball.isPowerBall() == false) {
                            b.changeBrick();
                            ball.rotateBall(b.getX(), b.getY(), "onBrick", pad);
                        } else {
                            brickList.remove(i);
                            i--;
                        }
                    } else if (b.getBrickType() == 3) { //if brick is super brick
                        ball.rotateBall(b.getX(), b.getY(), "onBrick", pad);
                        Toolkit.getDefaultToolkit().beep();
                    }
                }

            }

            // detect Collision of power and pad
            for (int i = 0; i < powerList.size(); i++) {
                Power p = powerList.get(i);
                Rectangle rpower = p.getBounds();
                if (rpower.intersects(rp)) {
                    p.setVisible(false);
                    if (p.getType() == 1) { // 1==if power is super ball
                        ball.changeBall(true);
                    } else if (p.getType() == 2) { // 2==if power is big puddle
                        pad.changePad("makeBig");
                    } else if (p.getType() == 3) { // 3==if power is small puddle
                        pad.changePad("makeSmall");
                    } else if (p.getType() == 4) { // 4==if power is bomb
                        resetPosition();
                        livesLeft -= 1;
                        if (livesLeft == 0) {
                            this.setWinStr("You lost the GAME");
                            endGame();
                        }

                    } else if (p.getType() == 5) { // 5== if power is weapon

                        pad.changePad("makeGun");
                    }
                }
            }

//detect Collision of bullets and brick
            ArrayList<Bullet> bullets = pad.getBulletList();

            for (int i = 0; i < bullets.size(); i++) {
                Bullet bullet = bullets.get(i);
                for (int j = 0; j < brickList.size(); j++) {
                    Brick brick = brickList.get(j);
                    if (brick.getVisible()) {
                        Rectangle rbullet = bullet.getBounds();
                        Rectangle rbrick = brick.getBounds();
                        if (rbullet.intersects(rbrick)) {
                            brick.setVisible(false);
                            bullet.setVisible(false);
                            if (brick.getBrickType() > 0) {

                                score += 10;

                                if (brick.getPower() != 0) { //check if brick has power hidden
                                    //code to start power flowing
                                    Power power = new Power(brick.getPower(), brick.getX(), brick.getY());
                                    powerList.add(power);

                                }
                            }
                        }

                    }

                }


            }
        }
    }

    public void setStage() {  //Initial set up before any level starts
        //System.out.println("size=" + brickList.size());
        brickList = null;
        brickList = new ArrayList<Brick>();
        powerList.clear();

        for (int i = 0; i < stage.length; i++) {
            //System.out.println(stage[i][0]);

            if (stage[i][0] == currentLevel) {
                Brick b = new Brick(stage[i][1], stage[i][2], stage[i][3]);  //create brick
                //System.out.println("yes");
                brickList.add(b); //add brick to list
            } else {
                if (stage[i][0] > currentLevel) {
                    break;
                }
            }
        }
        //System.out.println("size=" + brickList.size());

        //below code assignes each power twice to random bricks, total 10 powers.

        Random randnum = new Random();
        for (int i = 0; i < 2; i++) {
            brickList.get(randnum.nextInt(brickList.size())).setPower(1); //super ball
        }
        for (int i = 0; i < 2; i++) {
            brickList.get(randnum.nextInt(brickList.size())).setPower(2); //big puddle
        }
        for (int i = 0; i < 2; i++) {
            brickList.get(randnum.nextInt(brickList.size())).setPower(3); //small puddle
        }
        for (int i = 0; i < 2; i++) {
            brickList.get(randnum.nextInt(brickList.size())).setPower(4);  //bomb
        }
        for (int i = 0; i < 2; i++) {
            brickList.get(randnum.nextInt(brickList.size())).setPower(5);  //weapon
        }

    }

    public void setLivesLeft(int live) { //change lives left
        this.livesLeft = live;
    }

    public int getLivesLeft() { //return lives left
        return this.livesLeft;
    }

    public void endGame() { //finish the game
        gamePlay = false;
    }

    public Pad getPad() {  //return game pad
        return this.pad;
    }

    public void resetPosition() { //rest pad and ball to their initial position
        pad.resetPad();
        ball.changeBall(false);
        powerList.clear();

    }

    private class TAdapter extends KeyAdapter { //Key listeners

        public void keyReleased(KeyEvent e) {
            pad.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            pad.keyPressed(e);
        }
    }

    public static void setWinStr(String str) { //set win or losing string
        winStr = str;
    }
}
