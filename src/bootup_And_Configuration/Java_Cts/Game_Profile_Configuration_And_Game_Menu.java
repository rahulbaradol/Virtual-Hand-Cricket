package bootup_And_Configuration.Java_Cts;

import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Game_Profile_Configuration_And_Game_Menu implements Initializable {

    @FXML
    private StackPane gameProfileConfiguration;

    @FXML
    private AnchorPane gameProfileConfiguration_AnchorPane, game_Menu;

    @FXML
    private JFXTextField destination;

    private boolean clicked = false;

    // Menu Variables

    @FXML
    private Label sample;

    // Menu Variables

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game_Menu.setOpacity(0);
        gameProfileConfiguration.setOpacity(0);

        FadeTransition ft = new FadeTransition(Duration.seconds(2), gameProfileConfiguration);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    public void proceed() {
        File profile = new File(destination.getText());

        if (profile.exists()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(profile));
                String line = bufferedReader.readLine();

                if (line.equals("This is a valid Virtual Hand Cricket Game profile.")) {
                    gameProfileConfiguration_AnchorPane.setDisable(true);

                    FadeTransition ft = new FadeTransition(Duration.seconds(2), gameProfileConfiguration_AnchorPane);
                    ft.setFromValue(1);
                    ft.setToValue(0);
                    ft.setOnFinished(event -> Load_Game_Menu());
                    ft.play();
                } else {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Virtual Hand Cricket");
                    error.setHeaderText(null);
                    error.setContentText("Invalid profile defined. Please try again.");
                    error.showAndWait();
                }
            } catch (IOException e) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Virtual Hand Cricket");
                error.setHeaderText(null);
                error.setContentText("Invalid profile defined. Please try again.");
                error.showAndWait();
            }
        } else if (!profile.exists()) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Virtual Hand Cricket");
            error.setHeaderText(null);
            error.setContentText("Invalid profile defined. Please try again.");
            error.showAndWait();
        }
    }

    private void Load_Game_Menu() {
        FadeTransition ft = new FadeTransition(Duration.seconds(2), game_Menu);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setOnFinished(event -> sample.setText(destination.getText()));
        ft.play();
    }

    public void create_A_New_One() {
        if (!clicked) {
            gameProfileConfiguration.setDisable(true);

            FadeTransition ft = new FadeTransition(Duration.seconds(2), gameProfileConfiguration);
            ft.setFromValue(1);
            ft.setToValue(0);

            ft.setOnFinished(event -> {
                try {
                    Stage stage = (Stage) gameProfileConfiguration.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/bootup_And_Configuration/FXML_Files/Create_A_New_Profile.fxml"));

                    Scene sc = new Scene(loader.load());
                    sc.getStylesheets().setAll("/stylesheets/stylesheet.css");

                    stage.setTitle("Virtual Hand Cricket - Create A New Profile");
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

    public void browse() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            destination.setText(file.getAbsolutePath());
        }
    }

    public void exit() {
        Alert exit = new Alert(Alert.AlertType.CONFIRMATION);
        exit.setTitle("Virtual Hand Cricket - Exit");
        exit.setHeaderText(null);
        exit.setContentText("Are you sure you want to exit?");

        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");
        exit.getButtonTypes().setAll(yes, no);

        Optional<ButtonType> confirmation = exit.showAndWait();

        if (confirmation.get() == yes) {
            System.exit(0);
        } else if (confirmation.get() == no) {
            // Nothing happens here
        }
    }

}