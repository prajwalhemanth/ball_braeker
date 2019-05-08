/*
 * This is a Ball class. It defines all behavior of ball used in the game
 */
package ballbreaker;

/**
 *
 * @author dinesh salve
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball {

    private int dx;         //ball position change rate X
    private int dy;         //ball position change rate Y
    private int x;          //current position of ball X
    private int y;          //current position of ball Y
    private int width;      //horizontal diameter of ball
    private int height;     //verticle diameter of ball
    private int movementType; //defines ball's current movement direction e.g. 1=North-West, 2=SW, 3=SE, 4=NE
    
    private boolean powerBall;  //check if ball had power super ball

    public Ball() {

        x = 247;
        y = 427;
        dx = 1;
        dy = -1;
        width = 8;
        height = 8;
        movementType = 1;
    
        powerBall=false;

    }

    public void move(Game game) { //move ball to next position

        x += dx;
        y += dy;
        if (x < 0) {   //if ball is near left wall
            x = 0;
            if (movementType == 3) {
                movementType = 2;
                dx = -dx;
                y+=3;
            } else if (movementType == 4) {
                movementType = 1;
                dx = -dx;
                y-=2;
            }
        }
        if (x > (500-this.width)) {   //if ball is near right wall
            x = 500-this.width;
            if (movementType == 1) {
                movementType = 4;
                dx = -dx;
                y-=3;
            } else if (movementType == 2) {
                movementType = 3;
                dx = -dx;
                y+=2;
            }
        }
        if (y < 0) {  //if ball is near upper wall
            y = 0;
            if (movementType == 1) {
                movementType = 2;
                x+=2;
                dy = -dy;
            } else if (movementType == 4) {
                movementType = 3;
                dy = -dy;
                x-=3;
            }
        }
        if (y >= 439) { //if ball is below pad level and check if lives are over or not
            int lives = game.getLivesLeft();
            lives -= 1;
            if (lives == 0) {
                Game.setWinStr("You lost the GAME");
                game.endGame();
            } else {
                game.setLivesLeft(lives);

                dx = 1;
                dy = -1;
                movementType = 1;
                game.resetPosition();

            }

        }
    }

    public void rotateBall(int px, int py, String collisionType,Pad pad) {
    //this method changes dx and dy depending on type of collison and object on which collision
    //happened e.g. brick or pad
        if (collisionType.equals("onPad")) { //if collision is with pad
            if (this.x <= (px + (pad.getWidth()/3))) { //detect first 1/3rd of pad
                if (movementType == 2 || movementType == 3) {
                    //dx= -2;
                    x -= 5;
                    dx = -1;
                    dy = -1;
                    movementType = 4;
                }

            } else if (this.x > px + (pad.getWidth()/3) && x <= px + ((pad.getWidth()*2)/3)) {  //detect second 1/3 rd of pad
                if (movementType == 2) {
                    dx = 1;
                    dy = -1;
                    movementType = 1;
                } else if (movementType == 3) {
                    dx = -1;
                    dy = -1;
                    movementType = 4;
                }

            } else if (x > px + ((pad.getWidth()*2)/3) && x <= px + pad.getWidth()) {  //detect last 1/3rd of pad
                if (movementType == 2 || movementType == 3) {
                    x += 5;
                    dx = 1;
                    dy = -1;
                    movementType = 1;
                }


            }
        } else if (collisionType.equals("onBrick")) { //if collision is on brick
            if (y < py) { //on upper side of brick
                if (movementType == 2) {
                    movementType = 1;
                    dy = -dy;
                } else if (movementType == 3) {
                    movementType=4;
                    dy=-dy;
                }
            }
            else if(x <= px)  //on left side of brick
            {
                if (movementType == 2) {
                    movementType = 3;
                    dx = -dx;
                } else if (movementType == 1) {
                    movementType=4;
                    dx=-dx;
                    
                }
            }
            else if( y>= py+7) //on lower side of brick
            {
                if (movementType == 1) {
                    movementType = 2;
                    dy = -dy;
                } else if (movementType == 4) {
                    movementType=3;
                    dy=-dy;
                    
                }
            }
            else if(x >= px+42)  //on right side of brick
            {
                if (movementType == 4) {
                    movementType = 1;
                    dx = -dx;
                } else if (movementType == 3) {
                    movementType=2;
                    dx=-dx;

                }
            }


        }
 
    }

    public int getX() { //return  x position of ball
        return x;
    }

    public int getY() {  //return  y position of ball
        return y;
    }

    public int getWidth() { //return width of ball
        return width;
    }

    public void setX(int newX) { //set new X
        x = newX;
    }

    public void setY(int newY) { //set new Y
        y = newY;
    }

    public int getHeight() { //return  height position of ball
        return height;
    }

    public Rectangle getBounds() { //get rectangular bounderies of ball
        return new Rectangle(x, y, width, height);
    }

    public void changeBall(boolean newBall) //change type of ball between normal and super ball
    {
        powerBall=newBall;
        if(newBall==true) //if got the super ball power
        {
            width=10;
            height=10;
        }
    else { //reset ball
            width=8;
            height=8;
            x=247;
            y=427;
            dx = 1;
            dy = -1;
            movementType = 1;
            powerBall=false;

    }
    }

    public boolean isPowerBall() //check if ball has power
    {
        return powerBall;
    }
    

    public void drawBall(Graphics g) //display ball on screen
    {
        if(powerBall)
        {
            g.setColor(Color.red);
            g.fillArc(x, y, 10, 10, 0, 360);
            g.setColor(Color.black);
            g.drawArc(x, y, 10, 10, 0, 360);

        }
        else
        {
            g.fillArc(this.x,this.y, this.width,this.height, 0, 360);
        }
    }

}
