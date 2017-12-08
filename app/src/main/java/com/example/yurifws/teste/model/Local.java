package com.example.yurifws.teste.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yurifws on 11/21/17.
 */

public class Local {

    /*
    * latitude
    * longitude
    * endereco
    * */
    private String id;
    private double latitude;
    private double longitude;
    private String idEndereco;

    public Local() {

        this.id = "";
        this.latitude = 0;
        this.longitude = 0;
        this.idEndereco = "";
    }

    public Local(String id,HashMap<String, String> mapLocal) {
        this.id = id;
        this.latitude = Double.parseDouble(mapLocal.get("latitude"));
        this.longitude = Double.parseDouble(mapLocal.get("longitude"));
        for (Map.Entry<String, String> e : mapLocal.entrySet()) {
            String key = e.getKey();
            String value = e.getValue();
            if (value.equals("endereco")){
                idEndereco = key;
            }
        }
    }

    public Local(String id, double latitude, double longitude, String idEndereco) {

        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.idEndereco = idEndereco;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(String idEndereco) {
        this.idEndereco = idEndereco;
    }
}
