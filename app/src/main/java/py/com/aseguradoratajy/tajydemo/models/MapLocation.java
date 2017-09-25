package py.com.aseguradoratajy.tajydemo.models;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Diego on 9/23/2017.
 */

public class MapLocation {
    public String name;
    public LatLng center;

    public MapLocation(String name, LatLng center) {
        this.name = name;
        this.center = center;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng getCenter() {
        return center;
    }

    public void setCenter(LatLng center) {
        this.center = center;
    }
}
