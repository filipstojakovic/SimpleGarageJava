package userGarageView;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import platform.Garage;
import vehicle.Vehicle;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserGarageController extends Application implements Initializable
{

    public static final Object lock1 = new Object();

    int currentFloorSelected = 0;
    UserGarageModel userModel;
    private Garage globalGarage = new Garage(8);
    @FXML
    private ComboBox<Integer> platformNumComboBox;
    @FXML
    private TextArea platformTextArea;
    @FXML
    private TextField textField;
    private Runnable mainThread;
    private Thread platformThread;
    private List<Vehicle> allVehicles = new ArrayList<>();
   // private ObservableList<Vehicle> carObserver = FXCollections.observableArrayList();
    private List<Vehicle> carObserver = new ArrayList<>();

    @FXML
    private TextField newCarNumField;

    @FXML
    void backButtonClicked(ActionEvent event)
    {

    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {

    }

    @Override
    public void stop() throws Exception
    {
        System.out.println("closing this shit");
    }

    @FXML
    void newCarButtonClicked(ActionEvent event)
    {
        if (userModel == null || !userModel.isAlive())
        {
            userModel = new UserGarageModel(currentFloorSelected);
            userModel.start();


            mainThread = new Runnable()
            {
                @Override
                public void run()
                {

                    while (true)
                    {

                        String toString=new String();
                        toString= userModel.toString();
                        platformTextArea.setText(toString);     // voli bacati izuzetak

                        try
                        {
                            Thread.sleep(100);
                        } catch (InterruptedException ex)
                        {
                            ex.printStackTrace();
                        }

                    }
                }
            };

                platformThread = new Thread(mainThread);
                platformThread.setDaemon(true);
                platformThread.setName("platformThread");
                platformThread.start();
        }

        int n=0;
        try
        {
            n= Integer.parseInt( newCarNumField.getText());
            for(int i=0; i<n; i++)
            {
                Vehicle newVehicle = new Vehicle();
                carObserver.add(newVehicle);

                newVehicle.start();

                try
                {
                    Thread.sleep(20);
                }catch(InterruptedException ex)
                {
                    ex.printStackTrace();
                }

            }
        }catch (Exception ex)
        {
            Vehicle newVehicle = new Vehicle();
            carObserver.add(newVehicle);

            newVehicle.start();
        }


    }


    @FXML
    void platformNumComboBoxSelect(ActionEvent event)
    {
        currentFloorSelected = platformNumComboBox.getValue();
        System.out.println("combobox selected: "+currentFloorSelected);
        userModel.setCurrentFloor(currentFloorSelected);

    }

    @FXML
    void removeCarButtonClicked(ActionEvent event)
    {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        userModel = new UserGarageModel();
        platformTextArea.setText(userModel.currentFloorAsString(0));


        int n = globalGarage.getNumberOfPlatforms();
        List<Integer> asd = new ArrayList();
        for (int i = 0; i < n; i++)
        {
            platformNumComboBox.getItems().add(i);
        }

        platformNumComboBox.getSelectionModel().selectFirst();
    }


    @FXML
    void showTextFieldPlatform(ActionEvent event)   // try catch
    {
       try
       {
           int n= Integer.parseInt( textField.getText());
           System.out.println("shoing floor: "+n);
           globalGarage.platforms.get(n).showPlatform();
       }catch(Exception ex)
       {
           System.out.println("shoing floor: 0");
           globalGarage.platforms.get(0).showPlatform();
       }

    }






}

