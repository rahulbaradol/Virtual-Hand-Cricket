package bootup_And_Configuration;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class StartUp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bootup_And_Configuration/FXML_Files/Welcome.fxml"));
            Scene sc = new Scene(loader.load());

            primaryStage.setTitle("Virtual Hand Cricket - Welcome");
            primaryStage.setScene(sc);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        Alert talk = new Alert(Alert.AlertType.INFORMATION);
        talk.setTitle("Virtual Hand Cricket");
        talk.setHeaderText(null);
        talk.setContentText("Thanks for playing Virtual Hand Cricket!! \nGood bye. See you soon.");
        talk.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}