/*
 * This is Brick class and defines behavior of Bricks used in Game
 */
package ballbreaker;

/**
 *
 * @author dinesh salve
 */
import java.awt.Color;
import java.awt.Rectangle;

public class Brick {

    private int x;              //current position of brick X
    private int y;              //current position of brick Y
    private int width;          //width of brick
    private int height;         //height of brick
    private int brickType;      //type of brick e.g. 1=normal , 2= hard, 3=Super brick
    private boolean visible;    //if brick visible
    private int power = 0;        //if brick has hidden power
    private Color color;        //color of brick based on type

    public Brick(int btype, int bx, int by) {

        brickType = btype;
        //set color of brick based on type
        if (btype == 1) {
            color = Color.lightGray;  //normal brick
        } else if (btype == 2) {
            color = Color.gray; //hard brick
        } else if (btype == 3) {
            color = Color.black; //super brick
        }
        visible = true;
        x = bx;
        y = by;
        width = 44;
        height = 9;

    }

    public int getX() { //return X
        return x;
    }

    public int getY() { //return Y
        return y;
    }

    public int getWidth() { //return width
        return width;
    }

    public void setPower(int p) {  //assign hidden power to brick
        power = p;
    }

    public Color getColor() //get color of brick
    {
        return color;
    }

    public int getHeight() {   //return height of brick
        return height;
    }

    public int getPower() {  //return hidden power of brick
        return power;
    }

    public boolean getVisible() //if brick is visible
    {
        return visible;
    }

    public void setVisible(boolean newVisible) //set brick visibility
    {
        visible = newVisible;
    }

    public int getBrickType() //return brick type
    {
        return brickType;
    }

    public Rectangle getBounds() {  //return bounderies of brick
        return new Rectangle(x, y, width, height);
    }

    public void changeBrick() //change type of brick on stroke of ball
    {
        brickType = 1;
        color = Color.lightGray;
    }
}
