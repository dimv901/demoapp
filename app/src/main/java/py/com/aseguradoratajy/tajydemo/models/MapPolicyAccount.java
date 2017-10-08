package py.com.aseguradoratajy.tajydemo.models;


import org.json.JSONException;
import org.json.JSONObject;

import py.com.aseguradoratajy.tajydemo.R;

/**
 * Created by Manu0 on 10/7/2017.
 */

public class MapPolicyAccount {

    public static Insurance getInsuranceFromJson(JSONObject data) {
        Insurance insuranceObject = new Insurance();
        try {

            if (data.has("cedula")) {
                insuranceObject.setIdentifyCard(data.getString("cedula"));
            }

            if (data.has("seccion")) {
                insuranceObject.setSctionCode(data.getInt("seccion"));
            }
            if (data.has("poliza")) {
                insuranceObject.setPolicy(data.getString("poliza"));
            }

            if (data.has("endoso")) {
                insuranceObject.setEndorsement(data.getString("endoso"));
            }

            if (data.has("cuota")) {
                insuranceObject.setFeeDetails(data.getString("cuota"));
            }

            if (data.has("ruc")) {
                insuranceObject.setRuc(data.getString("ruc"));
            }

            if (data.has("asegurado")) {
                insuranceObject.setIssue(data.getString("asegurado"));
            }

            if (data.has("telComercial")) {
                insuranceObject.setComercialPhone(data.getString("telComercial"));
            }

            if (data.has("dirComercial")) {
                insuranceObject.setComercialAddress(data.getString("dirComercial"));
            }

            if (data.has("dirParticular")) {
                insuranceObject.setParticularAddress(data.getString("dirParticular"));
            }

            if (data.has("desde")) {
                insuranceObject.setDateFrom(data.getString("desde"));
            }

            if (data.has("hasta")) {
                insuranceObject.setDateTo(data.getString("hasta"));
            }

            if (data.has("emision")) {
                insuranceObject.setIssue(data.getString("emision"));
            }

            if (data.has("vencimiento")) {
                insuranceObject.setExpiration(data.getString("vencimiento"));
            }

            if (data.has("monto")) {
                insuranceObject.setAmount(data.getString("monto"));
            }

            if (data.has("pagado")) {
                insuranceObject.setPayment(data.getString("pagado"));
            }

            if (data.has("utimoPago")) {
                insuranceObject.setLastPayment(data.getString("pagado"));
            }

            if (data.has("sectionDescription")) {
                insuranceObject.setSectionDescription(data.getString("sectionDescription"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return insuranceObject;
    }


    public static int policyIconType(int sectionCode) {

        int icon = R.mipmap.tajy_logo;
        // VIDA
        if (isBetween(sectionCode, 100, 116)) {
            icon = R.mipmap.vida_colectiva;
        }
        // INCENDIO
        if (isBetween(sectionCode, 200, 202)) {
            icon = R.mipmap.ic_fire_man; // TODO CAMBIAR ICONO
        }
        // TRANSPORTE
        if (isBetween(sectionCode, 300, 304)) {
            icon = R.mipmap.ic_transporte;
        }
        // ACCIDENTES PERSONALES
        if (isBetween(sectionCode, 400, 403)) {
            icon = R.mipmap.ic_accidentes_personales_resource;
        }

        // AUTOMÓVILES
        if (isBetween(sectionCode, 500, 501)) {
            icon = R.mipmap.automovil;
        }

        // ACCIDENTES A PASAJEROS
        if (isBetween(sectionCode, 600, 601)) {
            icon = R.mipmap.asistencia_al_viajero;
        }

        // ROBO
        if (isBetween(sectionCode, 700, 799)) {
            icon = R.mipmap.tajy_logo; // TODO AGREGAR LOGO DE ROBO
        }
        // CIRSTALES
        if (isBetween(sectionCode, 800, 801)) {
            icon = R.mipmap.tajy_logo; // TODO AGREGAR LOGO DE CRISTALES
        }
        // GANADO
        if (isBetween(sectionCode, 900, 901)) {
            icon = R.mipmap.seguro_agricola;
        }

        // RIESGOS VARIOS
        if (isBetween(sectionCode, 1000, 1022)) {
            icon = R.mipmap.ic_multiriesgo_comercio; // TODO AGREGAR LOCO DE RIESGOS VARIOS
        }
        // RESPONSABILIDAD CIVIL
        if (isBetween(sectionCode, 1100, 1112)) {
            icon = R.mipmap.tajy_logo; // TODO AGREGAR ICONO DE RESPONSABILIDAD CIVIL
        }
        // SEGURO AGRICOLA
        if (isBetween(sectionCode, 1200, 1223)) {
            icon = R.mipmap.seguro_agricola;
        }

        // AERONAVEGACION
        if (isBetween(sectionCode, 1300, 1304)) {
            icon = R.mipmap.tajy_logo; // TODO AGREGAR ICONO DE AERONAVEGACION
        }

        // RIESGOS TECNICOS
        if (isBetween(sectionCode, 1400, 1404)) {
            icon = R.mipmap.tajy_logo; // TODO AGREGAR ICONO DE RIESGOS TECNICOS
        }

        // CAUCION
        if (isBetween(sectionCode, 1500, 1531)) {
            icon = R.mipmap.caucion;
        }

        // MULTIRIESGO
        if (isBetween(sectionCode, 1600, 1601)) {
            icon = R.mipmap.ic_multiriesgo_hogares; // TODO VERIFICAR EL ICONO GENERAL DE MULTIRIESGO
        }

        return icon;

    }


    public static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }


}
