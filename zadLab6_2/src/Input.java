import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Input extends JFrame {

    private JPanel rootPanel;
    private JLabel label5;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label1;
    private JLabel label0;
    private JButton drawButton;
    private JTextField text5;
    private JTextField text4;
    private JTextField text3;
    private JTextField text2;
    private JTextField text1;
    private JTextField text0;
    private JLabel startLabel;
    private JTextField startField;
    private JTextField stopField;
    private JTextField freqField;

    public Input() {

        super("Chart Drawing app");
        add(rootPanel);

        setSize(640,480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                double a = Double.parseDouble(text5.getText());
                double b = Double.parseDouble(text4.getText());
                double c = Double.parseDouble(text3.getText());
                double d = Double.parseDouble(text2.getText());
                double e = Double.parseDouble(text1.getText());
                double f = Double.parseDouble(text0.getText());

                double startX = Double.parseDouble(startField.getText());
                double stopX = Double.parseDouble(stopField.getText());
                double freq = Double.parseDouble(freqField.getText());

                HashMap<Double, Double> func = new HashMap<>();

                for(Double i = startX; i < stopX; i += freq) {
                    func.put(i, count(a, b, c, d, e, f, i));
                }

                ChartsDrawer cd = new ChartsDrawer();
                Charts chart = new Charts(func);

                cd.add(chart);
                cd.setLocationRelativeTo(null);
                cd.setVisible(true);
            }
        });
    }

    public double count(double a, double b, double c, double d, double e, double f, double x) {
        return a*Math.pow(x,5) + b*Math.pow(x,4) + c*Math.pow(x,3) + d*Math.pow(x,2) + e*x + f;
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        Input myInput = new Input();
        myInput.setVisible(true);

    }
}
