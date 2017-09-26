package py.com.aseguradoratajy.tajydemo.models;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Diego on 9/23/2017.
 */

public class Branches {
    private String branchName;
    private int branchNumber;
    private String branchPhone;
    private String branchAddress;
    private LatLng branchLocation;

    public Branches(String name, int number, String phone, String address, LatLng location) {
        this.branchName = name;
        this.branchNumber = number;
        this.branchPhone = phone;
        this.branchAddress = address;
        this.branchLocation = location;
    }

    public static List<Branches> getInstance() {
        List<Branches> mList = new ArrayList<>();

        Branches branch1 = new Branches("Casa Matriz", 1, "(021) 689 1000", "Avda. San Martín N° 1243 esq. Agustín Barrios", new LatLng(-25.2852672,-57.5726114));
        Branches branch2 = new Branches("Centro de Atención Integral - CAI", 2, "(021) 689 1000", "Avda. Eusebio Ayala N° 2496 esq. Tte. 1° Julián Riquelme", new LatLng(-25.310317248784, -57.590020895004));
        Branches branch3 = new Branches("Sucursal Katueté", 3, "(021) 689 1000 - int 530", "Ruta Internacional N°10 - Avda. Las Residentas c/ Padre Fidel Maíz", new LatLng(-24.245712403635, -54.757232666016));

        Branches branch4 = new Branches("Sucursal Encarnación", 4, " (021) 689 1000 - int 570", "Avda. Juan León Mallorquín c/ Cerro Corá", new LatLng(-27.254095464074, -55.920957326889));
        Branches branch5 = new Branches("Sucursal Hohenau", 5, "(021) 689 1000 - int 560", "Avda. Osvaldo Tischler c/ Luis Alberto de Herrera", new LatLng(-27.08694483728, -55.651588439941));
        Branches branch6 = new Branches("Sucursal Loma Plata", 6, "(021) 689 1000 - int 520", "Calle 3 de noviembre N° 1185 e/ Avda. Central y Eligio Ayala", new LatLng(-22.365475236044, -59.934689998627));

        Branches branch7 = new Branches("Sucursal Santa Rita", 7, " (021) 689 1000 - int 540", "Avda. Carlos Antonio López c/ 14 de mayo, Galería Mercosur - Salón 2", new LatLng(-25.790463250961, -55.090534687042));
        Branches branch8 = new Branches("Sucursal María Auxiliadora", 8, "(021) 689 1000 - int 550", "Dr. Juan León Mallorquín - Ruta 6, Km 117", new LatLng(-26.523728817301, -55.285606384277));
        Branches branch9 = new Branches("Sucursal Ciudad del Este", 9, "(021) 689 1000 - int 515", "Avda. San José esq. Los Rosales. Edif. Horizonte", new LatLng(-25.511925391881, -54.683353900909));

        mList.add(branch1);
        mList.add(branch2);
        mList.add(branch3);
        mList.add(branch4);
        mList.add(branch5);
        mList.add(branch6);
        mList.add(branch7);
        mList.add(branch8);
        mList.add(branch9);

        Comparator<Branches> mComparator = new Comparator<Branches>() {
            @Override
            public int compare(Branches o1, Branches o2) {
                if (o1.getBranchNumber() < o2.getBranchNumber()) {
                    return -1; //return negative integer if first argument is less than second
                }
                return 1;
            }
        };
        Collections.sort(mList, mComparator);
        return mList;
    }

    public String getBranchPhone() {
        return branchPhone;
    }

    public void setBranchPhone(String branchPhone) {
        this.branchPhone = branchPhone;
    }

    public int getBranchNumber() {
        return branchNumber;
    }

    public void setBranchNumber(int branchNumber) {
        this.branchNumber = branchNumber;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public LatLng getBranchLocation() {
        return branchLocation;
    }

    public void setBranchLocation(LatLng branchLocation) {
        this.branchLocation = branchLocation;
    }
}
