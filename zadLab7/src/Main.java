import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.HashMap;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chart Drawing App");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label polynomial = new Label("Enter polynomial:");
        grid.add(polynomial, 0, 1);

        TextField polynomialTextField = new TextField("0.001x^5-2x^2-10x^0");
        grid.add(polynomialTextField, 1, 1);

        Label start = new Label("Start: ");
        grid.add(start, 0, 2);

        TextField startTextField = new TextField("-10");
        grid.add(startTextField, 1, 2);

        Label stop = new Label("End: ");
        grid.add(stop, 0, 3);

        TextField stopTextField = new TextField("25");
        grid.add(stopTextField, 1, 3);

        Label freq = new Label("Freq: ");
        grid.add(freq, 0, 4);

        TextField freqTextField = new TextField("0.1");
        grid.add(freqTextField, 1, 4);

        Button btn = new Button("Submit");
        grid.add(btn, 1, 5);

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {

                String p = polynomialTextField.getText();

                double startX = Double.parseDouble(startTextField.getText());
                double stopX = Double.parseDouble(stopTextField.getText());
                double freq = Double.parseDouble(freqTextField.getText());

                try {
                    HashMap<Integer, Double> pol = Polynomial.getFromString(p);

                    HashMap<Double, Double> func = new HashMap<>();

                    for (Double i = startX; i < stopX; i += freq) {
                        func.put(i, Polynomial.count(pol, i));
                    }

                    Stage chartStage = new Stage();
                    chartStage.setTitle("Chart");
                    ChartsDrawer chart = new ChartsDrawer(func, chartStage);


                }catch(Exception err) {
                    System.err.println(err.getMessage());
                }
            }
        });

        Scene scene = new Scene(grid, 400, 400);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}