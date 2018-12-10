import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.*;
import java.net.*;

public class Sender extends Application {

    private double x;
    private double y;

    private static Socket echoSocket = null;
    private static ObjectOutputStream out = null;

    private Group group = new Group();

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Drawer");

        Rectangle background= new Rectangle(0,0,800,600);
        background.setFill(Color.WHITE);
        group.getChildren().add(background);


        Scene scene = new Scene(group, 800, 600);
        scene.setOnMousePressed(mouseHandler);
        scene.setOnMouseReleased(mouseHandler);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {


        try {
            echoSocket = new Socket("localhost", 6666);
            out = new ObjectOutputStream(echoSocket.getOutputStream());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                    + "the connection to: localhost.");
            System.exit(1);
        }

        launch();

        out.close();
        echoSocket.close();
    }

    EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent mouseEvent){


            if(mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED){
                x = mouseEvent.getX();
                y = mouseEvent.getY();
            }

            if(mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED){
                Object line = new LineSerialized((int)x, (int)y, (int)mouseEvent.getX(), (int)mouseEvent.getY());
                group.getChildren().add(new Line(x,y,mouseEvent.getX(), mouseEvent.getY()));

                try {
                    out.writeObject(line);
                } catch(IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    };
}
