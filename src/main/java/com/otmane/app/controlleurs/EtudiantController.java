package com.otmane.app.controlleurs;

import com.otmane.app.dataBase.MysqlConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EtudiantController extends MysqlConnection implements Initializable {
    @FXML
    private TableView<StudentsModel> tbData;
    @FXML
    public TableColumn<StudentsModel, Integer> studentId;

    @FXML
    public TableColumn<StudentsModel, String> firstName;

    @FXML
    public TableColumn<StudentsModel, String> lastName;
    @FXML
    public TableColumn<StudentsModel, String> score;

    private ObservableList<StudentsModel> studentsModels = FXCollections.observableArrayList();
    public EtudiantController()
    {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

     try{
            Connection con = getConnection();
         //   String current = "SELECT id,name,test_id,score FROM result_student ORDER BY score DESC";
            String current = "SELECT id,name,test_id,CAST(score as DECIMAL(9,2)) _totalBal FROM result_student ORDER BY _totalBal DESC";
            PreparedStatement ps = con.prepareStatement(current);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                //String tmp = rs.getString("name");
            //    String[] currencies = tmp.split(" ");
             //   System.out.println(currencies[0]+"  and  "+currencies[1]);
                StudentsModel currentStudent = new StudentsModel(rs.getInt("id"),rs.getString("name"),String.valueOf(rs.getInt("test_id")),rs.getString("_totalBal"));
                studentsModels.add(currentStudent);
            }
        }catch(SQLException e)
        {
            System.out.println("cannot insert result into database in loadingStudents at the statistic controller");
        }finally {
            System.out.println("from the finally bloc in the insertion step statistic controller loadstudent");
        }


        studentId.setCellValueFactory(new PropertyValueFactory<>("StudentId"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        score.setCellValueFactory(new PropertyValueFactory<>("score"));
        tbData.setItems(studentsModels);
    }


    // new StudentsModel(1,"Amos", "Chepchieng"),

}
