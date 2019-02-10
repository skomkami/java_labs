import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Application {
    protected Socket socket = null;
    protected ObjectInputStream in = null;
    protected ObjectOutputStream out = null;

    protected Pane root = new Pane();
    protected Canvas canvas = new Canvas(800, 600);
    protected GraphicsContext gc = canvas.getGraphicsContext2D();

    protected double x,y;

    public Client() {
        try {
            socket = new Socket("localhost", 3000);
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                    + "the connection to: localhost.");
            System.exit(1);
        }
    }

    public static void main(String[] args) throws IOException {
        launch();
    }

    protected Thread listener = new Thread() {
        public void run() {
            try {
                in = new ObjectInputStream(socket.getInputStream());
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
        }
    };

   protected EventHandler<WindowEvent> windowHandler = new EventHandler<>() {
        @Override
        public void handle(WindowEvent event) {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    };

    protected EventHandler<MouseEvent> mouseHandler = new EventHandler<>() {

        @Override
        public void handle(MouseEvent mouseEvent){

            if(mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED){
                x = mouseEvent.getX();
                y = mouseEvent.getY();
            }

            if(mouseEvent.getEventType() == MouseEvent.MOUSE_DRAGGED
                && mouseEvent.getX() != x && mouseEvent.getY() != y) {
                Object line = new LineSerialized((int)x, (int)y, (int)mouseEvent.getX(), (int)mouseEvent.getY());

                x = mouseEvent.getX();
                y = mouseEvent.getY();

                try {
                    out.writeObject(line);
                } catch(IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    };

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Client");
        primaryStage.setOnCloseRequest(windowHandler);

        canvas.setWidth(800);
        canvas.setHeight(600);

        root.getChildren().add(canvas);

        Scene scene = new Scene(root);
        scene.setOnMousePressed(mouseHandler);
        scene.setOnMouseDragged(mouseHandler);

        primaryStage.setScene(scene);
        primaryStage.show();

        listener.start();

    }
}
