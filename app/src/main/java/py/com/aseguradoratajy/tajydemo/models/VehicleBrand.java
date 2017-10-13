package py.com.aseguradoratajy.tajydemo.models;

import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mavalos on 11/10/17.
 */

public class VehicleBrand {

    private Integer vehicleId;
    private String description;

    public VehicleBrand() {

    }

    public VehicleBrand(Integer vehicleId, String description) {
        this.vehicleId = vehicleId;
        this.description = description;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static List<VehicleBrand> getVehiclesBrandList() {

        List<VehicleBrand> vehicleBrandList = new ArrayList<>();
        VehicleBrand vehicleBrand1 = new VehicleBrand(1, "prueba");
        vehicleBrandList.add(vehicleBrand1);

        return vehicleBrandList;
    }


}
