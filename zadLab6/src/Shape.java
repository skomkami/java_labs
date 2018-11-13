import java.awt.*;

public interface Shape {

    void draw(Graphics arg0);

    void setColor(float red, float green, float blue);

    void setX(int x);

    void setY(int y);

    int getX();

    int getY();

    int getWidth();

    int getHeight();

    boolean mouseOver(int mousePosX, int mousePosY);

}
