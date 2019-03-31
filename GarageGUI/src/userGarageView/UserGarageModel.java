package userGarageView;

import platform.Field.Field;
import platform.Field.FieldTypeEnum;
import platform.Garage;
import platform.Platform;

public class UserGarageModel extends Thread
{
    private static final Object lock = new Object();
    public int currentFloor=0;

    String text1=new String("");


    public UserGarageModel(int floor)
    {
        currentFloor=floor;
    }

    public UserGarageModel()
    {

    }
    @Override
    public void run()
    {
       while(true)
       {
           synchronized (lock)
           {

               text1=new String();
               text1=currentFloorAsString(currentFloor);
               try
               {
                   Thread.sleep(100);
               }catch(InterruptedException ex)
               {
                   ex.printStackTrace();
               }

           }
       }
    }

    public void setCurrentFloor(int currentFloor)
    {
        this.currentFloor = currentFloor;
    }

    public synchronized String currentFloorAsString(int currentFloor)
    {
        Field[][] currentFields = Garage.platforms.get(currentFloor).getFields();
        StringBuffer text2=new StringBuffer();
        for (int row = 0; row < Platform.rowNum; row++)
        {
            for (int col = 0; col < Platform.coloumnNum; col++)
            {


                if (Garage.platforms.get(currentFloor).getFieldOnPosition(row, col).getVehicleOnField() != null)
                    text2.append("V ");

                else if (FieldTypeEnum.PARKING.equals(Garage.platforms.get(currentFloor).getFieldOnPosition(row, col).getType()))
                    text2.append("P ");
                else if (FieldTypeEnum.STREET.equals(Garage.platforms.get(currentFloor).getFieldOnPosition(row, col).getType()))
                    text2.append("- ");
                else
                    text2.append("- ");
            }
            text2.append("\n");

        }
        return text2.toString();

    }

    @Override
    public String toString()
    {
        return text1.toString();
    }
}
