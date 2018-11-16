import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Main extends JFrame {

    private JPanel rootPanel;
    private JButton drawButton;
    private JLabel startLabel;
    private JTextField startField;
    private JTextField stopField;
    private JTextField freqField;
    private JTextField polynomial;

    public Main() {

        super("Chart Drawing app");
        add(rootPanel);

        setSize(640,480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String p = polynomial.getText();

                double startX = Double.parseDouble(startField.getText());
                double stopX = Double.parseDouble(stopField.getText());
                double freq = Double.parseDouble(freqField.getText());

                HashMap<Double, Double> func = new HashMap<>();

                try {
                    for (Double i = startX; i < stopX; i += freq) {
                        func.put(i, PolynomialCounter.count(p, i));
                    }

                    ChartsDrawer cd = new ChartsDrawer();
                    Charts chart = new Charts(func);

                    cd.add(chart);
                    cd.setLocationRelativeTo(null);
                    cd.setVisible(true);

                }catch(Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        Main main = new Main();
        main.setVisible(true);
    }
}
