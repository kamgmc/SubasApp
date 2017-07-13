package com.subasta.kamgmc.subasapp;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Usuario extends RealmObject{

    private String nombre;
    private String email;
    private String password;
    private RealmList<Puja> pujas;
    private RealmList<Subasta> subastas;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RealmList<Puja> getPujas() {
        return pujas;
    }

    public void setPujas(RealmList<Puja> pujas) {
        this.pujas = pujas;
    }

    public RealmList<Subasta> getSubastas() {
        return subastas;
    }

    public void setSubastas(RealmList<Subasta> subastas) {
        this.subastas = subastas;
    }
}
