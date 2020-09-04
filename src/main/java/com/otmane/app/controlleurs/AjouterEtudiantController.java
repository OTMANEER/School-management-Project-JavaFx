package com.otmane.app.controlleurs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.otmane.app.dataBase.MysqlConnection;
import com.sun.org.apache.regexp.internal.RESyntaxException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class AjouterEtudiantController extends MysqlConnection {
    @FXML
    private TextField nameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label lbrErrors;
    @FXML
    private void AjouterEtudiant(){
        System.out.println("  send now ");
        nameField.setStyle("-fx-text-box-border: #abc4a1;");
        passwordField.setStyle("-fx-text-box-border: #abc4a1;");
        String fullName = nameField.getText();
    try{
        String[] currencies = fullName.split(" ");
        System.out.println(currencies[0]+"  and  "+currencies[1]);
       if(!(nameField.getText().isEmpty() && passwordField.getText().isEmpty()) && currencies[1]!= "" )
       {
        try {
            boolean exist = false;
            String password =String.valueOf(passwordField.getText());
            System.out.println(String.valueOf(passwordField.getText()));
            Connection con = getConnection();
            Connection con1 = getConnection();
            String CheckExisting = "SELECT name FROM `user_student`";
            PreparedStatement ps1 = con1.prepareStatement(CheckExisting);
            String current = "INSERT INTO`user_student` SET `username`='"+currencies[0]+"' ,`password`='"+password+"', `name`='"+fullName+"'";
            PreparedStatement ps = con.prepareStatement(current);
            ResultSet rs = ps1.executeQuery();
            while (rs.next())
            {
                if(rs.getString("name").toLowerCase().equals(fullName.toLowerCase())){
                    exist = true;
                    System.out.println("exists"+exist);
                    setLblError(Color.TOMATO, "L'etudiant est déjà dans la base de données..");
                    nameField.setStyle("-fx-text-box-border: #B22222;");
                    break;
                }
            } 
            System.out.println(" exists "+ exist);
            if (exist == false)
            {
                ps.execute();
                setLblError(Color.GREEN, "Etudiant Ajouté..");
                nameField.setStyle("-fx-text-box-border: #4ec427;");
                passwordField.setStyle("-fx-text-box-border: #4ec427;");

            }
        } catch (SQLException | ArrayIndexOutOfBoundsException  ex ) {
            System.out.println("we tried to add your student!  we fail");
        }finally {
            passwordField.setText("");
            nameField.setText("");
        }
       }else {
           setLblError(Color.TOMATO, "Pas de connexion");
           nameField.setStyle("-fx-text-box-border: #B22222;");
       }
    }catch (RuntimeException ex)
    {
        System.out.println("split method doesn't work otmane");
        setLblError(Color.TOMATO, "Les champs ne correspond pas! ");
        nameField.setStyle("-fx-text-box-border: #B22222;");
        passwordField.setStyle("-fx-text-box-border: #B22222;");
    }
    }

    private void setLblError(Color color, String text) {
        lbrErrors.setTextFill(color);
        lbrErrors.setText(text);
        System.out.println(text);
    }

    public void SupprimerEtudiant(ActionEvent actionEvent) {
        System.out.println(" deleting now ");
        String fullName = nameField.getText();

        if(!(nameField.getText().isEmpty()))
        {
            try {
                passwordField.setText("");
                Connection con = getConnection();
             String current = "DELETE user_student,result_student FROM user_student INNER JOIN result_student WHERE user_student.name = result_student.name AND user_student.name ='"+fullName+"'";
            //   String current = "delete user_student from user_student where name = ? ; delete result_student from result_student where name = ?";
                PreparedStatement ps = con.prepareStatement(current);
               // ps.setString(1,fullName);
                ps.execute();
                Connection con1 = getConnection();
                String deleteFromUserStudentOnly = "delete user_student from user_student where name = ? ";
                PreparedStatement ps1 = con1.prepareStatement(deleteFromUserStudentOnly);
                ps1.setString(1,fullName);
                ps1.execute();
                setLblError(Color.GREEN, ""+fullName+" Est Supprimé");
                nameField.setStyle("-fx-text-box-border: #60d40b;");
            } catch (SQLException| ArrayIndexOutOfBoundsException  ex ) {
                System.out.println("we tried to delete your student!  we fail");
                nameField.setStyle("-fx-text-box-border: #B22222;");
            }finally {
                passwordField.setText("");
                nameField.setText("");
            }
        }else {
            setLblError(Color.TOMATO,  ""+fullName+" N'Est Supprimé");
            System.out.println("we tried to delete your student!  we fail");
            nameField.setStyle("-fx-text-box-border: #B22222;");
        }
    }
}