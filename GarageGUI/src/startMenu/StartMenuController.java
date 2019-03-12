package startMenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StartMenuController implements Initializable
{
    private List<Users> usersList = new ArrayList<>();
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField signUpUsernameField;
    @FXML
    private PasswordField signUpPasswordField;
    @FXML
    private PasswordField signUpConfPasswordField;
    @FXML
    private RadioButton userRb;
    @FXML
    private ToggleGroup group;
    @FXML
    private RadioButton adminRb;




    @FXML
    void loginButtonClicked(ActionEvent event) throws IOException
    {
        System.out.println("LOGIN CURRENT LIST SIZE: " + usersList.size());
        if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty())
        {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setContentText("EMPTY FIELDS");
            al.showAndWait();
            clearAll();
            return;
        }
        Users user = new Users(usernameField.getText(), passwordField.getText());

        if (usersList.contains(user))
        {
            Alert al = new Alert(Alert.AlertType.INFORMATION);


            if (usersList.stream().filter(tmp -> tmp.equals(user)).findFirst().get().isAdmin())
                al.setContentText("LOGED IN AS ADMIN!");
            else
                al.setContentText("LOGED IN AS USER!");


            //al.showAndWait();

            serializeUserList();

            try
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(".." + File.separatorChar + "userGarageView" + File.separatorChar + "GarageTextView.fxml"));
                Parent userGarageParent = loader.load();
                Scene userGarageScene = new Scene(userGarageParent);

                //                UserGarageController userGarageController = loader.getController();
                //                userGarageController.initializeUser(user);

                Stage userGarageStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                userGarageStage.setScene(userGarageScene);
                userGarageStage.setTitle(user.getUsername() + " Garage");
                userGarageStage.centerOnScreen();

                userGarageStage.show();

            } catch (Exception ex)
            {
                System.out.println("WWWWWWWWWWWW");
                ex.printStackTrace();
                try
                {
                    Thread.sleep(2000);
                } catch (Exception ex1)
                {
                    ex1.printStackTrace();
                }
                System.exit(1);
            }
            // ulogovan otvori scenu


        } else
        {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setContentText("USER DOES NOT EXIST");
            al.showAndWait();
        }

        clearAll();
    }

    @FXML
    void signUpButtonClicked(ActionEvent event)
    {

        if (signUpUsernameField.getText().isEmpty() || signUpConfPasswordField.getText().isEmpty() || signUpConfPasswordField.getText().isEmpty())
        {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setContentText("EMPTY FIELDS");
            al.showAndWait();
            clearAll();
            return;
        } else if (!signUpPasswordField.getText().equals(signUpConfPasswordField.getText()))
        {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setContentText("PASSWORD DO NOT MATCH");
            al.showAndWait();
            clearAll();
            return;
        }

        boolean isAdmin = adminRb.isSelected();
        Users user = new Users(signUpUsernameField.getText(), signUpPasswordField.getText(), isAdmin);

        if (usersList.isEmpty() || !usersList.contains(user))
        {
            usersList.add(user);
            clearAll();
            System.out.println("SIGNIN CURRENT LIST SIZE: " + usersList.size());  // cisto ispis NAKO
            for (Users tmp : usersList)
                System.out.println(tmp.getUsername() + " " + tmp.getPassword() + " " + tmp.isAdmin());

            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setContentText("ACCOUNT CREATED!");
            al.showAndWait();

        } else
        {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setContentText("USER ALREADY EXISTS!");
            al.showAndWait();
        }

        clearAll();
    }

    private void clearAll()
    {
        usernameField.clear();
        passwordField.clear();

        signUpUsernameField.clear();
        signUpPasswordField.clear();
        signUpConfPasswordField.clear();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {


        File file = new File("." + File.separator + "AllUsers.ser");
        try
        {
            if (!file.exists())
            {
                file.createNewFile();
                return;
            }
        } catch (IOException ex)
        {
            System.out.println("ERROR CREATING FILE");
            ex.printStackTrace();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file)))
        {

            usersList = (List<Users>) ois.readObject();

        } catch (ClassNotFoundException ex)
        {
            System.out.println("CLASS NOT FOUND EXCEPTION");
            ex.printStackTrace();
        } catch (IOException ex)
        {
            System.out.println("ERROR READING FROM FILE - DESERIALIZE");
            ex.printStackTrace();
        }
        System.out.println("SIGNIN CURRENT LIST SIZE: " + usersList.size());  // cisto ispis NAKO
        for (Users tmp : usersList)
            System.out.println(tmp.getUsername() + " " + tmp.getPassword() + " " + tmp.isAdmin());
    }

    public void serializeUserList()
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("." + File.separator + "AllUsers.ser")))
        {
            oos.writeObject(usersList);

        } catch (IOException ex)
        {
            System.out.println("ERROR WRITING TO FIKLE - SERIALIZE");
            ex.printStackTrace();
        }

    }


}
