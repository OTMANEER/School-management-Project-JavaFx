package com.otmane.app.controlleurs;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerHome implements Initializable {

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnStudents;

    @FXML
    private Button btnProfile;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnClasses;

    //suivre l'ction de la souris
    @FXML
    private void handleButtonClicks(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == btnDashboard) {
            loadStage("/FXMLS/Statistique.fxml");
        } else if (mouseEvent.getSource() == btnStudents) {
            loadStage("/FXMLS/Etudiant.fxml");
        } else if (mouseEvent.getSource() == btnProfile) {
            loadStage("/FXMLS/ProfessorProfile.fxml");
        }else if (mouseEvent.getSource() == btnClasses)
        {
            loadStage("/FXMLS/AjouterEtudiant.fxml");
        }else if(mouseEvent.getSource() == btnUpdate)
        {
            loadStage("/FXMLS/AjouterQuestion.fxml");
        }else if(mouseEvent.getSource() == btnSettings)
        {
            Platform.exit();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    private void loadStage(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image("images/icon.png"));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
