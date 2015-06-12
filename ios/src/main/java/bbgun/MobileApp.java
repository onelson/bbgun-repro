package bbgun;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class MobileApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("BBGun");
        BBGunApp app = new BBGunApp();
        Scene scene = new Scene(app.delegate());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
