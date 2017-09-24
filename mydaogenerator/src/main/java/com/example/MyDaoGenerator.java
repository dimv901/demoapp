package com.example;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MyDaoGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "py.com.aseguradoratajy.tajydemo.entities");
        schema.enableKeepSectionsByDefault();

        Entity products = schema.addEntity("Products");
        products.addIdProperty();
        products.addIntProperty("productId");
        products.addStringProperty("productDescription");

        Entity services = schema.addEntity("Services");
        services.addIdProperty();
        services.addIntProperty("serviceId");
        services.addStringProperty("serviceDescription");


        new DaoGenerator().generateAll(schema, "../app/src/main/java");

    }
}
