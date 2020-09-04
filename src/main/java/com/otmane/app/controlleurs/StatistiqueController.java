package com.otmane.app.controlleurs;

import com.otmane.app.dataBase.MysqlConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StatistiqueController extends MysqlConnection implements Initializable {
    @FXML
    private TableView<StudentsModel> tbData;
    @FXML
    public TableColumn<StudentsModel, Integer> studentId;

    @FXML
    public TableColumn<StudentsModel, String> firstName;

    @FXML
    public TableColumn<StudentsModel, String> lastName;

    @FXML
    private PieChart pieChart;
    @FXML
    Label NombreEtudiants;
    @FXML
    Label NombreQuestions;
    @FXML
    Label NombreProfesseurs;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadChart();
        loadStudents();
    }
    private void loadChart()
    {
        try{
            PieChart.Data slice1 = null,slice2=null,slice3=null;
            Connection con = getConnection();


            String allCounted ="SELECT  (SELECT COUNT(*) FROM   user_student) AS count1, (SELECT COUNT(*)FROM   user_professeur) AS count2, (SELECT COUNT(*)from questions)AS count3 FROM  dual";
            PreparedStatement ps= con.prepareStatement(allCounted);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                slice1 = new PieChart.Data("Etudiant"  , rs.getInt("count1"));
                NombreEtudiants.setText( String.valueOf(rs.getInt("count1")));
                System.out.println(rs.getInt("count1"));
                slice2 = new PieChart.Data("Professeur"  , rs.getInt("count2"));
                NombreProfesseurs.setText( String.valueOf(rs.getInt("count2")));
                System.out.println(rs.getInt("count2"));
                slice3 = new PieChart.Data("Questions"  , rs.getInt("count3"));
                NombreQuestions.setText( String.valueOf(rs.getInt("count3")));
                System.out.println(rs.getInt("count3"));
            }

            pieChart.getData().add(slice1);
            pieChart.getData().add(slice2);
            pieChart.getData().add(slice3);
        }catch(SQLException e)
        {
            System.out.println("cannot insert result into database in loadingStudents at the statistic controller");
        }finally {
            System.out.println("from the finally bloc in the insertion step statistic controller chart part ");
        }
    }
    private static ObservableList<StudentsModel> studentsModels = FXCollections.observableArrayList();
/*          new StudentsModel(1,"Amos", "Chepchieng"),
            new StudentsModel(2,"Amos", "Mors"),
            new StudentsModel(3,"Amos", "Chepchieng"),
            new StudentsModel(4,"Amos", "Mors"),
            new StudentsModel(1,"Amos", "Chepchieng"),
            new StudentsModel(2,"Amos", "Mors"),
            new StudentsModel(3,"Amos", "Chepchieng"),
            new StudentsModel(4,"Amos", "Mors")
            */
    private void loadStudents()
    {
        try{
            Connection con = getConnection();
            String current = "SELECT id,name FROM user_student  ORDER BY id ASC";
            PreparedStatement ps = con.prepareStatement(current);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                String tmp = rs.getString("name");
                String[] currencies = tmp.split(" ");
                System.out.println(currencies[0]+"  and  "+currencies[1]);
                StudentsModel currentStudent = new StudentsModel(rs.getInt("id"),currencies[0],currencies[1]);
                studentsModels.add(currentStudent);
            }
        }catch(SQLException e)
        {
            System.out.println("cannot insert result into database in loadingStudents at the statistic controller");
        }finally {
            System.out.println("from the finally bloc in the insertion step statistic controller loadstudent");
        }
       // studentsModels.add(new StudentsModel(1,"otmane","erragragui"));
        studentId.setCellValueFactory(new PropertyValueFactory<>("StudentId"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        tbData.setItems(studentsModels);
    }
}
