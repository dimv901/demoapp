package py.com.aseguradoratajy.tajydemo.models;

import java.util.ArrayList;
import java.util.List;

import py.com.aseguradoratajy.tajydemo.R;

/**
 * Created by Diego on 9/24/2017.
 */

public class Insurance {

    public String number;
    private int icon;
    private int insuranceAmount;
    private String expiration;
    private String description;
    private String subdescription;
    private boolean active;

    public static List<Insurance> getInstance() {
        List<Insurance> mList = new ArrayList<>();
        Insurance i1 = new Insurance("POLIZA N°: N-0501-86245-0", R.mipmap.ic_insurance_car, 25000000, "01/01/2018", "TOYOTA VITZ", "PATENTE: BRB450", true);
        Insurance i2 = new Insurance("POLIZA N°: N-0501-86245-1", R.mipmap.ic_insurance_life, 150000000, "01/01/2018", "DIEGO IVAN MALDONADO", "SEGURO INDIVIDUAL", true);
        mList.add(i1);
        mList.add(i2);
        return mList;
    }

    public Insurance(String number, int icon, int insuranceAmount, String expiration, String description, String subdescription, boolean active) {
        this.number = number;
        this.icon = icon;
        this.insuranceAmount = insuranceAmount;
        this.expiration = expiration;
        this.description = description;
        this.subdescription = subdescription;
        this.active = active;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getInsuranceAmount() {
        return insuranceAmount;
    }

    public void setInsuranceAmount(int insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubdescription() {
        return subdescription;
    }

    public void setSubdescription(String subdescription) {
        this.subdescription = subdescription;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
