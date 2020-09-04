package com.otmane.app.controlleurs;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;
import com.otmane.app.dataBase.MysqlConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import static com.otmane.app.controlleurs.JavaQuizController.Current_Question_Creator;

public class ContactProfesseurController extends MysqlConnection implements Initializable {


    public Label ProfessorNameContact; 
    public Label ProfessorMatiereContact;
    public JFXButton ProfessorMailContact;
    public Label ProfessorNumber;

    @Override
        public void initialize(URL url, ResourceBundle rb) {
            try
            {
                Connection con1 = getConnection();
                String sqlInfos = "select name,Matiere,Mail from user_professeur where username='"+Current_Question_Creator+"'";
                PreparedStatement ps1 = con1.prepareStatement(sqlInfos);
                ResultSet rs = ps1.executeQuery();
                while(rs.next())
                {
                    ProfessorNameContact.setText(" C'est Le Professeur : "+rs.getString("name"));
                    ProfessorMatiereContact.setText(" Sa  Matière  est : "+rs.getString("Matiere"));
                    ProfessorMailContact.setText(" Son Email est : "+rs.getString("Mail"));
                }

            }catch (SQLException ex)
            {
                System.out.println("problem of sql from infoline");
            }
            }

        @FXML
        void OpenLinkedin() {
            try {
                Desktop.getDesktop().browse(new URI("https://www.linkedin.com/in/otmane-er-ragragui-953b26183"));
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(ContactProfesseurController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }