package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage thePrimaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Open.fxml"));
        primaryStage.setTitle("Chef Script Builder");
        primaryStage.setScene(new Scene(root, 600, 275));
        primaryStage.show();

        thePrimaryStage = primaryStage;
    }




    public static void main(String[] args) {
        launch(args);
    }
}
