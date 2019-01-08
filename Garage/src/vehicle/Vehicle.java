package vehicle;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import utils.UtilMethods;
import vehicle.VhiecelEnum.CarModelImageEnum;

import java.io.Serializable;
import java.util.Observer;
import java.util.Random;

public class Vehicle extends Thread implements Serializable, Observable
{
    private MovementEnum currentMovement=MovementEnum.RIGHT;
    private int currentX=0;
    private int currentY=0;
    private String carName;
    private String chassis;
    private CarModelImageEnum carModelImageEnum=null;
    private MovementEnum getCurrentMovement=MovementEnum.RIGHT;
    private String registerNum;
    private boolean isMoving=false;

    //constructor
    public Vehicle ()
    {

    }


    @Override
    public void run()
    {

    }

    @Override
    public void addListener(InvalidationListener listener)
    {

    }

    @Override
    public void removeListener(InvalidationListener listener)
    {

    }
}
