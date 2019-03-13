

import platform.Field.Field;
import platform.Garage;
import vehicle.Vehicle;


public class GarageMain
{

    public static void main(String[] args)
    {

        Garage garage= new Garage(3);



        for(int i=0;i<28;i++)
        {
            garage.addVehicleinGarage(new Vehicle());
            try
            {
                Thread.sleep(600); // da ne bi isli jedan preko drugog dok se ne implementira opcija da se sacekaju
            } catch (InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }

        System.out.println("end of Garage Main");


        //garage.garagePlatform(0);

    }

}
