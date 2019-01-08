package vehicle.VhiecelEnum;

import utils.UtilMethods;
import vehicle.vehicleType.Car;

import java.util.Random;

public enum CarModelImageEnum
{
    BMW("./car images/bmw.jpeg"), OPEL(""), GOLF(""), RENO("");

    private String path;
    private CarModelImageEnum(String path)
    {
        this.path= path;
    }

    public String getPath()
    {
        return path;
    }


}
