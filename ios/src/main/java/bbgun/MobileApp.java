package bbgun;


import javafx.application.Application;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class MobileApp extends Application {

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("BBGun");
        AnchorPane appRoot = new AnchorPane();

        bbgun.BBGunApp app = new bbgun.BBGunApp();
        Pane appPane = app.delegate();
        appRoot.getChildren().add(appPane);

        AnchorPane.setTopAnchor(appPane, 20.0);
        AnchorPane.setBottomAnchor(appPane, 0.0);

        Scene scene = new Scene(appPane);
        scene.getStylesheets().add("bbgun/main.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
