package platform.Field;

public class Field
{
    private int xCoordinate;
    private int yCoordinate;
    private FieldTypeEnum type=null;
    private boolean isFree=false;


    public Field(FieldTypeEnum type,int x,int y)
    {
        this.type=type;
        xCoordinate=x;
        yCoordinate=y;
    }

    public Field(int x,int y)
    {
        xCoordinate=x;
        yCoordinate=y;
    }

    public int getxCoordinate()
    {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate)
    {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate()
    {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate)
    {
        this.yCoordinate = yCoordinate;
    }

    public FieldTypeEnum getType()
    {
        return type;
    }

    public void setType(FieldTypeEnum type)
    {
        this.type = type;
    }

    public boolean isFree()
    {
        return isFree;
    }

    public void setFree(boolean free)
    {
        isFree = free;
    }
}
