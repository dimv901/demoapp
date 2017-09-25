package py.com.aseguradoratajy.tajydemo.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diego on 9/24/2017.
 */

public class InsuranceDetail {
    private String description;
    private String model;
    private int year;
    private String plate;
    private String insuranceType;
    private String insuranceExpiration;

    public InsuranceDetail(String description, String model, int year, String plate, String insuranceType, String insuranceExpiration) {
        this.description = description;
        this.model = model;
        this.year = year;
        this.plate = plate;
        this.insuranceType = insuranceType;
        this.insuranceExpiration = insuranceExpiration;
    }

    public String getInsuranceExpiration() {
        return insuranceExpiration;
    }

    public void setInsuranceExpiration(String insuranceExpiration) {
        this.insuranceExpiration = insuranceExpiration;
    }

    public static List<InsuranceDetail> getInstance() {
        List<InsuranceDetail> mList = new ArrayList<>();
        InsuranceDetail d = new InsuranceDetail("TOYOTA", "VITZ", 2003, "BRR100", "SEGURO VEHICULO", "01/01/2018");
        mList.add(d);
        return mList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }
}
