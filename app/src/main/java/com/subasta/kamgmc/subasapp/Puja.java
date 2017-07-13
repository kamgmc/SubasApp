package com.subasta.kamgmc.subasapp;

import io.realm.RealmObject;

public class Puja extends RealmObject {
    double monto;

    public Puja() {
    }

    public Puja(float monto) {
        this.monto = monto;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}
