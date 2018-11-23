import javax.swing.*;
import java.awt.*;

public class ChartsViewer extends JFrame implements Viewer{

    Charts chart;

    public ChartsViewer() {
        super("Chart Drawing app");
        chart = new Charts();
        chart.setPreferredSize(new Dimension(690, 640));
        chart.repaint();

        setSize(690,640);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void drawLine(int x1, int y1, int x2, int y2, Color col){
        chart.putLine(x1, y1, x2, y2, col);
    }

    public void drawString(int x, int y, String s){

        chart.putString(x, y, s);
    }

    public void print(){

        this.add(chart);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
