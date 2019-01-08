package utils;

import vehicle.VhiecelEnum.CarModelImageEnum;

import java.util.Random;

public class UtilMethods
{
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static Random random= new Random();


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

    public static CarModelImageEnum randomCarModel()
    {
        return CarModelImageEnum.values()[(new Random()).nextInt(CarModelImageEnum.values().length)];
    }



}
