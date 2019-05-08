/*
 *This is Bullet class and defines behavior of bullets from weapon
 */
package ballbreaker;
/**
 *
 * @author dinesh salve
 */



import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet {

    
    private int x;              //current position of bullet X
    private int y;              //current position of bullet Y
    private int width=3;        //width of bullet
    private int height=7;       //height of bullet
    private boolean visible;    //is bullet visible
    

    public Bullet(int px,int py) {

        x=px;
        y=py;
        visible = true;


    }

    public void move() { //move the bullet
        if (y < 0) { //if bullet is out of screen
            visible = false;
        }
        y -= 1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g) {
        g.fillRect(x, y, width, height);
    }
}
