package com.otmane.app.controlleurs;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import com.otmane.app.dataBase.DbConnectionLogin;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.omg.PortableServer.THREAD_POLICY_ID;

/**
 *
 * @author OTMANE
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private JFXTextField usernameField;

    @FXML
    private HBox parent;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private Label lblErrors;

    @FXML
    private JFXCheckBox checked_proffeseur,checked_étudiant;
    private boolean flag = false;
    private  String dataBaseName="";
    public static   String Current_user;

    @FXML
    private void btnLog() throws SQLException, InterruptedException {
        System.out.println(" clicked now");
        if(checked_proffeseur.isSelected())
            dataBaseName = "professeur";
            else if(checked_étudiant.isSelected())
            dataBaseName = "étudiant";
        try  {
            int status=1;
            if(!usernameField.getText().isEmpty() && !passwordField.getText().isEmpty() && dataBaseName!="")
            {
                status =DbConnectionLogin.tryConnect(usernameField.getText().trim().toLowerCase(),passwordField.getText(),dataBaseName);
            }
            switch(status)
            {
                case 0 :{
                    setLblError(Color.GREEN, "Login Successful..Redirecting..");
                    flag = true;
                  if(checked_proffeseur.isSelected())
                  {
                      Current_user = usernameField.getText();
                      System.out.println(Current_user);
                      loadStageLogin("/FXMLS/HomeProfessor.fxml");
                  }else if(checked_étudiant.isSelected())
                  {
                      Current_user = usernameField.getText();
                      System.out.println(Current_user);
                      loadStageLogin("/FXMLS/JavaQuiz.fxml");
                  }
                    //   Stage  stage = (Stage) usernameField.getScene().getWindow();
                    // Parent root = FXMLLoader.load(getClass().getResource(""));
                    //stage.setScene(new Scene(root));
                }
                break;
                case 1: setLblError(Color.TOMATO, "Enter Correct Email/Password");
                    break;
                case -1:  setLblError(Color.TOMATO, "Failled to connect with database");
                    break;
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void btnExit()
    {
        Platform.exit();
    }

    private void setLblError(Color color, String text) {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
    }

    @FXML
    public void radioClicked(javafx.event.ActionEvent actionEvent) {
        checked_proffeseur.setSelected(false);
        checked_étudiant.setSelected(false);
        ((JFXCheckBox)actionEvent.getSource()).setSelected(true);
    }
    private void loadStageLogin(String fxml) throws InterruptedException {
        try {
            Stage stage = (Stage) usernameField.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image("images/icon.png"));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
