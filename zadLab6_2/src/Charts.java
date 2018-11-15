import javax.swing.*;
import java.awt.*;
import java.util.*;


public class Charts extends JPanel{

    private HashMap<Double, Double> func;
    private double startX;
    private double stopX;
    private double minY;
    private double maxY;

    private double scale;

    public Charts(HashMap<Double, Double> f) {
        func = f;
        SortedSet<Double> keys = new TreeSet<>(func.keySet());
        startX = keys.first();
        stopX = keys.last();
        minY = func.get(keys.first());
        maxY = func.get(keys.first());

        for (Double key : keys) {
            if(func.get(key) < minY)
               minY = func.get(key);

            if(func.get(key) > maxY)
                maxY = func.get(key);
        }

        scale = 620/(stopX - startX);

    }

    private void drawRect(Graphics arg0, int x, int y, int w, int h){
        Graphics2D g = (Graphics2D)arg0;
        g.setPaint(Color.white);
        g.fillRect(x, y, w, h);
    }

    private void drawLine(Graphics arg0, int x1, int y1, int x2, int y2, Color col) {
        Graphics2D g = (Graphics2D)arg0;
        g.setPaint(col);
        g.drawLine(x1, y1, x2, y2);
    }

    private  void drawString(Graphics arg0, int x, int y, String s){
        Graphics2D g = (Graphics2D)arg0;
        g.setColor(Color.black);

        g.setFont(new Font("SansSerif",Font.BOLD,16));
        g.drawString(s, x, y);

    }

    private void drawCoordSys(Graphics arg0) {
        drawLine(arg0, 30, 580, 610, 580, Color.black);
        drawLine(arg0, 30, 30, 30, 580, Color.black);
        drawLine(arg0, 30, 30, 20, 40, Color.black);
        drawLine(arg0, 30, 30, 40, 40, Color.black);
        drawLine(arg0, 600, 570, 610, 580, Color.black);
        drawLine(arg0, 600, 590, 610, 580, Color.black);
        drawString(arg0, 10, 30, "y");
        drawString(arg0, 610, 600, "x");

        for(int i=1; i<=5; ++i) {
            drawLine(arg0, (int)(30 + (double)i/6*580), 575, (int)(30 + (double)i/6*580),585, Color.black);
            drawString(arg0, (int)(10 + (double)i/6*580), 600, String.valueOf(Math.round((startX + (stopX - startX)/6*i)*100.0)/100.0));
        }

        for(int i=0; i<=5; ++i) {
            drawLine(arg0, 25, (int)(30 + (double)i/6*550), 35,(int)(30 + (double)i/6*550), Color.black);
            drawString(arg0, 0,595 - (int)(10 + (double)i/6*550), String.valueOf(Math.round((minY + (maxY - minY)/6*i)*100.0)/100.0));
        }

    }

    private void drawFunc(Graphics arg0) {

        SortedSet<Double> keys = new TreeSet<>(func.keySet());
        SortedSet<Double> keys2 = new TreeSet<>(func.keySet());
        Iterator it = keys.iterator();
        Iterator it2 = keys2.iterator();
        it2.next();

        while (it2.hasNext())
        {
            double a = (double)it.next();
            double b = (double)it2.next();
            double av = func.get(a);
            double bv = func.get(b);

            int one = 30 + (int)(Math.abs(startX*(580.0/(stopX - startX)))) + (int)(a*580.0/(stopX-startX));
            int two = 30 + (int)(Math.abs(startX*(580.0/(stopX - startX)))) + (int)(b*580.0/(stopX-startX));

            int onev = 30 + (int)(Math.abs(minY*(580.0/(maxY - minY)))) + (int)(av*580.0/(maxY - minY));
            int twov = 30 + (int)(Math.abs(minY*(580.0/(maxY - minY)))) + (int)(bv*580.0/(maxY - minY));
            drawLine(arg0, one, 610 - onev, two, 610 - twov,Color.red);
        }
    }

    @Override
    public void paint(Graphics arg0) {
        drawRect(arg0,0,0,640,640);
        drawCoordSys(arg0);
        drawFunc(arg0);
    }

}
