package welcome;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GarageApp extends Application
{
    @FXML
    private ProgressBar progresBar=new ProgressBar(0.25f);


    public static void main(String[] args)
    {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception
    {
        try
        {
            javafx.scene.Parent welcomeParent = javafx.fxml.FXMLLoader.load(getClass().getResource("../userGarageView/GarageTextView.fxml"));
            primaryStage.setTitle("Filip's Garage");
            Scene welcomeScene=new Scene(welcomeParent);
            primaryStage.setScene(welcomeScene);
            primaryStage.show();

        }catch(Exception ex)
        {
            System.out.println("main exception");
        }


//
//
//        javafx.scene.Parent welcomeParent = javafx.fxml.FXMLLoader.load(getClass().getResource("WelcomeScreenView.fxml"));
//        primaryStage.setTitle("Filip's Garage");
//        Scene welcomeScene=new Scene(welcomeParent);
//        primaryStage.setScene(welcomeScene);
//
//
//        progresBar.progressProperty().unbind();
//        progresBar.setProgress(0.25);
//
//        FadeTransition fadeIn = new FadeTransition(Duration.seconds(3),welcomeParent);   // 3 sekunde
//
//
//        fadeIn.setFromValue(0);
//        fadeIn.setToValue(1);
//        fadeIn.setCycleCount(1);
//        fadeIn.play();
//        primaryStage.initStyle(StageStyle.UNDECORATED); // initStyle bez minimize maximize, cloase
//        primaryStage.show();
//
//
//        fadeIn.setOnFinished( event->{
//            try
//            {
//
//
//               Parent startMenuParent = FXMLLoader.load(getClass().getResource("../startMenu/StartMenuView.fxml"));
//                Scene startMenuScene=new Scene(startMenuParent);
//                Stage startMenuStage = new Stage(); // zbog drugog initStyle
//                startMenuStage.setScene(startMenuScene);
//                startMenuStage.show();
//                primaryStage.hide();
//
//
//            }catch(IOException ex)
//            {
//                System.out.println("KAPUT");
//                ex.printStackTrace();
//            }
//        });



       // primaryStage.hide();
    }


    @Override
    public void stop() throws Exception
    {
        System.out.println("exiting");
        System.exit(1);
    }

}
