package py.com.aseguradoratajy.tajydemo.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manu0 on 9/23/2017.
 */

public class Products {

    private int productId;
    private String productDescription;

    public Products(int productId, String productDescription) {
        this.productId = productId;
        this.productDescription = productDescription;
    }

    public static  List<Products> setupDataProducts() {
        List<Products> productsList = new ArrayList<>();
        Products products1 = new Products(1, "Accidentes Personales");
        Products products2 = new Products(2, "Transporte");
        Products products3 = new Products(3, "Multiriesgo Comercio");
        Products products4 = new Products(4, "Multiriesgo Hogares");
        Products products5 = new Products(5, "Caucción");
        Products products6 = new Products(6, "Equipos Electrónicos");
        Products products7 = new Products(7, "Vida Colectiva");
        Products products8 = new Products(8, "Seguro Agricola");

        productsList.add(products1);
        productsList.add(products2);
        productsList.add(products3);
        productsList.add(products4);
        productsList.add(products5);
        productsList.add(products6);
        productsList.add(products7);
        productsList.add(products8);

        return productsList;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
}
