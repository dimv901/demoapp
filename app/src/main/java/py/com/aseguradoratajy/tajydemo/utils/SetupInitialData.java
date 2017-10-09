package py.com.aseguradoratajy.tajydemo.utils;

import java.util.ArrayList;
import java.util.List;

import py.com.aseguradoratajy.tajydemo.entities.Products;
import py.com.aseguradoratajy.tajydemo.repositories.ProductsRepository;


/**
 * Created by Manu0 on 10/8/2017.
 */

public class SetupInitialData {

    public static void insertData() {
        List<Products> productsList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        Products products1 = new Products(1L, 1, "Accidentes Personales", sb.append("1. Gastos Médicos").append("\n")
                .append("2. Incapacidad Premanente").append("\n")
                .append("3. Muerte accidental(incluye motociclistas").toString());
        Products products2 = new Products(2L, 2, "Transporte", sb.append("Cobertura de daños materiales a consecuencia de:").append("\n")
                .append("1. Accidentes").append("\n")
                .append("2. Vuelvo").append("\n")
                .append("3. Descarrilamiento").append("\n")
                .append("4. Derrumbe").append("\n")
                .append("5. Incendio").append("\n")
                .append("6. Explosión").append("\n")
                .append("7. Robo").toString());
        Products products3 = new Products(3L, 3, "Multiriesgo Comercio", sb.append("1. Incendio").append("\n")
                .append("2. Robo de mercaderías/contenido general").append("\n")
                .append("3. Robo en ventanilla y/o caja fuerte").append("\n")
                .append("4. Robo de valores en tránsito").append("\n")
                .append("5. Cristales").append("\n")
                .append("6. Responsibilidad").append("\n")
                .append("7. Equipos Electrónicos").append("\n").toString());
        Products products4 = new Products(4L, 4, "Multiriesgo Hogares", sb.append("1. Incendio").append("\n")
                .append("2. Robo").append("\n")
                .append("3. Cristales").append("\n")
                .append("4. Daños por agua").append("\n")
                .append("5. Responsabilidad civil").append("\n")
                .append("6. Asistencia al hogar").append("\n")
                .append("7. Asistencia jurídica").append("\n").toString());
        Products products5 = new Products(5L, 5, "Caucción", sb.append("2. Garantía de mantenimiento de oferta").append("\n")
                .append("3. Fiel cumplimiento de contrato").append("\n")
                .append("4. Garantía de alquiler").toString());
        Products products6 = new Products(6L, 6, "Equipos Electrónicos", sb.append("1. Cobertura en caso de").append("\n")
                .append("2. Incendio").append("\n")
                .append("3. Extinción de incendios").append("\n")
                .append("4. Explosión").append("\n")
                .append("5. Rayos").append("\n")
                .append("6. Combustión espontánea").append("\n")
                .append("7. Robos").toString());
        Products products7 = new Products(7L, 7, "Vida Colectiva", sb.append("1. Seguro de ahorro y aporte, deudores").append("\n")
                .append("2. Protección de préstamos").append("\n")
                .append("3. Seguro p/ directivos y empleados").toString());
        Products products8 = new Products(8L, 8, "Seguro Agricola", sb.append("1. Sequía").append("\n")
                .append("2. Granizo, helada").append("\n")
                .append("3. Vientos fuertes").append("\n")
                .append("4. Inundaciones").append("\n")
                .append("5. Incendio").toString());

        productsList.add(products1);
        productsList.add(products2);
        productsList.add(products3);
        productsList.add(products4);
        productsList.add(products5);
        productsList.add(products6);
        productsList.add(products7);
        productsList.add(products8);

        for (Products products : productsList) {
            ProductsRepository.getDao().insertOrReplace(products);
        }
    }
}
