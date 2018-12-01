import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.*;

import java.sql.SQLException;

public class DatabaseGUI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Stage stage = new Stage();
        GridPane grid = new GridPane();

        grid.setPadding(new Insets( 10, 10, 10, 10));
        grid.setHgap(5);
        grid.setVgap(5);

        Label dbLabel = new Label("AGH database name:");
        grid.add(dbLabel, 0, 1);

        TextField dbField = new TextField("database");
        grid.add(dbField, 1, 1);

        Label userLabel = new Label("Login:");
        grid.add(userLabel, 0, 2);

        TextField userField = new TextField("username");
        grid.add(userField, 1, 2);

        Label passLabel = new Label("Password:");
        grid.add(passLabel, 0, 3);

        PasswordField passField = new PasswordField();
        passField.setPromptText("Your password");
        grid.add(passField, 1, 3);

        Button btn = new Button("Log in");
        grid.add(btn, 1, 4);

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {

                String db = dbField.getText();
                String user = userField.getText();
                String pass = passField.getText();

                DB conn;
                try{
                    conn = new DB(db, user, pass);

                    new Menu(conn);
                }catch (SQLException ex) {
                    new ErrorWindow(ex);
                }

                stage.close();
            }
        });


        Scene scene = new Scene(grid);

        stage.setTitle("Database Login");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {

        launch();

    }

}