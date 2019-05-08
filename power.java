/**
 * This is Power class which defines different powers used in Game
 */
package ballbreaker;
/**
 *
 * @author dinesh salve
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Power {

    private int x;      //current position of power X
    private int y;      //current position of power Y
    private int width;  //width
    private int height; //height
    private boolean visible;
    private int type;
    //type of power e.g. 1=super ball, 2=big puddle, 3=small puddle, 4=bomb, 5=weapon

    public Power(int ptype, int x, int y) {

       visible = true;
        type = ptype;
        this.x = x;
        this.y = y;
        if (type == 1) { //set dimentions for bounderies based on type of power
            width = 10;
            height = 10;

        }
        if (type == 2) {
            width = 25;
            height = 12;
        }
        if (type == 3) {
            width = 20;
            height = 12;
        }
        if (type == 4) {
            width = 15;
            height = 20;
        }
        if(type==5)
        {
            width=25;
            height=15;
        }
    }

    public void move() { //move power
        if (y > 442) { //if power out of screen
            visible = false;
        }
        y += 1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getType() {
        return type;
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

    public void draw(Graphics g) { //draw power on screen
        if (type == 1) {
            g.setColor(Color.MAGENTA);
            g.fillArc(x, y, 10, 10, 0, 360);
            g.setColor(Color.black);
            g.drawArc(x, y, 10, 10, 0, 360);

        } else if (type == 2) {
            g.setColor(Color.black);
            g.fillRect(x + 5, y + 3, 15, 6);
            g.fillPolygon(new int[]{x + 5, x, x + 5, x + 5}, new int[]{y, y + 6, y + 12, y}, 4);
            g.fillPolygon(new int[]{x + 20, x + 25, x + 20, x + 20}, new int[]{y, y + 6, y + 12, y}, 4);

        } else if (type == 3) {

            g.setColor(Color.black);
            g.fillRect(x, y + 3, 5, 6);
            g.fillRect(x + 15, y + 3, 5, 6);
            g.fillPolygon(new int[]{x + 10, x + 5, x + 5, x + 10}, new int[]{y + 6, y + 12, y, y + 6}, 4);
            g.fillPolygon(new int[]{x + 10, x + 15, x + 15, x + 10}, new int[]{y + 6, y + 12, y, y + 6}, 4);
        } else if (type == 4) {
            g.setColor(Color.gray);
            g.fillArc(x, y + 5, 15, 15, 0, 360);
            g.drawLine(x + 7, y, x + 7, y + 6);
            g.setColor(Color.red);
            g.fillArc(x + 5, y, 4, 4, 0, 360);
            g.setColor(Color.black);
        } else if (type == 5) {
            g.setColor(Color.darkGray);
            g.fillRect(x, y+2, 5, 3);
            g.fillRect(x+5, y, 20, 7);
            g.fillRect(x+10, y, 4, 15);
            g.fillRect(x+20, y  , 5, 15);
            g.setColor(Color.black);
        }
    }
}
