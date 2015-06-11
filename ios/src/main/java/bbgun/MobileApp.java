package bbgun;

import java.net.IDN;
import javafx.application.Application;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Scene;



public class MobileApp extends Application {

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("BBGun");
        AnchorPane appRoot = new AnchorPane();
        Scene scene = new Scene(appRoot);
        primaryStage.setScene(scene);
        IDN.toASCII("api.github.com");
        primaryStage.show();
    }
}
