package com.example.yurifws.teste.model;

import java.util.HashMap;

/**
 * Created by yurifws on 11/19/17.
 */

public class Espetaculo {

    /*
    * id
    * nome
    * sinopse
    * id ficha tecnica
    * classicicacao
    * genero
    * duracao
    * */
    private String id;
    private String nome;
    private String sinopse;
    //private String idFichaTecnica;
    private String classificacao;
    private String genero;
    private String duracao;

    public Espetaculo() {
        this.id = "";
        this.nome = "";
        this.sinopse = "";
        this.classificacao = "";
        this.genero = "";
        this.duracao = "";
    }

    public Espetaculo(String id,HashMap<String, String> mapEspetaculo) {
        this.id = id;
        this.nome = mapEspetaculo.get("nome");
        this.sinopse = mapEspetaculo.get("sinopse");
        this.classificacao = mapEspetaculo.get("classificacao");
        this.genero = mapEspetaculo.get("genero");
        this.duracao = mapEspetaculo.get("duracao");
    }

    public Espetaculo(String id, String nome, String sinopse, String classificacao, String genero, String duracao) {
        this.id = id;
        this.nome = nome;
        this.sinopse = sinopse;
        this.classificacao = classificacao;
        this.genero = genero;
        this.duracao = duracao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    @Override
    public String toString() {
        return "Espetaculo{" +
                "nome='" + nome + '\'' +
                ", sinopse='" + sinopse + '\'' +
                ", classificacao='" + classificacao + '\'' +
                ", genero='" + genero + '\'' +
                ", duracao='" + duracao + '\'' +
                '}';
    }


}
