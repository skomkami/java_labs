import java.awt.*;

public interface Viewer {

    void drawLine(int x1, int y1, int x2, int y2, Color col);

    void drawString(int x, int y, String s);

    void print();

}