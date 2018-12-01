import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.sql.SQLException;

public class ErrorWindow {

    private Stage stage;
    private Scene scene;
    private GridPane pane;

    public ErrorWindow(SQLException ex) {
        stage = new Stage();

        pane = new GridPane();

        pane.setPadding(new Insets( 10, 10, 10, 10));
        pane.setHgap(5);
        pane.setVgap(5);

        Label error = new Label("Connection error!");
        error.setStyle("-fx-font-weight: bold; -fx-font-size: 20; -fx-text-fill: red;");
        GridPane.setHalignment(error, HPos.CENTER);
        pane.add(error, 0, 0, 2, 1);

        Label sqlErrLabel = new Label("SQLException: ");
        sqlErrLabel.setStyle("-fx-font-weight: bold;");
        pane.add(sqlErrLabel, 0, 2);

        Label sqlErrLabelContent = new Label(ex.getMessage());
        pane.add(sqlErrLabelContent, 1, 2);

        Label sqlStatLabel = new Label("SQLState: ");
        sqlStatLabel.setStyle("-fx-font-weight: bold;");
        pane.add(sqlStatLabel, 0, 3);

        Label sqlStatLabelContent = new Label(ex.getSQLState());
        pane.add(sqlStatLabelContent, 1, 3);

        Label sqlVenLabel = new Label("VendorError: ");
        sqlVenLabel.setStyle("-fx-font-weight: bold;");
        pane.add(sqlVenLabel, 0, 4);

        Label sqlVenLabelContent = new Label(String.valueOf(ex.getErrorCode()));
        pane.add(sqlVenLabelContent, 1, 4);

        scene = new Scene(pane);

        stage.setTitle("Database Connection Error");
        stage.setScene(scene);
        stage.show();
    }
}
