package com.subasta.kamgmc.subasapp;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Subasta extends RealmObject implements Serializable{
    private String title;
    private String description;
    private RealmList<Puja> pujas;
    private double pujaMinima;
    private int image;
    private int id;

    public Subasta(){
        //Empty Constructor
    }
    public Subasta(int img, String title, String description){
        super();
        this.image = img;
        this.description = description;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealmList<Puja> getPujas() {
        return pujas;
    }

    public void setPujas(RealmList<Puja> pujas) {
        this.pujas = pujas;
    }

    public Puja mejorPuja(){
        return this.pujas.sort("monto").last();
    }

    public double getPujaMinima() {
        return pujaMinima;
    }

    public void setPujaMinima(double minPuja) {
        this.pujaMinima = minPuja;
    }
}
