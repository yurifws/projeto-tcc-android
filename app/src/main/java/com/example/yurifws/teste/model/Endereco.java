package com.example.yurifws.teste.model;

import java.util.HashMap;

/**
 * Created by yurifws on 11/21/17.
 */

public class Endereco {

    /*
    * id
    * logradouro
    * cep
    * numero
    * bairro
    * pais
    * cidade
    * uf
    * bairro
    * */

    private String id;
    private String logradouro;
    private String cep;
    private String numero;
    private String bairro;
    private String pais;
    private String cidade;
    private String uf;

    public Endereco() {
        this.id = "";
        this.logradouro = "";
        this.cep = "";
        this.numero = "";
        this.bairro = "";
        this.pais = "";
        this.cidade = "";
        this.uf = "";
    }

    public Endereco(String id, HashMap<String, String> mapEndereco) {
        this.id = id;
        this.logradouro = mapEndereco.get("logradouro");
        this.cep = mapEndereco.get("cep");
        this.numero = mapEndereco.get("numero");
        this.bairro = mapEndereco.get("bairro");
        this.pais = mapEndereco.get("pais");
        this.cidade = mapEndereco.get("cidade");
        this.uf = mapEndereco.get("uf");
    }

    public Endereco(String id, String logradouro, String cep, String numero, String bairro, String pais, String cidade, String uf) {
        this.id = id;
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.bairro = bairro;
        this.pais = pais;
        this.cidade = cidade;
        this.uf = uf;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}

