package userGarageView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import startMenu.Users;
import vehicle.Vehicle;

import java.io.IOException;

public class UserGarageController
{
    private Users currentUser;
    private Vehicle test;


    @FXML
    private GridPane gridPane;


    public UserGarageController()
    {
        System.out.println("DEFAULT UserGarageController");
    }

    public void initializeUser(Users user)
    {
        currentUser = user;
    }

    @FXML
        // go back to Start Menu/Login
    void backButtonClicked(ActionEvent event) throws IOException
    {
        Parent startMenuParent = FXMLLoader.load(getClass().getResource("../startMenu/StartMenuView.fxml"));
        Scene startMenuScene = new Scene(startMenuParent);

        Stage startMenuStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        startMenuStage.setScene(startMenuScene);
        startMenuStage.centerOnScreen();
        startMenuStage.show();

    }

}
