package vehicle.VhiecelEnum;

public enum SpecializedVehicleEnum
{
    POLICE_CAR(""),POLICE_MOTORCYCLE(""), POLICE_VAN(""),
    AMBULANCE_CAR(""),AMBULANCE_VAN(""),
    FIREDEPARTMENT_VAN("");

    private String path;

    private SpecializedVehicleEnum(String path)
    {
        this.path=path;
    }

    public String getPath()
    {
        return path;
    }
}
