package py.com.aseguradoratajy.tajydemo.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mavalos on 11/10/17.
 */

public class VehiclesModels {

    private Integer modelId;
    private String description;

    public VehiclesModels() {

    }

    public VehiclesModels(Integer modelId, String description) {
        this.modelId = modelId;
        this.description = description;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public static List<VehiclesModels> getVehiclesList() {
        List<VehiclesModels> vehiclesModeList = new ArrayList<>();
        VehiclesModels vehiclesModels1 = new VehiclesModels(1,"prueba modelo");
        vehiclesModeList.add(vehiclesModels1);
        return vehiclesModeList;
    }


}
