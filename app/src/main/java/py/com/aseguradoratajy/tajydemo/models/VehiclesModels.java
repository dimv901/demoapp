package py.com.aseguradoratajy.tajydemo.models;

/**
 * Created by mavalos on 11/10/17.
 */

public class VehiclesModels {

    private String modelId;
    private String description;

    public VehiclesModels(){

    }

    public VehiclesModels(String modelId, String description) {
        this.modelId = modelId;
        this.description = description;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
