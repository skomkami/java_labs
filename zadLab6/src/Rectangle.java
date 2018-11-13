import java.awt.*;

public class Rectangle implements Shape {

    private int x;
    private int y;
    private int w;
    private int h;

    private float red;
    private float green;
    private float blue;

    private Color color;

    private boolean filled;

    public Rectangle(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

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
        Graphics2D g2 = (Graphics2D)arg0;
        g2.setPaint(color);

        if(filled)
            g2.fillRect(x, y, w, h);
        else
            g2.drawRect(x, y, w, h);

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
        return w;
    }

    public int getHeight() {
        return h;
    }

    public boolean mouseOver(int mousePosX, int mousePosY) {
        if(mousePosX>=this.getX() && mousePosX<=this.getX()+getWidth()
            && mousePosY>=this.getY() && mousePosY<=this.getY()+getHeight())
            return true;
        else
            return false;
    }
}
