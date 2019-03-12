package platform;

import platform.Field.Field;
import platform.Field.FieldTypeEnum;
import utils.UtilMethods;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Platform implements Serializable
{

    public static final int coloumnNum = 8;
    public static final int rowNum = 10;
    public static final Field[][] fields = new Field[rowNum][coloumnNum];
    private ArrayList<String> textField;
    private int floorNum;   // jos cemo vidjeti da li ostati
    private int numFreeSpaces = 28;
   // private static int floorNum=0;

    private String refresh()
    {
        String textFieldAsString="";


        return textFieldAsString;
    }
    public Platform()
    {
        this(0);    // radi testiranja
    }

    public Platform(int floorNum)   // konstruktor platfrome
    {
        this.floorNum=floorNum;
        this.textField=new ArrayList<String>(floorNum);
        fields[0][0] = new Field(FieldTypeEnum.LEFT_EXIT, 0, 0);
        fields[1][0] = new Field(FieldTypeEnum.LEFT_ENTRANCE, 1, 0);

        fields[0][7] = new Field(FieldTypeEnum.RIGHT_EXIT, 7, 0);
        fields[1][7] = new Field(FieldTypeEnum.RIGHT_ENTRANCE, 7, 1);

        for (int row = 0; row < 2; row++)   // prva dva reda
            for (int col = 1; col < 7; col++)
                fields[row][col] = new Field(FieldTypeEnum.STREET, row, col);


        for (int row = 2; row < Platform.rowNum; row++) // Parking prva i zadnja kolona i u sredini
        {
            fields[row][0] = new Field(FieldTypeEnum.PARKING, row, 0);
            fields[row][7] = new Field(FieldTypeEnum.PARKING, row, 7);
            if (row < 8)
            {
                fields[row][3] = new Field(FieldTypeEnum.PARKING, row, 3);
                fields[row][4] = new Field(FieldTypeEnum.PARKING, row, 4);
            }
        }

        for (int row = 2; row < rowNum; row++)  // Street od 2 reda do zadnjeg reda
        {
            fields[row][1] = new Field(FieldTypeEnum.STREET, row, 1);
            fields[row][2] = new Field(FieldTypeEnum.STREET, row, 2);
            fields[row][5] = new Field(FieldTypeEnum.STREET, row, 5);
            fields[row][6] = new Field(FieldTypeEnum.STREET, row, 6);
            if (row > 7)
            {
                fields[row][3] = new Field(FieldTypeEnum.STREET, row, 3);
                fields[row][4] = new Field(FieldTypeEnum.STREET, row, 4);
            }
        }

        // TODO: ljepse napravi
//        fields[1][1].setType(FieldTypeEnum.CROSSROAD_LEFT);
//        fields[1][5].setType(FieldTypeEnum.CROSSROAD_LEFT);
//        fields[2][2].setType(FieldTypeEnum.CROSSROAD_DOWN);
//        fields[2][6].setType(FieldTypeEnum.CROSSROAD_DOWN);

    }

    public Field getFieldOnPosition(int row, int col) {return fields[row][col];}

   // public void setFieldOnPosition(int i, int j) { }

    public static Field[][] getFields()
    {
        return fields;
    }   // vidjecemo hoces li biti public static

    public int getNumFreeSpaces()
    {
        return numFreeSpaces;
    }

    public void incrementNumOfFreeSpaces()
    {
        if(numFreeSpaces<28)
        {
            numFreeSpaces++;
        }
        // else exception , 28 limit
    }
    public void decrementNumOfFreeSpaces()
    {
        if(numFreeSpaces>0)
        {
            numFreeSpaces--;
        }
        //else exception, no more free space
    }


    //nadji prvo slobodno mjesto na parkingu -> Slijeva na desno
    public Field findFreeParkingField()
    {
        if(numFreeSpaces<1)
            return null; // nema slobodnog mjesta

        for (int row = 2; row < Platform.rowNum; row++)
        {
            if (fields[row][0].isFree())
                return fields[row][0];
        }


        for(int column=3;column<5;column++)
        {
            for(int row=2;row<Platform.rowNum-2;row++)
            {
                if(fields[row][column].isFree())
                    return fields[row][column];
            }
        }

        for (int row = 2; row < Platform.rowNum; row++)
        {
            if (fields[row][7].isFree())
                return fields[row][7];
        }

        return null;    // nema slobodnog mjesta -> ne bi se trebalo nikad desiti jer se provjerava prvo ima li mjesta

    }





    //prikaz platforme
    public void showPlatform()
    {
        UtilMethods.clearScreen();

        for (int i = 0; i < rowNum; i++)
        {
            for (int j = 0; j < coloumnNum; j++)
            {
                if(fields[i][j].getVehicleOnField()!=null)
                    System.out.print("V ");

                else if (FieldTypeEnum.PARKING.equals(fields[i][j].getType()))
                    System.out.print("P ");
                else if (FieldTypeEnum.STREET.equals(fields[i][j].getType()))
                    System.out.print("- ");
                else
                    System.out.print("- ");

            }
            System.out.println("");

        }
        System.out.println("");

//        try
//        {
//            Thread.sleep(600);
//        }catch(Exception ex)
//        {
//            ex.printStackTrace();
//        }


    }


}
