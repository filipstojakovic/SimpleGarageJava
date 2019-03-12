package utils;

import vehicle.VhiecelEnum.CarModelImageEnum;

import java.util.Random;

public class UtilMethods
{
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static Random random= new Random();


    // generisi slucajan string od @size karaktera
    public static String randomString(int size)
    {

        StringBuffer buffer = new StringBuffer();

        while(size>0)
        {
            buffer.append(ALPHA_NUMERIC_STRING.charAt(random.nextInt(ALPHA_NUMERIC_STRING.length())));
            size--;
        }
        return buffer.toString();
    }

    // generisi slucajan Car model
    public static CarModelImageEnum randomCarModel()
    {
        return CarModelImageEnum.values()[(new Random()).nextInt(CarModelImageEnum.values().length)];
    }

    public static void clearScreen() // whatever
    {
        for (int i = 0; i < 15; ++i) System.out.println();
    }


}
