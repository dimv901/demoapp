package py.com.aseguradoratajy.tajydemo.models;

/**
 * Created by mavalos on 11/10/17.
 */

public class VehicleBrand {

    private String vehicleId;
    private String description;

    public VehicleBrand(){

    }

    public VehicleBrand(String vehicleId, String description) {
        this.vehicleId = vehicleId;
        this.description = description;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
