import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class ChartsDrawer extends Stage {

    private HashMap<Double, Double> func;
    private double startX;
    private double stopX;
    private double minY;
    private double maxY;

    private double scale;

    public ChartsDrawer(HashMap<Double, Double> f, Stage stage) {
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

        print(stage);
    }

    private void drawLine(Group box, int x1, int y1, int x2, int y2, Color col) {

        Line line = new Line();
        line.setStartX(x1);
        line.setStartY(y1);
        line.setEndX(x2);
        line.setEndY(y2);
        line.setStroke(col);


        box.getChildren().add(line);
    }

    private  void drawString(Group box, int x, int y, String s){

        Text text = new Text(s);

        text.setX(x);
        text.setY(y);

        box.getChildren().add(text);

    }

    private void drawCoordSys(Group box) {
        drawLine(box, 80, 580, 660, 580, Color.BLACK);
        drawLine(box, 80, 30, 80, 580, Color.BLACK);
        drawLine(box, 80, 30, 70, 40, Color.BLACK);
        drawLine(box, 80, 30, 90, 40, Color.BLACK);
        drawLine(box, 650, 570, 660, 580, Color.BLACK);
        drawLine(box, 650, 590, 660, 580, Color.BLACK);
        drawString(box, 50, 40, "y");
        drawString(box, 650, 610, "x");

        for(int i=0; i<=5; ++i) {
            if(i>0)
                drawLine(box, (int)(80 + (double)i/6*580), 575, (int)(80 + (double)i/6*580),585, Color.BLACK);
            drawString(box, (int)(60 + (double)i/6*580), 610, String.valueOf(Math.round((startX + (stopX - startX)/6*i)*100.0)/100.0));
        }

        for(int i=0; i<=5; ++i) {
            if(i>0)
                drawLine(box, 75, (int)(30 + (double)i/6*550), 85,(int)(30 + (double)i/6*550), Color.BLACK);

            drawString(box, 0,
                    595 - (int)(10 + (double)i/6*550), String.valueOf(Math.round((minY + (maxY - minY)/6*i)*100.0)/100.0));
        }

    }

    private void drawFunc(Group box) {

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
            drawLine(box, one, 610 - onev, two, 610 - twov, Color.RED);
        }
    }


    public void print(Stage stage) {
        Group box = new Group();
        final Scene scene = new Scene(box,690, 640);
        scene.setFill(null);

        drawCoordSys(box);
        drawFunc(box);

        stage.setScene(scene);
        stage.show();
    }

}