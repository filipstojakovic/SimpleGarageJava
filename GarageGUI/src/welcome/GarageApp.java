package welcome;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class GarageApp extends Application
{

    public static void main(String[] args)
    {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception
    {
        javafx.scene.Parent welcomeParent = javafx.fxml.FXMLLoader.load(getClass().getResource("WelcomeScreenView.fxml"));
        primaryStage.setTitle("Filip's Garage");
        Scene welcomeScene=new Scene(welcomeParent);
        primaryStage.setScene(welcomeScene);

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(3),welcomeParent);   // 3 sekunde

        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);
        fadeIn.play();
        primaryStage.initStyle(StageStyle.UNDECORATED); // initStyle bez minimize maximize, cloase
        primaryStage.show();

        fadeIn.setOnFinished( event->{
            try
            {

               Parent startMenuParent = FXMLLoader.load(getClass().getResource("../startMenu/StartMenuView.fxml"));
                Scene startMenuScene=new Scene(startMenuParent);
                Stage startMenuStage = new Stage(); // zbog drugog initStyle
                startMenuStage.setScene(startMenuScene);
                startMenuStage.show();
                primaryStage.hide();


//                FXMLLoader loader = new FXMLLoader();
//                loader.setLocation(getClass().getResource("../startMenu/StartMenuView.fxml")); // second fxml
//                Parent secondRoot1 = loader.load();        // throws IOException
//                Scene scene = new Scene(secondRoot1);
//
//                StartMenuController secondController = loader.getController();    // secondController
//                secondController.setStage(currentStage);
//                currentStage.setScene(scene);
//                currentStage.show();

//                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//                window.setScene(scene);
//                window.setTitle("Second Window Title");      // set Title
//                window.show();

            }catch(IOException ex)
            {
                System.out.println("KAPUT");
                ex.printStackTrace();
            }
        });



       // primaryStage.hide();
    }
}
