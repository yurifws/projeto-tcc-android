package com.example.yurifws.teste.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yurifws on 12/6/17.
 */

public class Evento {

    private String idEspetaculo;
    private ArrayList<String> idsLocais;

    public Evento(){
        idEspetaculo = "";
        idsLocais = new ArrayList<>();
    }

    public Evento(String idEspetaculo, HashMap<String, String> mapEvento){
        this.idEspetaculo = idEspetaculo;
        this.idsLocais = new ArrayList<>();
        for (Map.Entry<String, String> e : mapEvento.entrySet()) {
            String key = e.getKey();
            String value = e.getValue();
            if (value.equals("local")){
                this.idsLocais.add(key);
            }

        }

    }

    public Evento(String idEspetaculo, ArrayList<String> idsLocais) {
        this.idEspetaculo = idEspetaculo;
        this.idsLocais = idsLocais;
    }

    public String getIdEspetaculo() {
        return idEspetaculo;
    }

    public void setIdEspetaculo(String idEspetaculo) {
        this.idEspetaculo = idEspetaculo;
    }

    public ArrayList<String> getIdsLocais() {
        return idsLocais;
    }

    public void setIdsLocais(ArrayList<String> idsLocais) {
        this.idsLocais = idsLocais;
    }
}
