import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ImageViewer {

    public ImageViewer(Stage stage, Image image) {
        Stage displayStage = new Stage();

        displayStage.initStyle(StageStyle.UTILITY);
        displayStage.initModality(Modality.APPLICATION_MODAL);
        displayStage.initOwner(stage);

        ImageView imgV = new ImageView(image);

        if(image.getWidth() >= image.getHeight())
            imgV.setFitWidth(1200);
        else
            imgV.setFitHeight(1200);

        imgV.setPreserveRatio(true);

        displayStage.setScene(
                new Scene(
                        new Group(imgV)
                )
        );
        displayStage.show();
    }

}
