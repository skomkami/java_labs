import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class RelationViewer {

    private Stage stage;
    private Scene scene;
    private GridPane pane;
    private Pane p;
    private ScrollPane scrollPane;

    public RelationViewer(ArrayList<ArrayList<String>> relation) {

        stage = new Stage();

        pane = new GridPane();
        scrollPane = new ScrollPane();
        p = new Pane();

        pane.setPadding(new Insets( 10, 10, 10, 10));
        pane.setHgap(30);
        pane.setVgap(10);

        if(relation.size() == 1) {
            Label noResults = new Label("No records match the paramiters");
            noResults.setStyle("-fx-font-weight: bold; -fx-font-size: 20");
            pane.add(noResults, 0, 0);
        }

        else {
            for (int j = 0; j < relation.get(0).size(); ++j) {
                Label field = new Label(relation.get(0).get(j));
                field.setStyle("-fx-font-weight: bold");
                pane.add(field, j, 0);
            }

            for (int i = 1; i < relation.size(); ++i) {
                for (int j = 0; j < relation.get(0).size(); ++j) {
                    Label field = new Label(relation.get(i).get(j));
                    pane.add(field, j, i);
                }
            }
        }

        p.getChildren().add(pane);
        p.setPadding(new Insets( 0, 20, 0, 0));
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(p);
        scrollPane.setMaxHeight(600);

        scene = new Scene(scrollPane);

        stage.setTitle("Selected records");
        stage.setScene(scene);
        stage.show();

    }

}
