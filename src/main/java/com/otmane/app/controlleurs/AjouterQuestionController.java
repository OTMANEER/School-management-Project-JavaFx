package com.otmane.app.controlleurs;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.otmane.app.dataBase.MysqlConnection;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import javax.xml.transform.Result;
import java.nio.file.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.otmane.app.controlleurs.FXMLDocumentController.Current_user;

public class AjouterQuestionController extends MysqlConnection implements Initializable {


  @FXML
    private JFXComboBox<Integer> testIdField;
    @FXML
    private TextArea questionField;
    @FXML
    private TextField reponse1Field;
    @FXML
    private TextField reponse2Field;
    @FXML
    private TextField reponse3Field;
    @FXML
    private TextField reponse4Field;
    @FXML
    private TextField solutionField;
    @FXML
    private AnchorPane videoField;
    @FXML
    private HBox videoChooserPane;
    @FXML
    private JFXButton chooseMyVideobtn;
    @FXML
    private Label videoExtention;

    private Desktop desktop = Desktop.getDesktop();
    private static  int  current_video_number=0;

    private  Path video_choosed_path;
    @FXML
    private Label lbrErrors;

  void loadCombo(Integer [] a)
  {
      testIdField.setItems(FXCollections.observableArrayList(a));
  }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Integer [] items = {1,2,3,4,5,6};
        loadCombo(items);

    }

    @FXML
    private void AjouterQuestion(){
        System.out.println("  ajouter question en cours  ");

if(!reponse1Field.getText().isEmpty() && !reponse2Field.getText().isEmpty() && !reponse3Field.getText().isEmpty() && !reponse4Field.getText().isEmpty() && !solutionField.getText().isEmpty() && (!videoExtention.getText().isEmpty() || !questionField.getText().isEmpty()) && !testIdField.getSelectionModel().getSelectedItem().toString().isEmpty()) {
    try {
        Connection con = getConnection();
        String current = "INSERT IGNORE INTO `questions`(question,reponse1,reponse2,reponse3,reponse4, type,solution,createur) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(current);
        if (videoField.isVisible()) {
            ps.setString(1, String.valueOf(current_video_number));
            System.out.println(String.valueOf(current_video_number));

        } else {
            ps.setString(1, questionField.getText());
        }
        ps.setString(2, reponse1Field.getText());
        ps.setString(3, reponse2Field.getText());
        ps.setString(4, reponse3Field.getText());
        ps.setString(5, reponse4Field.getText());
        ps.setInt(6, testIdField.getSelectionModel().getSelectedItem());
        ps.setString(7, solutionField.getText());
        System.out.println(Current_user);
        ps.setString(8, Current_user);
        ps.execute();
        setLblError(Color.GREEN, "Question Ajoutée ...");
        reponse1Field.setStyle("-fx-text-box-border: #26b50a;");
        reponse2Field.setStyle("-fx-text-box-border: #26b50a;");
        reponse3Field.setStyle("-fx-text-box-border: #26b50a;");
        reponse4Field.setStyle("-fx-text-box-border: #26b50a;");
        if (questionField.isVisible()) questionField.setStyle("-fx-text-box-border: #26b50a;");
        solutionField.setStyle("-fx-text-box-border: #26b50a;");
        if (videoField.isVisible())
            Files.copy(video_choosed_path, Paths.get("src/main/resources/images/" + current_video_number + ".mp4"), StandardCopyOption.REPLACE_EXISTING);

    } catch (SQLException | IOException | RuntimeException ex) {
        System.out.println("On n'a essayé désolé !");
        setLblError(Color.TOMATO, "Les Champs ne correspond pas! ");
        if (reponse1Field.getText().isEmpty()) reponse1Field.setStyle("-fx-text-box-border: #B22222;");
        if (reponse2Field.getText().isEmpty()) reponse2Field.setStyle("-fx-text-box-border: #B22222;");
        if (reponse3Field.getText().isEmpty()) reponse3Field.setStyle("-fx-text-box-border: #B22222;");
        if (reponse4Field.getText().isEmpty()) reponse4Field.setStyle("-fx-text-box-border: #B22222;");
        if (questionField.isVisible() && questionField.getText().isEmpty())
            questionField.setStyle("-fx-text-box-border: #B22222;");
        if (solutionField.getText().isEmpty()) solutionField.setStyle("-fx-text-box-border: #B22222;");
    } finally {
        questionField.setText("");
        reponse1Field.setText("");
        reponse2Field.setText("");
        reponse3Field.setText("");
        reponse4Field.setText("");
        solutionField.setText("");
    }
}else
{
    setLblError(Color.TOMATO, "Les Champs ne correspond pas! ");
    if (reponse1Field.getText().isEmpty()) reponse1Field.setStyle("-fx-text-box-border: #B22222;");
    if (reponse2Field.getText().isEmpty()) reponse2Field.setStyle("-fx-text-box-border: #B22222;");
    if (reponse3Field.getText().isEmpty()) reponse3Field.setStyle("-fx-text-box-border: #B22222;");
    if (reponse4Field.getText().isEmpty()) reponse4Field.setStyle("-fx-text-box-border: #B22222;");
    if (questionField.isVisible() && questionField.getText().isEmpty())
        questionField.setStyle("-fx-text-box-border: #B22222;");
    if (solutionField.getText().isEmpty()) solutionField.setStyle("-fx-text-box-border: #B22222;");
}


    }

    private void setLblError(Color color, String text) {
        lbrErrors.setTextFill(color);
        lbrErrors.setText(text);
        System.out.println(text);
    }

    public void SupprimerQuestion(ActionEvent actionEvent) {
        System.out.println(" deleting now ");
        if (!videoField.isVisible() &&  !questionField.getText().isEmpty())
        {
            try{
                String InputQuestion = questionField.getText();
                System.out.println( questionField.getText());

                try {
                    Connection con = getConnection();
                    String current = "DELETE questions FROM questions  WHERE question=?";
                    PreparedStatement ps = con.prepareStatement(current);
                    ps.setString(1,InputQuestion);
                    ps.execute();
                    setLblError(Color.GREEN, "deleting Successful..");
                } catch (SQLException | ArrayIndexOutOfBoundsException  ex ) {
                    System.out.println("we tried to add your student!  we fail");
                }finally {
                    questionField.setText("");
                    reponse1Field.setText("");
                    reponse2Field.setText("");
                    reponse3Field.setText("");
                    reponse4Field.setText("");
                    solutionField.setText("");
                }
            }catch (RuntimeException ex)
            {
                System.out.println("split method doesn't work otmane");
                setLblError(Color.TOMATO, "Le nom saisi ne correspond pas! ");
            }
        }else
        {
            System.out.println("Selectionner la partie Textuelle S'il Vous Plait! ");
    questionField.setStyle("-fx-text-box-border: #B22222;");
            setLblError(Color.TOMATO,"Selectionner la partie Textuelle S'il Vous Plait! ");
        }
    }

    public void addText(ActionEvent actionEvent) {
        if(videoField.isVisible())
        {
            videoField.setVisible(false);
        }
        Integer [] items = {1,2,3,4,5,6};
        loadCombo(items);
        questionField.setVisible(true);
    }

    public void addVideo(ActionEvent actionEvent) {
        if(questionField.isVisible())
        {
            questionField.setVisible(false);
        }
        Integer [] items = {7};
        loadCombo(items);
        videoField.setVisible(true);

    }

    public void chooseMyvideo(ActionEvent actionEvent) throws IOException {
        final FileChooser fileChooser = new FileChooser();
try{
    Stage stage = (Stage) videoField.getScene().getWindow();
    configureFileChooser(fileChooser);
    File file = fileChooser.showOpenDialog(stage);
    System.out.println(file.getAbsolutePath());
    video_choosed_path = Paths.get(file.getAbsolutePath());
    Connection con3 = getConnection();
    String sql = "select count(*) from `questions` where `type` ='"+7+"'";
    PreparedStatement ps = con3.prepareStatement(sql);
    ResultSet rs = ps.executeQuery();
        while (rs.next())
        {
            current_video_number = rs.getInt("count(*)");
        }
    current_video_number++;
    videoExtention.setText(current_video_number +".mp4");

}catch (NullPointerException | SQLException e)
{
    System.out.println("from the stage chooser exception");
    current_video_number=0;
    videoExtention.setText(current_video_number+".mp4");

}
}
    private static void configureFileChooser(
            final FileChooser fileChooser) {
        fileChooser.setTitle("Open Video");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("MP4", "*.mp4")
        );
    }

}