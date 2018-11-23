import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;


public class Charts extends JPanel{

    ArrayList<Line> lines = new ArrayList<>();
    ArrayList<Str> strings = new ArrayList<>();

    public void drawRect(Graphics arg0, int x, int y, int w, int h){
        Graphics2D g = (Graphics2D)arg0;
        g.setPaint(Color.white);
        g.fillRect(x, y, w, h);
    }

    public void putLine(int x1, int y1, int x2, int y2, Color col) {
        lines.add(new Line(new Line2D.Double(x1, y1, x2, y2), col));
    }

    public void putString(int x, int y, String s){
        strings.add(new Str(x, y, s));
    }

    @Override
    public void paint(Graphics arg0) {
        Graphics2D g2d = (Graphics2D)arg0;
        drawRect(arg0, 0, 0, 690, 640);
        g2d.setPaint(Color.black);

        for (Line l : lines) {
            if(g2d.getPaint() != l.c)
                g2d.setPaint(l.c);
            g2d.draw(l.l);
        }

        g2d.setFont(new Font("SansSerif",Font.PLAIN,16));
        g2d.setPaint(Color.black);

        for (Str s : strings)
            g2d.drawString(s.s, s.x, s.y);

    }

    private class Str {
        public String s;
        public int x;
        public int y;

        public Str(int x, int y, String s) {
            this.x = x;
            this.y = y;
            this.s = s;
        }
    }

    private class Line {
        public Line2D.Double l;
        public Color c;

        public Line(Line2D.Double l, Color c) {
            this.l = l;
            this.c = c;
        }

    }

}
