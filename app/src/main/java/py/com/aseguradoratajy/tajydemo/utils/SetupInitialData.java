package py.com.aseguradoratajy.tajydemo.utils;

import java.security.Policy;
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
        StringBuilder sb;
        Products products1 = new Products(1L, 1, "Accidentes Personales", new StringBuilder().append("1. Gastos Médicos").append("\n")
                .append("2. Incapacidad Permanente").append("\n")
                .append("3. Muerte accidental(incluye motociclistas)").toString());
        Products products2 = new Products(2L, 2, "Transporte", new StringBuilder().append("Cobertura de daños materiales a consecuencia de:").append("\n")
                .append("1. Accidentes").append("\n")
                .append("2. Vuelvo").append("\n")
                .append("3. Descarrilamiento").append("\n")
                .append("4. Derrumbe").append("\n")
                .append("5. Incendio").append("\n")
                .append("6. Explosión").append("\n")
                .append("7. Robo").toString());
        Products products3 = new Products(3L, 3, "Multiriesgo Comercio",  new StringBuilder().append("1. Incendio").append("\n")
                .append("2. Robo de mercaderías/contenido general").append("\n")
                .append("3. Robo en ventanilla y/o caja fuerte").append("\n")
                .append("4. Robo de valores en tránsito").append("\n")
                .append("5. Cristales").append("\n")
                .append("6. Responsibilidad").append("\n")
                .append("7. Equipos Electrónicos").append("\n").toString());
        Products products4 = new Products(4L, 4, "Multiriesgo Hogares",  new StringBuilder().append("1. Incendio").append("\n")
                .append("2. Robo").append("\n")
                .append("3. Cristales").append("\n")
                .append("4. Daños por agua").append("\n")
                .append("5. Responsabilidad civil").append("\n")
                .append("6. Asistencia al hogar").append("\n")
                .append("7. Asistencia jurídica").append("\n").toString());
        Products products5 = new Products(5L, 5, "Caución",  new StringBuilder().append("1. Garantía de mantenimiento de oferta").append("\n")
                .append("2. Fiel cumplimiento de contrato").append("\n")
                .append("3. Garantía de alquiler").toString());
        Products products6 = new Products(6L, 6, "Equipos Electrónicos",  new StringBuilder().append("Cobertura en caso de: ").append("\n")
                .append("1. Incendio").append("\n")
                .append("2. Extinción de incendios").append("\n")
                .append("3. Explosión").append("\n")
                .append("4. Rayos").append("\n")
                .append("5. Combustión espontánea").append("\n")
                .append("6. Robos").toString());
        Products products7 = new Products(7L, 7, "Vida Colectiva",  new StringBuilder().append("1. Seguro de ahorro y aporte, deudores").append("\n")
                .append("2. Protección de préstamos").append("\n")
                .append("3. Seguro p/ directivos y empleados").toString());
        Products products8 = new Products(8L, 8, "Seguro Agricola",  new StringBuilder().append("1. Sequía").append("\n")
                .append("2. Granizo, helada").append("\n")
                .append("3. Vientos fuertes").append("\n")
                .append("4. Inundaciones").append("\n")
                .append("5. Incendio").toString());
        Products products9 = new Products(9L, 9, "Robo",  new StringBuilder().append("1. Locales comerciales, civiles e industriales").append("\n").append("2. Valores en caja fuerte y/o ventanilla").append("\n")
                .append("3. Fidelidad de empleados").append("\n").append("4. Tarjetas de crédito").append("\n").append("5. Valores en tránsito").toString());

        Products products10 = new Products(10L, 10, "Responsabilidad civil",  new StringBuilder().append("1. Cobertura en caso de que deba rearcirse cualquier daño, sea a personas o bienes materiales").append("\n")
                .append("2. Responsabilidad Civil").append("\n")
                .append("3. Responsabilidad civil comprensiva").append("\n")
                .append("4. Responsabilidad civil para talleres").append("\n")
                .append("5. Responsabilidad civil vida privada").append("\n")
                .append("6. Responsabilidad civil construcción de edificios").toString());
        Products products11 = new Products(11L, 11, "Protección familar en Grupo", "Brinda un apoyo económico en momentos de dificultad Cubertura por fallecimiento del titular, cónyuge e hijos, sus padres y/o hermanos.");
        Products products12 = new Products(12L, 12, "Integral p/ Cooperativas - Bancos",  new StringBuilder().append("1. Responsabilidad agregada por año calendario").append("\n")
                .append("2. Fidelidad del empleado (fraude de empleados y directores)").append("\n")
                .append("3. Pérdidas o daños por incendio a la propiedad por actos delictivos").append("\n")
                .append("4. Pérdida de propiedad en tránsito").append("\n")
                .append("5. Pérdidas por falsificación").append("\n")
                .append("6. Gastos por falsificación").append("\n")
                .append("7. Pérdida de propiedad de empleados o socios").toString());
        Products products13 = new Products(13L, 13, "Incendio",  new StringBuilder().append("1. Rayo o explosión").append("\n")
                .append("2. Tumulto y/o Huelga").append("\n")
                .append("3. Impacto de vehículos terrestre").append("\n")
                .append("4. Huracán, vendaval, ciclón o tornado").append("\n")
                .append("5. Impacto de vehículos aéreos o sus partes").toString());
        Products products14 = new Products(14L, 14, "Automóvil",  new StringBuilder().append("1. Accidentes").append("\n")
                .append("2. Incendio").append("\n")
                .append("3. Robo").append("\n")
                .append("4. Tumulto popular y/o Huelga").append("\n")
                .append("5. Accesorios").append("\n")
                .append("6. Responsabilidad").append("\n")
                .append("7. Ocupantes del vehículo").toString());
        productsList.add(products1);
        productsList.add(products2);
        productsList.add(products3);
        productsList.add(products4);
        productsList.add(products5);
        productsList.add(products6);
        productsList.add(products7);
        productsList.add(products8);
        productsList.add(products9);
        productsList.add(products10);
        productsList.add(products11);
        productsList.add(products12);
        productsList.add(products13);
        productsList.add(products14);

        for (Products products : productsList) {
            ProductsRepository.getDao().insertOrReplace(products);
        }
    }
}
