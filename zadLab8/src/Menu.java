import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Menu {
    private Stage stage;
    private Scene scene;
    private GridPane pane;

    DB conn;

    public Menu(DB connection) {

        this.conn = connection;

        stage = new Stage();

        pane = new GridPane();

        pane.setPadding(new Insets( 10, 10, 10, 10));
        pane.setHgap(5);
        pane.setVgap(5);

        Label allBooksLabel = new Label("View all books");
        allBooksLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20");
        GridPane.setHalignment(allBooksLabel, HPos.CENTER);
        pane.add(allBooksLabel, 0, 0, 2, 1);

        Button btn = new Button("View");
        GridPane.setHalignment(btn, HPos.CENTER);
        pane.add(btn, 0, 1, 2, 1);

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {

                ArrayList<ArrayList<String>> rel = conn.getRows("SELECT * FROM books;");

                new RelationViewer(rel);

            }
        });


        Label addBookLabel = new Label("Add a book");
        addBookLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20");
        GridPane.setHalignment(addBookLabel, HPos.CENTER);
        pane.add(addBookLabel, 0, 2, 2, 1);

        Label isbnLabel = new Label("Enter isbn: ");
        pane.add(isbnLabel, 0, 3);

        TextField isbnField = new TextField();
        pane.add(isbnField, 1, 3);

        Label titleLabel = new Label("Enter title: ");
        pane.add(titleLabel, 0, 4);

        TextField titleField = new TextField();
        pane.add(titleField, 1, 4);

        Label authorLabel = new Label("Enter author: ");
        pane.add(authorLabel, 0, 5);

        TextField authorField = new TextField();
        pane.add(authorField, 1, 5);

        Label yearLabel = new Label("Enter year: ");
        pane.add(yearLabel, 0, 6);

        TextField yearField = new TextField();
        pane.add(yearField, 1, 6);


        Button btn2 = new Button("Add to database");
        GridPane.setHalignment(btn2, HPos.CENTER);
        pane.add(btn2, 0, 7, 2, 1);

        btn2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {

                String query = "INSERT INTO books VALUES(" + isbnField.getText() + ", '" + titleField.getText()
                        + "', '" + authorField.getText() + "', " + yearField.getText() + ");";

                conn.addRow(query);

                isbnField.clear();
                titleField.clear();
                authorField.clear();
                yearField.clear();

            }
        });

        Label searchByLabel = new Label("Search for book by author or isbn");
        searchByLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20");
        GridPane.setHalignment(searchByLabel, HPos.CENTER);
        pane.add(searchByLabel, 0, 8, 2, 1);

        Label searchAuthorLabel = new Label("Author: ");
        pane.add(searchAuthorLabel, 0, 9);

        TextField searchAuthorField = new TextField();
        pane.add(searchAuthorField, 1, 9);

        Label searchIsbnLabel = new Label("ISBN: ");
        pane.add(searchIsbnLabel, 0, 10);

        TextField searchIsbnField = new TextField();
        pane.add(searchIsbnField, 1, 10);

        Button btn3 = new Button("Search");
        GridPane.setHalignment(btn3, HPos.CENTER);
        pane.add(btn3, 0, 11, 2, 1);

        btn3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {

                String query = "SELECT * FROM books";

                boolean authorIsEmpty = searchAuthorField.getText() == null || searchAuthorField.getText().trim().isEmpty();
                boolean isbnIsEmpty = searchIsbnField.getText() == null || searchIsbnField.getText().trim().isEmpty();
                if ( !authorIsEmpty || !isbnIsEmpty){
                    query += " where";

                    if(!authorIsEmpty)
                        query += " author like '%" + searchAuthorField.getText() + "'";

                    if ( !authorIsEmpty && !isbnIsEmpty)
                        query += " and";

                    if(!isbnIsEmpty)
                        query += " isbn = " + searchIsbnField.getText();

                }

                query += ";";

                ArrayList<ArrayList<String>> row = conn.getRows(query);

                new RelationViewer(row);

            }
        });

        scene = new Scene(pane);

        stage.setTitle("Database Menu");
        stage.setScene(scene);
        stage.show();

    }


}
