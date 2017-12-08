package com.example.yurifws.teste.model;

import java.util.HashMap;

/**
 * Created by yurifws on 10/20/17.
 */

public class Usuario {

    private String id;
    private String email;
    private String senha;

    public Usuario() {
        this.id = "";
        this.email = "";
        this.senha = "";

    }

    public Usuario(String id, HashMap<String, String> mapUsuario) {
        this.id = id;
        this.email = mapUsuario.get("email");
        this.senha = mapUsuario.get("senha");

    }

    public Usuario(String id, String email, String senha) {
        this.id = id;
        this.email = email;
        this.senha = senha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
