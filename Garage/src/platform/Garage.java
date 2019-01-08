package platform;

import java.util.ArrayList;

public class Garage
{
    private int numberOfPlatforms = 24;
    private ArrayList<Platform> platforms;


    public Garage(int n)
    {
        numberOfPlatforms = n;
        platforms = new ArrayList<>(numberOfPlatforms);
        for (int i = 0; i < numberOfPlatforms; i++)
        {
            platforms.add(new Platform());
        }
    }

    public Garage()
    {
        for (int i = 0; i < numberOfPlatforms; i++)
        {
            platforms.add(new Platform());
        }
    }

    public void garagePlatform(int platformNum)
    {
        platforms.get(platformNum).showPlatform();
    }

    public boolean hasFreeSpace()   // da li postoji slobodno mjesto u citavoj garazi
    {
        return platforms.stream()
                        .anyMatch(freeSpace-> freeSpace.getNumFreeSpaces()!=0);
    }

}
