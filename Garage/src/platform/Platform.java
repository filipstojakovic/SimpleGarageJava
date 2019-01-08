package platform;

import platform.Field.Field;
import platform.Field.FieldTypeEnum;

import java.io.Serializable;

public class Platform implements Serializable
{

    private static final int coloumnNum = 8;
    private static final int rowNum = 10;
    public static final Field[][] fields = new Field[rowNum][coloumnNum];
    private int numFreeSpaces = 28;

    public Platform()
    {
        fields[0][0] = new Field(FieldTypeEnum.LEFT_EXIT, 0, 0);
        fields[1][0] = new Field(FieldTypeEnum.LEFT_ENTRANCE, 1, 0);

        fields[0][7] = new Field(FieldTypeEnum.RIGHT_EXIT, 7, 0);
        fields[1][7] = new Field(FieldTypeEnum.RIGHT_ENTRANCE, 7, 1);

        for (int i = 0; i < 2; i++)
            for (int j = 1; j < 7; j++)
                fields[i][j] = new Field(FieldTypeEnum.STREET, i, j);

        for (int i = 2; i < rowNum; i++)
        {
            fields[i][0] = new Field(FieldTypeEnum.PARKING, i, 0);
            fields[i][7] = new Field(FieldTypeEnum.PARKING, i, 0);
            if (i < 8)
            {
                fields[i][3] = new Field(FieldTypeEnum.PARKING, i, 3);
                fields[i][4] = new Field(FieldTypeEnum.PARKING, i, 3);
            }
        }

        for (int i = 2; i < rowNum; i++)
        {
            fields[i][1] = new Field(FieldTypeEnum.STREET, i, 1);
            fields[i][2] = new Field(FieldTypeEnum.STREET, i, 2);
            fields[i][5] = new Field(FieldTypeEnum.STREET, i, 5);
            fields[i][6] = new Field(FieldTypeEnum.STREET, i, 6);
            if (i > 7)
            {
                fields[i][3] = new Field(FieldTypeEnum.STREET, i, 3);
                fields[i][4] = new Field(FieldTypeEnum.STREET, i, 4);
            }
        }

    }

    public static Field[][] getFields()
    {
        return fields;
    }

    public int getNumFreeSpaces()
    {
        return numFreeSpaces;
    }

    public void setNumFreeSpaces(int numFreeSpaces)
    {
        this.numFreeSpaces = numFreeSpaces;
    }

    public void showPlatform()
    {
        for (int i = 0; i < rowNum; i++)
        {
            for (int j = 0; j < coloumnNum; j++)
            {
                if (FieldTypeEnum.PARKING.equals(fields[i][j].getType()))
                    System.out.print("P ");
                else if (FieldTypeEnum.STREET.equals(fields[i][j].getType()))
                    System.out.print("S ");
                else
                    System.out.print("X ");

            }
            System.out.println("");

        }
    }


}
