package py.com.aseguradoratajy.tajydemo.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manu0 on 9/25/2017.
 */

public class Sinisters {
    private int sinisterId;
    private String description;
    private String numberPhone;

    public Sinisters(int sinisterId, String description, String phoneNumber) {
        this.sinisterId = sinisterId;
        this.description = description;
        this.numberPhone = phoneNumber;
    }


    public static List<Sinisters> setupSinisterList() {
        List<Sinisters> sinistersList = new ArrayList<>();

        Sinisters sinisters1 = new Sinisters(1, "Policía","911");
        Sinisters sinister2 = new Sinisters(2, "Incendio","932");
        Sinisters sinisters3 = new Sinisters(3, "Ambulancia","111");
        Sinisters sinisters4 = new Sinisters(4, "Call Center","0216891000");

        sinistersList.add(sinisters1);
        sinistersList.add(sinister2);
        sinistersList.add(sinisters3);
        sinistersList.add(sinisters4);

        return sinistersList;
    }

    public int getSinisterId() {
        return sinisterId;
    }

    public void setSinisterId(int sinisterId) {
        this.sinisterId = sinisterId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }
}
