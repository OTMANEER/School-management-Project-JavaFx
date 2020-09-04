package com.otmane.app.controlleurs;

import com.jfoenix.controls.JFXComboBox;
import com.otmane.app.dataBase.MysqlConnection;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.otmane.app.controlleurs.FXMLDocumentController.Current_user;

public class ProfessorProfileController extends MysqlConnection implements Initializable {

    @FXML
    private TextField ProfessorLastName;
    @FXML
    private TextField ProfessorFirstName;
    @FXML
    private TextField ProfessorMail;
    @FXML
    private TextField ProfessorPassword;
    @FXML
    private JFXComboBox<String>  ProfessorMatiere;
    @FXML
    private Label lbrErrors;
    @FXML
    private Label ProfessorInformations;

    private static String infoLine="null";


    void loadCombo()
    {
        String  modules= new String("");
        try {
            Connection connection = getConnection();
            String sql = "select Matiere from user_professeur where username=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,Current_user);
            ResultSet rs = ps.executeQuery();

            int i=0;
            while (rs.next())
            {
                modules = rs.getString("Matiere");
            }
        }catch (SQLException ex)
        {
            System.out.println("cannot bring Matier_Professor ");
        }
        ProfessorMatiere.setItems(FXCollections.observableArrayList(modules));
    }

    @FXML
    public void SaveProfessor(ActionEvent actionEvent) {
        String firstName = ProfessorFirstName.getText();
        String lastName = ProfessorLastName.getText();
        String mail= ProfessorMail.getText();
        String password= ProfessorPassword.getText();
        String matiere = ProfessorMatiere.getSelectionModel().getSelectedItem();
        System.out.println( ProfessorFirstName.getText() +" "+ProfessorLastName.getText() +""+ ProfessorMail.getText() +""+ProfessorPassword.getText()  +""+ ProfessorMatiere.getSelectionModel().getSelectedItem());
        String fullName = firstName +" "+ lastName;

        try{
            if(!(firstName.isEmpty() && lastName.isEmpty() && mail.isEmpty() && matiere.isEmpty())  )
            {
                try {

                    Connection con = getConnection();
                    String current = "UPDATE `user_professeur` SET  `username`='"+firstName+"' ,`password`='"+password+"' , `name`='"+fullName+"', `Matiere`= '"+matiere+"', `Mail`= '"+mail+"' WHERE `username`='"+Current_user+"'" ;
                    PreparedStatement ps = con.prepareStatement(current);
                    ps.execute();
                    setLblError(Color.GREEN, "Saving Professor Successfully..");
                } catch (SQLException | ArrayIndexOutOfBoundsException  ex ) {
                    System.out.println("we tried to add your teacher!");
                    setLblError(Color.TOMATO, "Essayer de  Remplir les champs correctement! ");
                }finally {
                    ProfessorPassword.setText("");
                    ProfessorFirstName.setText("");
                    ProfessorLastName.setText("");
                    ProfessorMail.setText("");

                }
            }else {
                setLblError(Color.TOMATO, "Problème de connexion avec la base de données");
            }
        }catch (RuntimeException ex)
        {
            System.out.println("inputs of the professor do this problem");
            setLblError(Color.TOMATO, "Essayer de Remplir les champs correctement! ");
        }
    }

    public void Cancel(ActionEvent actionEvent) {
         Stage stage = (Stage) ProfessorFirstName.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadCombo();
      try
      {
          Connection con1 = getConnection();
          String sqlInfos = "select name,Matiere,Mail from user_professeur where username='"+Current_user+"'";
          PreparedStatement ps1 = con1.prepareStatement(sqlInfos);

          ResultSet rs = ps1.executeQuery();
          while (rs.next())
          {
              infoLine = " Bonjour Le Professeur:  \""+rs.getString("name")+"\", de la Matière: \""+rs.getString("Matiere") +"\",  Votre Mail actuel est:  \""+rs.getString("Mail")+"\"  Vous pouvez les changés Maintenant ";
          }
      }catch (SQLException ex)
      {
          System.out.println("sql from info line");
      }
        ProfessorInformations.setTextFill(Color.GREEN);
        ProfessorInformations.setText(infoLine);
        System.out.println(infoLine);
    }
    private void setLblError(Color color, String text) {
        lbrErrors.setTextFill(color);
        lbrErrors.setText(text);
        System.out.println(text);
    }
}
