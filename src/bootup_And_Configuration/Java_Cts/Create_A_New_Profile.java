package bootup_And_Configuration.Java_Cts;

import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Create_A_New_Profile implements Initializable {

    @FXML
    private StackPane create_A_New_One;

    @FXML
    private JFXTextField destination_ProfileName;

    private boolean clicked = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        create_A_New_One.setOpacity(0);

        FadeTransition ft = new FadeTransition(Duration.seconds(2), create_A_New_One);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    public void create() throws FileNotFoundException {
        File profile = new File(destination_ProfileName.getText());
        File profile_MainStarter = new File(destination_ProfileName.getText() + "/Main.vhc");
        File profile_StatusDirectory = new File(destination_ProfileName.getText() + "/Status");

        if (!profile.exists()) {
            profile.mkdir();

            if (!profile_MainStarter.exists()) {
                PrintWriter pw = new PrintWriter(profile_MainStarter);
                pw.println("This is a valid Virtual Hand Cricket Game profile.");
                pw.println("Copyright (C)");
                pw.close();

                if (!profile_StatusDirectory.exists()) {
                    profile_StatusDirectory.mkdir();

                    Alert workDone = new Alert(Alert.AlertType.INFORMATION);
                    workDone.setTitle("Virtual Hand Cricket");
                    workDone.setHeaderText(null);
                    workDone.setContentText("Congratulations!! Your game profile has been created successfully!!");
                    workDone.showAndWait();

                    create_A_New_One.setDisable(true);

                    FadeTransition ft = new FadeTransition(Duration.seconds(2), create_A_New_One);
                    ft.setFromValue(1);
                    ft.setToValue(0);
                    ft.setOnFinished(event -> {
                        try {
                            Stage stage = (Stage) create_A_New_One.getScene().getWindow();
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
                } else {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Virtual Hand Cricket");
                    error.setHeaderText(null);
                    error.setContentText("Error creating the profile.\nIt might not happen because of some unknown error or the profile or directory name that you have mentioned already exists.");
                    error.showAndWait();
                }
            } else {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Virtual Hand Cricket");
                error.setHeaderText(null);
                error.setContentText("Error creating the profile.\nIt might not happen because of some unknown error or the profile or directory name that you have mentioned already exists.");
                error.showAndWait();
            }
        } else {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Virtual Hand Cricket");
            error.setHeaderText(null);
            error.setContentText("Error creating the profile.\nIt might not happen because of some unknown error or the profile or directory name that you have mentioned already exists.");
            error.showAndWait();
        }
    }

    public void browse() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            destination_ProfileName.setText(file.getAbsolutePath());
        }
    }

    public void clear() {
        destination_ProfileName.setText("");
    }

    public void back() {
        if (!clicked) {
            create_A_New_One.setDisable(true);

            FadeTransition ft = new FadeTransition(Duration.seconds(2), create_A_New_One);
            ft.setFromValue(1);
            ft.setToValue(0);

            ft.setOnFinished(event -> {
                try {
                    Stage stage = (Stage) create_A_New_One.getScene().getWindow();
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