package bootup_And_Configuration.Java_Cts;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Welcome implements Initializable {

    @FXML
    private StackPane welcome;

    @FXML
    private Label clickToContinue;

    private boolean clicked = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        welcome.setOpacity(0);

        FadeTransition ft = new FadeTransition(Duration.seconds(2), welcome);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();

        LoadFirstTransition();
    }

    private void LoadFirstTransition() {
        FadeTransition ft = new FadeTransition(Duration.seconds(1), clickToContinue);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.setOnFinished(event -> LoadSecondTransition());
        ft.play();
    }

    private void LoadSecondTransition() {
        FadeTransition ft = new FadeTransition(Duration.seconds(1), clickToContinue);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setOnFinished(event -> LoadFirstTransition());
        ft.play();
    }

    public void Load_Game_Profile_Configuration() {
        if (!clicked) {
            FadeTransition ft = new FadeTransition(Duration.seconds(2), welcome);
            ft.setFromValue(1);
            ft.setToValue(0);

            ft.setOnFinished(event -> {
                try {
                    Stage stage = (Stage) welcome.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/bootup_And_Configuration/FXML_Files/Game_Profile_Configuration_And_Game_Menu.fxml"));

                    Scene sc = new Scene(loader.load());
                    sc.getStylesheets().setAll("/stylesheets/stylesheet.css");

                    stage.setTitle("Virtual Hand Cricket");
                    stage.setScene(sc);
                    stage.setResizable(false);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            ft.play();
            clicked = true;
        }
    }
}