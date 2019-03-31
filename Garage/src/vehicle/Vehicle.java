package vehicle;

import platform.Field.Field;
import platform.Garage;
import platform.Platform;
import vehicle.VhiecelEnum.CarModelImageEnum;

import java.io.Serializable;

public class Vehicle extends Thread implements Serializable
{
    public static Object Lock = new Object();
    private MovementEnum currentMoveDirection = MovementEnum.RIGHT;
    private int currentRow = 0;
    private int currentCol = 0;
    private int currentFloor = 0;
    private String carName;
    private String chassis;
    private CarModelImageEnum carModelImageEnum = null;
    private String registerNum;


    private boolean isMoving = false;


    private boolean isInGarage = false;

    //constructor
    public Vehicle()
    {
    }

    @Override
    public void run()
    {
//        synchronized (Lock)
//        {


            while(!Garage.platforms.get(0).getFieldOnPosition(1, 0).isFree())
            {
                synchronized (Lock)
                {
                    try
                    {
                        Thread.sleep(300);
                    }catch(InterruptedException ex)
                    {
                        System.out.println("ulaz nije slobodan");
                        ex.printStackTrace();
                    }

                }
            }


            currentRow = 1;
            currentCol = 0;
            currentFloor = 0;
            Garage.platforms.get(currentFloor).getFieldOnPosition(currentRow, currentCol).setVehicleOnField(this);
            isInGarage = true;
            int floorNum = 0;
            Field destination = null;
            isMoving=true;
          if (isInGarage)
            {

                synchronized (Lock)
                {
                    floorNum = Garage.findFloorWithFreeParkingField();   // nadji sprat sa slobodnim mjestom
                    destination = Garage.findFreeParkingField(floorNum); // nadji slobodno mjesto na tom spratu

                }

                goToFloorNum(floorNum);


                parkOnGivenField(destination);
                isMoving=false;

            }

//        }

//            while (isInGarage)
//            {
//
//
//                // current X and Y
//                //
//
//
//                //            while(currentFloor<2)
//                //            {
//                //                moveRight();
//                //                Garage.platforms.get(currentFloor).showPlatform();
//                //                try
//                //                {
//                //                    sleep(1000);
//                //                }catch(Exception ex)
//                //                {
//                //                    ex.printStackTrace();
//                //                }
//                //            }
//                //            System.out.println("current floor: "+currentFloor);
//                //            Garage.platforms.get(currentFloor).showPlatform();
//                //
//                //            while(isInGarage)
//                //            {
//                //                try
//                //                {
//                //                    sleep(1200);
//                //                }catch(Exception ex)
//                //                {
//                //                    ex.printStackTrace();
//                //                }
//                //
//                //                this.moveLeft();
//                //                System.out.println("current floor: "+currentFloor);
//                //                Garage.platforms.get(currentFloor).showPlatform();
//                //
//                //            }
//
//
//                // vozi Misko na slobodno mjesto
//
//
//                // if exiting isInGarage = false;
//                isInGarage = false;
//            }
////        }


    }

    public boolean isMoving()
    {
        return isMoving;
    }

    public int getCurrentRow()
    {
        return currentRow;
    }

    public int getCurrentCol()
    {
        return currentCol;
    }

    public int getCurrentFloor()
    {
        return currentFloor;
    }

    void goToFloorNum(int floorNum) // vozi na @FloorNum sprat // TODO: mozda prepraviti u goUpToFloorNum // TODO: mozda dodati metodu goDownToFloorNum;
    {
        while(currentFloor<floorNum)
        {
            moveRight();
        }


//        // TODO: pazi na pravilo desne strane
//        for (; currentFloor < floorNum; currentFloor++)
//        {
//            for ( ; currentCol < Platform.coloumnNum - 1; currentCol++)
//            {
//                // vidi ima li auta ispred/guzve
//                // vidi jel Field slobodno
//
//               // Garage.platforms.get(i).showPlatform();
//                //System.out.println("\n");
//
//
//                Garage.platforms.get(currentFloor).getFieldOnPosition(1, currentCol).removeVehicleFromField();
//                Garage.platforms.get(currentFloor).getFieldOnPosition(1, currentCol + 1).setVehicleOnField(this);
//
//                try
//                {
//                    sleep(600);
//                } catch (InterruptedException ex)
//                {
//                    ex.printStackTrace();
//                }
//
//            }
//
//            this.currentFloor++;
//
//            //Garage.platforms.get(i).showPlatform();
//            //System.out.println("\n");
//            Garage.platforms.get(currentFloor).getFieldOnPosition(1, 0).setVehicleOnField(this);
//            currentRow=1;
//            currentCol=0;
//            Garage.platforms.get(currentFloor-1).getFieldOnPosition(1, Platform.coloumnNum - 1).removeVehicleFromField();
//
//            try
//            {
//                sleep(600);
//            } catch (InterruptedException ex)
//            {
//                ex.printStackTrace();
//            }
//
//
//        }

        //TODO: while loop with moveRight()

        //NOT TODO: reset currentRow = 0

    }


    public void moveRight()
    {


        currentMoveDirection = MovementEnum.RIGHT;
        if (currentCol < Platform.coloumnNum - 1)
        {
            // vidi da li ima auto ispred

            // obrisi ovo vozilo sa ove pozicije
            Garage.platforms.get(currentFloor).getFieldOnPosition(currentRow, currentCol).removeVehicleFromField();
            currentCol++;
            // prebaci vozilo na novu poziciju
            Garage.platforms.get(currentFloor).getFieldOnPosition(currentRow, currentCol).setVehicleOnField(this);

        } else
        {
            // TODO: provjeri da li je zadnji sprat
            Garage.platforms.get(currentFloor).getFieldOnPosition(currentRow, Platform.coloumnNum - 1).removeVehicleFromField();
            currentFloor++;
            currentCol = 0;
            Garage.platforms.get(currentFloor).getFieldOnPosition(currentRow, currentCol).setVehicleOnField(this);
        }


        //Garage.platforms.get(currentFloor).showPlatform();

        try
        {
            Thread.sleep(600);
        } catch (Exception ex)
        {
            System.out.println("moveRight exception");
            ex.printStackTrace();
        }
        // mozda throw exception

    }

    public void moveLeft()
    {


        currentMoveDirection = MovementEnum.LEFT;
        if (currentCol > 0)
        {

            Garage.platforms.get(currentFloor).getFieldOnPosition(currentRow, currentCol).removeVehicleFromField();
            currentCol--;
            // prebaci vozilo na novu poziciju
            Garage.platforms.get(currentFloor).getFieldOnPosition(currentRow, currentCol).setVehicleOnField(this);

        } else if (currentFloor == 0)
        {
            System.out.println("Izasao iz garaze");
            isInGarage = false;

        } else
        {
            // TODO: if(prizemlje) napusti garazu// isInGarage=false;
            Garage.platforms.get(currentFloor).getFieldOnPosition(currentRow, currentCol).removeVehicleFromField();
            currentCol = Platform.coloumnNum - 1;
            currentFloor--;
            Garage.platforms.get(currentFloor).getFieldOnPosition(currentRow, currentCol).setVehicleOnField(this);

        }
      //  Garage.platforms.get(currentFloor).showPlatform();

        try
        {
            Thread.sleep(600);
        } catch (Exception ex)
        {
            System.out.println("moveLeft exception");
            ex.printStackTrace();
        }


    }

    public void moveDown()
    {


        currentMoveDirection = MovementEnum.DOWN;
        if (currentRow < Platform.rowNum - 1) // ako je 8<9
        {
            Garage.platforms.get(currentFloor).getFieldOnPosition(currentRow, currentCol).removeVehicleFromField();
            currentRow++;
            // prebaci vozilo na novu poziciju
            Garage.platforms.get(currentFloor).getFieldOnPosition(currentRow, currentCol).setVehicleOnField(this);

        } else
        {
            System.out.println("out of garage exception -> moveDown");

            // out of garage exception
        }

      //  Garage.platforms.get(currentFloor).showPlatform();

        try
        {
            Thread.sleep(600);
        } catch (Exception ex)
        {
            System.out.println("moveDown exception");
            ex.printStackTrace();
        }


    }

    public void moveUp()
    {


        currentMoveDirection = MovementEnum.UP;
        if (currentRow > 0)
        {
            Garage.platforms.get(currentFloor).getFieldOnPosition(currentRow, currentCol).removeVehicleFromField();
            currentRow--;
            // prebaci vozilo na novu poziciju
            Garage.platforms.get(currentFloor).getFieldOnPosition(currentRow, currentCol).setVehicleOnField(this);

        } else
        {
            // out of garage exception
        }
//        Garage.platforms.get(currentFloor).showPlatform();

        try
        {
            Thread.sleep(600);
        } catch (Exception ex)
        {
            System.out.println("moveUp exception");
            ex.printStackTrace();
        }


    }

    //prvo pozovi metodu da auto ode na odredjeni sprat
    // zatim ovu metodu
    public void parkOnGivenField(Field parkField)
    {
        moveRight();
        //        Garage.platforms.get(currentFloor).showPlatform();

        if(parkField==null)
        {
            System.out.println("parkOnGivenField -> park field null");
            return;
        }

        if (parkField.getColumnPosition() >= Platform.coloumnNum / 2) //(x >= 4)
        {
            while (currentCol < 5)
            {
                moveRight();
            }
        }
        while (currentRow < parkField.getRowPosition())
        {
            moveDown();
        }
        if (parkField.getColumnPosition() < currentCol)
        {
            moveLeft();
        } else
        {
            moveRight();
            moveRight();
        }

    }


    void exitFromGarage()   // napusti garazu
    {

    }

}
