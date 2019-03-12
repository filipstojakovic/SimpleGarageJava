package platform;

import platform.Field.Field;
import vehicle.Vehicle;

import java.util.ArrayList;

public class Garage
{
    private int numberOfPlatforms = 1;
    public static ArrayList<Platform> platforms;   // prvobitno private moguce nepotrebne funkcije
    public static ArrayList<Vehicle> allVehiclesInGarage;

    public Garage(int n)
    {
        allVehiclesInGarage=new ArrayList<>();  // mozda ce biti odredjen kapacitet
        numberOfPlatforms = n;
        platforms = new ArrayList<>(numberOfPlatforms);
        for (int i = 0; i < numberOfPlatforms; i++)
        {
            platforms.add(new Platform(i));
        }
    }

    public int getNumberOfPlatforms()
    {
        return numberOfPlatforms;
    }

    public void addVehicleinGarage(Vehicle vehicle)
    {
        vehicle.start();
        allVehiclesInGarage.add(vehicle);
    }

    public Garage()
    {
        platforms=new ArrayList<>();
        for (int i = 0; i < numberOfPlatforms; i++)
        {
            platforms.add(new Platform());
        }
    }


    public static int findFloorWithFreeParkingField()
    {

        for(int i=0;i<platforms.size();i++)
            if (platforms.get(i).getNumFreeSpaces() != 0)
                return i;


        return 0;   // ako nema mjesta
    }

    public static Field findFreeParkingField(int floorNum)
    {

        for(int row=2,col=0;row<Platform.rowNum;row++)
            if(platforms.get(floorNum).getFieldOnPosition(row,col).isFree())
            {
                platforms.get(floorNum).getFieldOnPosition(row,col).setFree(false);
                return platforms.get(floorNum).getFieldOnPosition(row,col);
            }


        for(int col=3;col<5;col++)
        {
            for(int row=2;row<Platform.rowNum-2;row++)
                if(platforms.get(floorNum).getFieldOnPosition(row,col).isFree())
                {
                    platforms.get(floorNum).getFieldOnPosition(row,col).setFree(false);
                    return platforms.get(floorNum).getFieldOnPosition(row,col);
                }

        }

        for(int row=2,col=7;row<Platform.rowNum;row++)
            if(platforms.get(floorNum).getFieldOnPosition(row,col).isFree())
            {
                platforms.get(floorNum).getFieldOnPosition(row,col).setFree(false);
                return platforms.get(floorNum).getFieldOnPosition(row,col);
            }

        return null;
    }

    public void garagePlatform(int platformNum)
    {
        platforms.get(platformNum).showPlatform();
    }

    public static boolean hasFreeSpace()   // da li postoji slobodno mjesto u citavoj garazi // mozda bolje private
    {
        return platforms.stream()
                        .anyMatch(freeSpace-> freeSpace.getNumFreeSpaces()!=0);
    }

}
