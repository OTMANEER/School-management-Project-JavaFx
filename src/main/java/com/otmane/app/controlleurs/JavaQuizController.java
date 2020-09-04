package com.otmane.app.controlleurs;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXSnackbar;
import com.otmane.app.dataBase.MysqlConnection;
import com.otmane.app.dataBase.QuestionDao;
import com.otmane.app.questionV.QuestionVo;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author OTMANE_Erragragui
 */
public class JavaQuizController  extends MysqlConnection implements Initializable{

            public static  String Current_Question_Creator;

            @FXML
            private AnchorPane parent;
            @FXML
            private StackPane root;
            @FXML
            private AnchorPane homePane,testPane,finishedPane,resultPane;
            @FXML
            private JFXSlider progress;
            @FXML
            private Label titleLbl;
            @FXML
            private TextArea questionLbl;
            @FXML
            private JFXRadioButton reponse1,reponse2,reponse3,reponse4,reponse11,reponse21,reponse31,reponse41;

            // Controle de la video
            @FXML private JFXSlider slider;
            @FXML private JFXSlider volumeSlider;
            @FXML private javafx.scene.media.MediaView mediaView;
            @FXML private Label playTime;
            @FXML private FontAwesomeIconView icon;
            @FXML private AnchorPane videoPane;
            @FXML private Label  titleLbl1;
            @FXML private ImageView imageView;
            @FXML private Circle myCircle;
            @FXML private ImageView imageView3;
            @FXML private Circle myCircle1;
            @FXML private ImageView imageView2;
            @FXML private Circle myCircle2;

            private JFXDialog TheCreator;

            private Duration duration;
            //    private Media media = new Media("file:///C:/Users/gleid/Videos/Android.mp4");
            private Media media = null;
            private MediaPlayer mediaPlayer = null;
            private final boolean repeat = false;
            private boolean stopRequested = false;
            private boolean atEndOfMedia = false;
            private JFXSnackbar toastMsg;
            //le type de votre choix d'apres le Manu principale sont indexés
            public static int choix;
            // Une liste des questions

            private List<QuestionVo> questions;
            // sauvgarder la reponse de façon indexé ---(key,value) numero de question et la reponse
            private Map<Integer,Integer> instanceQuestion;

            // le maximum nombre des questions possible;
            private static int MAX_NUMBER_QUESTION;

            //Index courrant de la question  posé
            private static int indexCurrantQuestion=0;
            @FXML
            private Label scoreLbl,precentageScoreLbl;
            public  static String tempResult;

    @Override
    public void initialize(URL location, ResourceBundle resources){
                        toastMsg = new JFXSnackbar(homePane);

        myCircle.setFill(new ImagePattern(imageView.getImage()));
        myCircle.setEffect(new DropShadow(+5d,1d,+2d,Color.rgb(108,108,129)));

        myCircle1.setFill(new ImagePattern(imageView2.getImage()));
        myCircle1.setEffect(new DropShadow(+5d,1d,+2d,Color.rgb(108,108,129)));

        myCircle2.setFill(new ImagePattern(imageView3.getImage()));
        myCircle2.setEffect(new DropShadow(+5d,1d,+1.5d,Color.rgb(108,108,129)));
       }

    @FXML
    private void close() {
                        System.exit(0);
                    }
    //Les buttons de la Scene

    @FXML
    private void btnBasic() {
                        choix = 1;
                        redirect();
                    }

    @FXML
    private void btnOperator() {
                        choix=2;
                        redirect();
                    }

    @FXML
    private void btnTables() {
                        choix=3;
                        redirect();
                    }

    @FXML
    private void btnOOP(){
                        choix=4;
                        redirect();
                    }

    @FXML
    private void btnPoly(){
                       choix=5;
                       redirect();
                    }

    @FXML
    private void btnException(){
                        choix=6;
                        redirect();
                    }

    @FXML
    private void btnAdvanced(){
                        choix=7;
                        redirectVideo();
                    }

    private void redirect(){
        int temp = 0;
        try {
            Connection con3 = getConnection();
            String sql = "select count(*) from `questions` where `type` ='"+choix+"'";
            PreparedStatement ps = con3.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                temp =rs.getInt("count(*)");
            }
        }catch (SQLException e)
        {
            temp =0;
        }
        if(temp != 0)
        {
            questions = new QuestionDao().getQuestion(choix);
        }else {
            questions =null;
        }
                        if(questions == null)
                        {
                            toastMsg.show("Pas de Question!",2000);
                            return;
                        }
                        switch(choix)
                        {
                            case 1: {
                                titleLbl.setText("Test 1");
                            }
                            break;
                            case 2: {
                                titleLbl.setText("Test 2");
                            }
                            break;
                            case 3: {
                                titleLbl.setText("Test 3");
                            }
                            break;
                            case 4: {
                                titleLbl.setText("Test 4");
                            }
                            break;
                            case 5: {
                                titleLbl.setText("Test 5");
                            }
                            break;
                            case 6: {
                                titleLbl.setText("Test 6");
                            }
                            break;
                        }
                        MAX_NUMBER_QUESTION = questions.size();
                        instanceQuestion = new HashMap<>();
                        for (int i=0;i<questions.size();i++)
                        {
                            instanceQuestion.put(i,0);
                        }
                            loadQuestion(0);
                            homePane.setVisible(false);
                            testPane.setVisible(true);

                    }

    private void redirectVideo(){
        int temp = 0;
        try {
            Connection con3 = getConnection();
            String sql = "select count(*) from `questions` where `type` ='"+choix+"'";
            PreparedStatement ps = con3.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                temp =rs.getInt("count(*)");
            }
        }catch (SQLException e)
        {
            temp =0;
        }
        if (temp != 0)
        {
            questions = new QuestionDao().getQuestion(choix);
        }else
        {
            questions = null;
        }

        if(questions == null){
            toastMsg.show("Pas de Question!",2000);
            return;
        }
                titleLbl1.setText("Videos");
        MAX_NUMBER_QUESTION = questions.size();
        instanceQuestion = new HashMap<>();
        for (int i=0;i<questions.size();i++){
            instanceQuestion.put(i,0);
        }
        loadQuestionVideos(0);
        homePane.setVisible(false);
        videoPane.setVisible(true);
    }

    @FXML
    private void btnPrevious(){
                        if(indexCurrantQuestion == 0)
                        {
                            if(choix != 7)
                            {
                                testPane.setVisible(false);
                            }else{
                                mediaPlayer.stop();
                                mediaPlayer = null;
                                videoPane.setVisible(false);
                            }
                            homePane.setVisible(true);
                            return;
                        }
                        indexCurrantQuestion--;
                        if(choix != 7)
                        {
                            loadQuestion(indexCurrantQuestion);
                        }else {
                            mediaPlayer.stop();
                            mediaPlayer = null;
                            loadQuestionVideos(indexCurrantQuestion);
                        }
                    }

    @FXML
    private void btnNext(){
                        if(choix != 7)
                        {
                            saveInstance();
                        }else
                        {
                            saveInstanceVideos();
                        }
                        System.out.println(instanceQuestion.toString());
                        if(indexCurrantQuestion == MAX_NUMBER_QUESTION -1)
                        {
                            if(choix != 7)
                            {
                                testPane.setVisible(false);
                            }else{
                                videoPane.setVisible(false);
                                mediaPlayer.stop();
                                mediaPlayer = null;
                            }
                            finishedPane.setVisible(true);
                            return;
                        }
                        indexCurrantQuestion++;
                        if(choix != 7)
                        {
                            loadQuestion(indexCurrantQuestion);
                        }else {
                            mediaPlayer.stop();
                             mediaPlayer = null;
                            loadQuestionVideos(indexCurrantQuestion);

                        }
                    }

    private void loadQuestion(int index){
                        if(choix!=7)
                        {
                            progress.setMax(MAX_NUMBER_QUESTION);
                            progress.setValue(indexCurrantQuestion);
                            questionLbl.setText(questions.get(index).getQuestion());
                            reponse1.setText(questions.get(index).getReponse()[0]);
                            reponse2.setText(questions.get(index).getReponse()[1]);
                            reponse3.setText(questions.get(index).getReponse()[2]);
                            reponse4.setText(questions.get(index).getReponse()[3]);
                            Current_Question_Creator = questions.get(index).getLeCreateur();
                        }else {
                            reponse11.setText(questions.get(index).getReponse()[0]);
                            reponse21.setText(questions.get(index).getReponse()[1]);
                            reponse31.setText(questions.get(index).getReponse()[2]);
                            reponse41.setText(questions.get(index).getReponse()[3]);
                            Current_Question_Creator = questions.get(index).getLeCreateur();
                        }
                        switch (instanceQuestion.get(index))
                        {
                            case 0:
                                reponse1.setSelected(false);
                                reponse2.setSelected(false);
                                reponse3.setSelected(false);
                                reponse4.setSelected(false);
                                break;
                            case 1:
                                reponse1.setSelected(true);
                                reponse2.setSelected(false);
                                reponse3.setSelected(false);
                                reponse4.setSelected(false);
                                break;
                            case 2:
                                reponse1.setSelected(false);
                                reponse2.setSelected(true);
                                reponse3.setSelected(false);
                                reponse4.setSelected(false);
                                break;
                            case 3:
                                reponse1.setSelected(false);
                                reponse2.setSelected(false);
                                reponse3.setSelected(true);
                                reponse4.setSelected(false);
                                break;
                            case 4:
                                reponse1.setSelected(false);
                                reponse2.setSelected(false);
                                reponse3.setSelected(false);
                                reponse4.setSelected(true);
                                break;
                        }
                    }

    private void loadQuestionVideos(int index){
        //Load une video pour toute paragraphe d'apres le lien de la base de donnée

        icon.setGlyphName("PLAY");
        try
        {
            String VideoName = questions.get(index).getQuestion();
            media = new Media(new File("src/main/resources/images/"+VideoName+".mp4 ").toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {

            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                updateValues();
            }
        });
        }catch (IndexOutOfBoundsException e)
        {
            System.out.println("No data exists for this choice");
            return;
        }
        mediaPlayer.setOnPlaying(new Runnable() {
            public void run() {

                if (stopRequested) {
                    mediaPlayer.pause();
                    stopRequested = false;
                } else {
                    icon.setGlyphName("PAUSE");
//
                }
            }
        });
        mediaPlayer.setOnPaused(new Runnable() {
            public void run() {

                icon.setGlyphName("PLAY");
                //playButton.setText("||");
            }
        });
        mediaPlayer.setOnReady(new Runnable() {
            public void run() {
                duration = mediaPlayer.getMedia().getDuration();
                mediaPlayer.getMedia().getDuration();
                updateValues();
            }
        });

        mediaPlayer.setCycleCount(repeat ? MediaPlayer.INDEFINITE : 1);
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                if (!repeat) {
                    icon.setGlyphName("PAUSE");
//                    playButton.setGraphic(imageViewPlay);
                    //playButton.setText(">");
                    stopRequested = true;
                    atEndOfMedia = true;
                }
            }
        });
        slider.setValue(0);
        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (volumeSlider.isValueChanging()) {
                    mediaPlayer.setVolume(volumeSlider.getValue() / 100.0);
                }
            }
        });
        // here set every Question string from database to the RadioButton
            reponse11.setText(questions.get(index).getReponse()[0]);
            reponse21.setText(questions.get(index).getReponse()[1]);
            reponse31.setText(questions.get(index).getReponse()[2]);
            reponse41.setText(questions.get(index).getReponse()[3]);


        switch (instanceQuestion.get(index))
        {
            case 0:
                reponse11.setSelected(false);
                reponse21.setSelected(false);
                reponse31.setSelected(false);
                reponse41.setSelected(false);
                break;
            case 1:
                reponse11.setSelected(true);
                reponse21.setSelected(false);
                reponse31.setSelected(false);
                reponse41.setSelected(false);
                break;
            case 2:
                reponse11.setSelected(false);
                reponse21.setSelected(true);
                reponse31.setSelected(false);
                reponse41.setSelected(false);
                break;
            case 3:
                reponse11.setSelected(false);
                reponse21.setSelected(false);
                reponse31.setSelected(true);
                reponse41.setSelected(false);
                break;
            case 4:
                reponse11.setSelected(false);
                reponse21.setSelected(false);
                reponse31.setSelected(false);
                reponse41.setSelected(true);
                break;
        }
    }

    private void saveInstance(){
        if (reponse1.isSelected()) {
            instanceQuestion.replace(indexCurrantQuestion, 1);
        } else if (reponse2.isSelected()) {
            instanceQuestion.replace(indexCurrantQuestion, 2);
        } else if (reponse3.isSelected()) {
            instanceQuestion.replace(indexCurrantQuestion, 3);
        } else if (reponse4.isSelected()) {
            instanceQuestion.replace(indexCurrantQuestion, 4);
        }
    }

    private void saveInstanceVideos(){
    if (reponse11.isSelected()) {
        instanceQuestion.replace(indexCurrantQuestion, 1);
    } else if (reponse21.isSelected()) {
        instanceQuestion.replace(indexCurrantQuestion, 2);
    } else if (reponse31.isSelected()) {
        instanceQuestion.replace(indexCurrantQuestion, 3);
    } else if (reponse41.isSelected()) {
        instanceQuestion.replace(indexCurrantQuestion, 4);
    }
}

    @FXML
    private void radioClicked(javafx.event.ActionEvent event){
                        reponse1.setSelected(false);
                        reponse2.setSelected(false);
                        reponse3.setSelected(false);
                        reponse4.setSelected(false);
                        reponse11.setSelected(false);
                        reponse21.setSelected(false);
                        reponse31.setSelected(false);
                        reponse41.setSelected(false);
                        ((JFXRadioButton)event.getSource()).setSelected(true);
                    }

    @FXML
    void btnBackHome(){
                        questions=null;
                        indexCurrantQuestion = 0;
                        try{
                            if(testPane.isVisible()) {
                                testPane.setVisible(false);
                                homePane.setVisible(true);
                            }else if(videoPane.isVisible()){
                                mediaPlayer.stop();
                                mediaPlayer  = null;
                                videoPane.setVisible(false);
                                homePane.setVisible(true);
                            } else if(finishedPane.isVisible())
                            {
                                finishedPane.setVisible(false);
                                homePane.setVisible(true);
                            }else if(resultPane.isVisible())
                            {
                                resultPane.setVisible(false);
                                homePane.setVisible(true);
                            }
                        }catch (NullPointerException e)
                        {
                            System.out.println("data for choice doesn't aldready exists");
                            return;
                        }




                    }

    @FXML
    void btnBackTest(){
                        finishedPane.setVisible(false);
                        if(choix!=7)
                        {
                            testPane.setVisible(true);
                            indexCurrantQuestion =0;
                            redirect();
                            loadQuestion(indexCurrantQuestion);
                        }else
                        {
                            videoPane.setVisible(true);
                            indexCurrantQuestion =0;
                            choix = 7;
                            redirectVideo();
                            loadQuestionVideos(indexCurrantQuestion);
                        }
                    }

    @FXML
    void btnViewResult() {
                        int score=0;
                        System.out.println("je calcule ton score");
                        for(int i=0;i<MAX_NUMBER_QUESTION;i++)
                        {
                            int v = instanceQuestion.get(i);
                            if(v == 0)
                            {
                                continue;
                            }
                            if(questions.get(i).getReponse()[v-1].equals(questions.get(i).getSolution()))
                            {
                                System.out.println(questions.get(i).getSolution());
                                score++;
                            }
                        }
                        try{
                            Connection con = getConnection();
                            tempResult = " "+score + "/"+ MAX_NUMBER_QUESTION+"";
                        String TheCurrentName = "null";
                        String current_user_name = "SELECT name FROM user_student WHERE username=?";
                        PreparedStatement ps1 = con.prepareStatement(current_user_name);
                        ps1.setString(1,FXMLDocumentController.Current_user);
                        ResultSet rs = ps1.executeQuery();
                        while(rs.next())
                        {
                            TheCurrentName = rs.getString("name");
                        }
                            System.out.println(TheCurrentName);
                            String query = "INSERT INTO `result_student` (name,test_id,score) VALUES (?,?,?)";
                            PreparedStatement ps = con.prepareStatement(query);
                            ps.setString (1, TheCurrentName);
                            ps.setInt (2, choix);
                            ps.setString(3, tempResult);
                            ps.execute();
                        }catch(SQLException e)
                        {
                            System.out.println("cannot insert result into database");
                        }finally {
                            System.out.println("from the finally bloc in the insertion step");
                        }

                     //       ResultSet rs1 = ps.executeQuery();

                        scoreLbl.setText(score + "/"+ MAX_NUMBER_QUESTION);
                        System.out.println(score);
                        if(score<MAX_NUMBER_QUESTION/2.0)
                        {
                            scoreLbl.setStyle(scoreLbl.getStyle()+"; -fx-text-fill: #E00");
                        }else {
                            scoreLbl.setStyle(scoreLbl.getStyle()+ "; -fx-text-fill: #0B0");
                        }
                        System.out.println("before out of range 5");
                        if(score != 0)
                        {
                            precentageScoreLbl.setText(String.valueOf(score * 100.0 / MAX_NUMBER_QUESTION).concat(" ").substring(0,5)+" %");
                        }else
                        {
                            precentageScoreLbl.setText(" 0 %");
                        }
                        System.out.println("before out of range 5");
                        finishedPane.setVisible(false);
                        resultPane.setVisible(true);
                    }

    private void updateValues(){
        try {
            if (playTime != null && slider != null && volumeSlider != null && duration != null) {
                Platform.runLater(new Runnable() {
                    public void run() {
                        try {
                        Duration currentTime = mediaPlayer.getCurrentTime();
                        playTime.setText(formatTime(currentTime, duration));
                        slider.setDisable(duration.isUnknown());

                            if (!slider.isDisabled() && duration.greaterThan(Duration.ZERO) && !slider.isValueChanging()) {
                       //     slider.setValue(currentTime.divide(duration).toMillis() * 100.0);

                                double du = duration.toMillis() * 100.0;
                                Duration divide =  currentTime.divide(du);
                            }


                        if (!volumeSlider.isValueChanging()) {
                            volumeSlider.setValue((int) Math.round(mediaPlayer.getVolume() * 100));
                        }
                        }catch (NullPointerException e)
                        {
                            System.out.println("...");
                        }
                    }
                });
            }
        }catch (NullPointerException ex)
        {
            System.out.println("Null pointer exception from updateValues method! ");
        }

    }

    private String formatTime(Duration elapsed, Duration duration) {
        int intElapsed = (int)Math.floor(elapsed.toSeconds());
        int elapsedHours = intElapsed / (60 * 60);
        if (elapsedHours > 0) {
            intElapsed -= elapsedHours * 60 * 60;
        }
        int elapsedMinutes = intElapsed / 60;
        int elapsedSeconds = intElapsed - elapsedHours * 60 * 60 - elapsedMinutes * 60;

        if (duration.greaterThan(Duration.ZERO)) {
            int intDuration = (int)Math.floor(duration.toSeconds());
            int durationHours = intDuration / (60 * 60);
            if (durationHours > 0) {
                intDuration -= durationHours * 60 * 60;
            }
            int durationMinutes = intDuration / 60;
            int durationSeconds = intDuration - durationHours * 60 * 60 - durationMinutes * 60;

            if (durationHours > 0) {
                return String.format("%d:%02d:%02d/%d:%02d:%02d",
                        elapsedHours, elapsedMinutes, elapsedSeconds,
                        durationHours, durationMinutes, durationSeconds);
            } else {
                return String.format("%02d:%02d/%02d:%02d",
                        elapsedMinutes, elapsedSeconds,
                        durationMinutes, durationSeconds);
            }
        } else {
            if (elapsedHours > 0) {
                return String.format("%d:%02d:%02d",
                        elapsedHours, elapsedMinutes, elapsedSeconds);
            } else {
                return String.format("%02d:%02d",
                        elapsedMinutes, elapsedSeconds);
            }
        }
    }

    @FXML
    private void play(){
        updateValues();
        try{
            System.out.println((double)mediaPlayer.getTotalDuration().toSeconds());
            slider.setMax((int)mediaPlayer.getTotalDuration().toSeconds());
            MediaPlayer.Status status = mediaPlayer.getStatus();
            if (status == MediaPlayer.Status.UNKNOWN
                    || status == MediaPlayer.Status.HALTED)
            {
                // don't do anything in these states
                return;
            }
            if (status == MediaPlayer.Status.PAUSED
                    || status == MediaPlayer.Status.READY
                    || status == MediaPlayer.Status.STOPPED)
            {
                // rewind the movie if we're sitting at the end
                if (atEndOfMedia) {
                    mediaPlayer.seek(mediaPlayer.getStartTime());
                    atEndOfMedia = false;
                    icon.setGlyphName("PLAY");
                    updateValues();
                }
                mediaPlayer.play();

                mediaPlayer.currentTimeProperty().addListener(new InvalidationListener() {
                    public void invalidated(Observable ov)
                    {
                        // slider.setValue((int)(mediaPlayer.getCurrentTime().toSeconds()));
                    try {
                        if(indexCurrantQuestion <= MAX_NUMBER_QUESTION)
                            slider.setValue(mediaPlayer.getCurrentTime().toSeconds());
                        else
                            slider = null;
                    }catch (NullPointerException e)
                    {
                        System.out.println("cannot update the slider");
                    }
                    }
                });

                icon.setGlyphName("PAUSE");
            }
            else {
                mediaPlayer.pause();
            }
        }catch (NullPointerException ex)
        {
            System.out.println("null pointer exception here");
        }

    }

    public void btnInfosProfs(ActionEvent actionEvent) {
        try
        {
            AnchorPane aboutPane = FXMLLoader.load(getClass().getResource("/FXMLS/ContactProfesseur.fxml"));
            TheCreator = new JFXDialog(this.root, aboutPane, JFXDialog.DialogTransition.TOP);
        } catch (IOException ex) {
            Logger.getLogger(JavaQuizController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TheCreator.show();
    }
}
