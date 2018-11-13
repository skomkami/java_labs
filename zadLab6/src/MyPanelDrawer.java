import java.awt.*;
import java.awt.event.*;


public class MyPanelDrawer extends Frame{

    public static void main(String [] argv){
        MyPanelDrawer dp = new MyPanelDrawer();
        MyPanel d = new MyPanel();

        Circle circle = new Circle(100, 100, 100);
        Rectangle rectangle = new Rectangle(300, 400, 100, 50);
        Rectangle rectangle2 = new Rectangle(500, 300, 30, 50);
        circle.setFilled(true);
        rectangle.setFilled(true);
        rectangle2.setFilled(true);
        circle.setColor(0.8f, 0.2f, 0.1f);
        rectangle2.setColor(0.1f, 0.6f, 0.2f);
        d.addShape(circle);
        d.addShape(rectangle);
        d.addShape(rectangle2);

        dp.setSize(640, 480);
        dp.setVisible(true);

        dp.add(d);

        dp.setLocationRelativeTo(null);

        dp.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });


    }
}

