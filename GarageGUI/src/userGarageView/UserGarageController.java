package userGarageView;

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
import platform.Field.FieldTypeEnum;
import platform.Garage;
import platform.Platform;
import vehicle.Vehicle;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserGarageController implements Initializable
{

    public static final Object lock1 = new Object();

    int currentFloorSelected = 0;

    private Garage globalGarage = new Garage(10);
    @FXML
    private ComboBox<Integer> platformNumComboBox;
    @FXML
    private TextArea platformTextArea;
    @FXML
    private TextField textField;
    private Task task;
    private Runnable mainThread;
    private Thread platformThread;

    private List<Vehicle> allVehicles = new ArrayList<>();
    private ObservableList<Vehicle> carObserver = FXCollections.observableArrayList();


    //    private Vector<Vehicle> carObserver = new Vector<>();
    private ListView<Vehicle> list;

    UserGarageModel userModel;

    @FXML
    void backButtonClicked(ActionEvent event)
    {

    }

    @FXML
    void newCarButtonClicked(ActionEvent event)
    {
        if(userModel==null || !userModel.isAlive())
        {
            userModel=new UserGarageModel();
            userModel.start();


            mainThread = new Runnable()
            {
                @Override
                public void run()
                {

                    while(true)
                    {

                        String toString = userModel.toString();
//
                        platformTextArea.setText(toString);
//                   //     System.out.println(toString);


                        try
                        {
                            Thread.sleep(100);
                        }catch(InterruptedException ex)
                        {
                            ex.printStackTrace();
                        }
                        platformTextArea.clear();
                    }
                }
            };
            platformThread=new Thread(mainThread);
            platformThread.setName("platfromThread");
            platformThread.start();
        }




        Vehicle tmp = new Vehicle();
        carObserver.add(tmp);
        tmp.setName("FILIPOVO VOZILO!");
        tmp.start();


    }





    @FXML
    void platformNumComboBoxSelect(ActionEvent event)
    {

    }

    @FXML
    void removeCarButtonClicked(ActionEvent event)
    {

    }


    public void platformToString(int i)
    {


    }


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        forTextArea();
        for(int i=0;i<globalGarage.getNumberOfPlatforms();i++)
        {
            platformNumComboBox.setValue(i);
        }


    }


    public void forTextArea()
    {
        //StringBuffer text=new StringBuffer();    // StringBuilder / StringBuffer ???


        platformTextArea.clear();

        for (int row = 0; row < Platform.rowNum; row++)
        {
            for (int col = 0; col < Platform.coloumnNum; col++)
            {


                if (Garage.platforms.get(currentFloorSelected).getFieldOnPosition(row, col).getVehicleOnField() != null)
                    platformTextArea.appendText("V ");

                else if (FieldTypeEnum.PARKING.equals(Garage.platforms.get(currentFloorSelected).getFieldOnPosition(row, col).getType()))
                    platformTextArea.appendText("P ");
                else if (FieldTypeEnum.STREET.equals(Garage.platforms.get(currentFloorSelected).getFieldOnPosition(row, col).getType()))
                    platformTextArea.appendText("- ");
                else
                    platformTextArea.appendText("- ");
            }
            platformTextArea.appendText("\n");


        }

    }

    // da se pauzira refresovanje polja
    public boolean anyMovingCars()  // ovo cemo poboljsati // na trenutnoj platformi kretanje auta?
    {
        for (Vehicle cars : carObserver)
        {
            if (!cars.isMoving())
            {
                return false;
            }
        }

        return true;

    }


    /*
      forTextArea();
        task = new Task()
        {
            @Override
            protected Void call() throws Exception
            {
                javafx.application.Platform.runLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        int i=0;
                        while(true)
                        {
                            try
                            {
                                Thread.sleep(500);
                                System.out.println("printing");
                                synchronized (lock1)
                                {
                                    forTextArea();

                                }

                            }catch(Exception ex)
                            {
                                System.out.println("error");
                            }
                            i++;

                        }
                    }
                });

                return null;
            }
        };
        mainThread = new Thread(task);
     */
}

