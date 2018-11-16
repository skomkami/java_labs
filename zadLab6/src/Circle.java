import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Circle implements Shape {

    private int x;
    private int y;
    private int r;

    private float red;
    private float green;
    private float blue;

    private Color color;

    private boolean filled;


    Circle(int x_coord, int y_coord, int radius) {
        x = x_coord;
        y = y_coord;
        r = radius;

        color = new Color(0.5f, 0.5f , 0.5f);
        filled = false;
    }

    public void setColor(float red, float green, float blue) {
        this.color = new Color(red, green, blue);
    }

    public void setFilled(boolean f) {
        this.filled = f;
    }

    public void draw(Graphics arg0){
        Graphics2D g2 = (Graphics2D) arg0;
        Ellipse2D e = new Ellipse2D.Double(x,y,r,r);

        g2.setPaint(color);
        if(filled)
            g2.fill(e);

        g2.draw(e);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return r;
    }

    public int getHeight() {
        return r;
    }

    public boolean mouseOver(int mousePosX, int mousePosY) {

        int centerX = this.x + this.r/2;
        int centerY = this.y + this.r/2;

        return (mousePosX - centerX)*(mousePosX - centerX) + (mousePosY - centerY)*(mousePosY - centerY) <= r*r/4;
    }
}
