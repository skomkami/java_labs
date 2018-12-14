import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Application {
    public Socket socketIn = null;
    public ObjectInputStream in = null;

    Pane root = new Pane();
    Canvas canvas = new Canvas(800, 600);
    GraphicsContext gc = canvas.getGraphicsContext2D();

    Thread listener = new Thread() {
        public void run() {
            try {
                socketIn = new Socket("localhost", 6666);
                in = new ObjectInputStream(socketIn.getInputStream());
            } catch (UnknownHostException e) {
                System.err.println("Don't know about host: localhost.");
                System.exit(1);
            } catch (IOException e) {
                System.err.println("Couldn't get I/O for "
                        + "the connection to: localhost.");
                System.exit(1);
            }
            Object line;
            try {
                while ((line = in.readObject()) != null) {
                    int startX = ((LineSerialized)line).getStartX();
                    int startY = ((LineSerialized)line).getStartY();
                    int endX = ((LineSerialized)line).getEndX();
                    int endY = ((LineSerialized)line).getEndY();

                    gc.strokeLine(startX, startY, endX, endY);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            try {
                in.close();
                socketIn.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    };

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Viewer");

        canvas.setWidth(800);
        canvas.setHeight(600);


        root.getChildren().add(canvas);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
        listener.start();

    }

    public static void main(String[] args) throws IOException {
        launch();
    }

}
