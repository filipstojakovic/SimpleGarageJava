package platform.Field;

import vehicle.Vehicle;

public class Field
{
    private int rowPosition;
    private int columnPosition;
    private Vehicle vehicleOnField;
    private FieldTypeEnum type = null;
    private boolean isFree = true;

    public Field(FieldTypeEnum type, int x, int y)
    {
        this.type = type;
        rowPosition = x;
        columnPosition = y;
    }

    public Field(int x, int y)
    {
        rowPosition = x;
        columnPosition = y;
    }

    public Vehicle getVehicleOnField()
    {
        return vehicleOnField;
    }

    public void setVehicleOnField(Vehicle vehicleOnField)
    {
        this.vehicleOnField = vehicleOnField;
        isFree=false;
    }

    public void removeVehicleFromField()
    {
        vehicleOnField=null;
        isFree=true;
    }

    public int getRowPosition()
    {
        return rowPosition;
    }

    public void setRowPosition(int rowPosition)
    {
        this.rowPosition = rowPosition;
    }

    public int getColumnPosition()
    {
        return columnPosition;
    }

    public void setColumnPosition(int columnPosition)
    {
        this.columnPosition = columnPosition;
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

    public String toString()
    {
        return "Field row= "+ rowPosition +" col= "+ columnPosition;
    }
}
