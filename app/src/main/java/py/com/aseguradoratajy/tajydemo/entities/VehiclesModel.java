package py.com.aseguradoratajy.tajydemo.entities;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END

/**
 * Entity mapped to table "VEHICLES_MODEL".
 */
@Entity
public class VehiclesModel {

    @Id
    private Long id;
    private Integer modelCode;
    private String description;
    private Integer brandId;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    @Generated
    public VehiclesModel() {
    }

    public VehiclesModel(Long id) {
        this.id = id;
    }

    @Generated
    public VehiclesModel(Long id, Integer modelCode, String description, Integer brandId) {
        this.id = id;
        this.modelCode = modelCode;
        this.description = description;
        this.brandId = brandId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getModelCode() {
        return modelCode;
    }

    public void setModelCode(Integer modelCode) {
        this.modelCode = modelCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
