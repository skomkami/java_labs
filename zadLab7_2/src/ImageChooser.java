import java.io.File;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.*;

import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageChooser extends Application {

    private int columns;
    private int imgSize;
    protected DirectoryChooser directoryChooser = new DirectoryChooser();

    public ImageChooser(int columns, int imgSize) throws Exception{

        if(imgSize < 20 || imgSize > 1920)
            throw new Exception("Wrong image with. Choose one between 20 and 1920");

        this.imgSize = imgSize;

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        if(columns < 1 || columns > primaryScreenBounds.getWidth() / imgSize)
            throw new Exception("Columns won't fit in the screen");

        this.columns = columns;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        directoryChooser.setTitle("Open Resource File");

        Stage stage = new Stage();
        GridPane grid = new GridPane();

        grid.setPadding(new Insets( 10, 10, 10, 10));
        grid.setHgap(5);
        grid.setVgap(5);

        File file = directoryChooser.showDialog(primaryStage);

        final FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Image Files", "png", "jpg", "gif");

        int i=0;

        for (File child : file.listFiles()) {
            if(extensionFilter.accept(child)) {

                Image img = new Image("file:"+child.getAbsolutePath());
                ImageView view = new ImageView(img);
                view.setOnMouseClicked(e -> {
                    new ImageViewer(stage, img);
                });

                if(img.getWidth() >= img.getHeight())
                    view.setFitWidth(imgSize);
                else
                    view.setFitHeight(imgSize);

                view.setPreserveRatio(true);

                GridPane.setConstraints(view, i%columns, i/columns);
                grid.getChildren().add(view);
                ++i;
            }
        }

        if(i == 0)
            throw new Exception("Select directory with pictures");

        Scene picScene = new Scene(grid);

        stage.setTitle("JavaFX PictureOpener");
        stage.setScene(picScene);
        stage.show();

    }

}