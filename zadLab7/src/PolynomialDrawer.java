import javafx.scene.Group;
import javafx.scene.paint.Color;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class PolynomialDrawer {
    private HashMap<Double, Double> func;
    private double startX;
    private double stopX;
    private double minY;
    private double maxY;

    private double scale;

    Viewer viewer;


    public PolynomialDrawer(HashMap<Double, Double> f) {
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

        viewer = new ChartsViewer();

        drawCoordSys();

        drawFunc();

        viewer.print();
    }

    private void drawCoordSys() {
        viewer.drawLine(80, 580, 660, 580, Color.BLACK);
        viewer.drawLine(80, 30, 80, 580, Color.BLACK);
        viewer.drawLine(80, 30, 70, 40, Color.BLACK);
        viewer.drawLine(80, 30, 90, 40, Color.BLACK);
        viewer.drawLine(650, 570, 660, 580, Color.BLACK);
        viewer.drawLine(650, 590, 660, 580, Color.BLACK);
        viewer.drawString(50, 40, "y");
        viewer.drawString(650, 610, "x");

        for(int i=0; i<=5; ++i) {
            if(i>0)
                viewer.drawLine((int)(80 + (double)i/6*580), 575, (int)(80 + (double)i/6*580),585, Color.BLACK);
            viewer.drawString((int)(60 + (double)i/6*580), 610, String.valueOf(Math.round((startX + (stopX - startX)/6*i)*100.0)/100.0));
        }

        for(int i=0; i<=5; ++i) {
            if(i>0)
                viewer.drawLine(75, (int)(30 + (double)i/6*550), 85,(int)(30 + (double)i/6*550), Color.BLACK);

            viewer.drawString(0,
                    595 - (int)(10 + (double)i/6*550), String.valueOf(Math.round((minY + (maxY - minY)/6*i)*100.0)/100.0));
        }

    }

    private void drawFunc() {

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

            int one = 80 + (int)(Math.abs(startX*(580.0/(stopX - startX)))) + (int)(a*580.0/(stopX-startX));
            int two = 80 + (int)(Math.abs(startX*(580.0/(stopX - startX)))) + (int)(b*580.0/(stopX-startX));

            int onev = 30 + (int)(Math.abs(minY*(580.0/(maxY - minY)))) + (int)(av*580.0/(maxY - minY));
            int twov = 30 + (int)(Math.abs(minY*(580.0/(maxY - minY)))) + (int)(bv*580.0/(maxY - minY));
            viewer.drawLine(one, 610 - onev, two, 610 - twov, Color.RED);
        }
    }
}
