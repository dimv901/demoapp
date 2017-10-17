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
        products.addStringProperty("productDetails");

        Entity insurance = schema.addEntity("Insurance");
        insurance.addIdProperty();
        insurance.addStringProperty("identifyCard");
        insurance.addIntProperty("sectionCode");
        insurance.addStringProperty("sectionDescription");
        insurance.addStringProperty("policy");
        insurance.addStringProperty("endorsement");
        insurance.addStringProperty("feeDetail");
        insurance.addStringProperty("ruc");
        insurance.addStringProperty("insurance");
        insurance.addStringProperty("comercialPhone");
        insurance.addStringProperty("comercialAddress");
        insurance.addStringProperty("parcitularAddress");
        insurance.addStringProperty("dateFrom");
        insurance.addStringProperty("dateTo");
        insurance.addStringProperty("issue");
        insurance.addStringProperty("expiration");
        insurance.addStringProperty("amount");
        insurance.addStringProperty("payment");
        insurance.addStringProperty("balance");
        insurance.addStringProperty("lastPayment");


        Entity marks = schema.addEntity("Marks");
        marks.addIdProperty();
        marks.addIntProperty("brandCode");
        marks.addStringProperty("description");

        Entity vehiclesModel = schema.addEntity("VehiclesModel");
        vehiclesModel.addIdProperty();
        vehiclesModel.addIntProperty("modelCode");
        vehiclesModel.addStringProperty("description");
        vehiclesModel.addIntProperty("brandId");




        new DaoGenerator().generateAll(schema, "../app/src/main/java");

    }
}
