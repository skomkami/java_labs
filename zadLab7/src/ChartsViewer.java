import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class ChartsViewer extends Stage implements Viewer{

    Stage stage;
    Scene scene;
    Group box;

    public ChartsViewer() {
        box  = new Group();
    }

    public void drawLine(int x1, int y1, int x2, int y2, Color col) {

        Line line = new Line();
        line.setStartX(x1);
        line.setStartY(y1);
        line.setEndX(x2);
        line.setEndY(y2);
        line.setStroke(col);

        box.getChildren().add(line);
    }

    public void drawString(int x, int y, String s){

        Text text = new Text(s);
        text.setX(x);
        text.setY(y);

        box.getChildren().add(text);

    }


    public void print() {
        scene = new Scene(box, 690, 640);

        stage = new Stage();
        stage.setTitle("Chart");

        stage.setScene(scene);
        stage.show();
    }

}