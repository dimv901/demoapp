package py.com.aseguradoratajy.tajydemo.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manu0 on 9/23/2017.
 */

public class Services {

    private int serviceId;
    private String serviceDescription;

    public Services(int serviceId, String serviceDescription) {
        this.serviceId = serviceId;
        this.serviceDescription = serviceDescription;
    }

    public static List<Services> setupServiceList() {

        List<Services> servicesList = new ArrayList<>();
        Services services1 = new Services(1, "Asistencia al Hogar");
        Services services2 = new Services(2, "Asistencia Legal");
        Services services3 = new Services(3, "Asistencia Vehicular");
        Services services4 = new Services(4, "Asistencia al Viajero");
        Services services5 = new Services(5, "Red de Pagos");
        Services services6 = new Services(6, "Carta Verde");

        servicesList.add(services1);
        servicesList.add(services2);
        servicesList.add(services3);
        servicesList.add(services4);
        servicesList.add(services5);
        servicesList.add(services6);

        return servicesList;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }
}
