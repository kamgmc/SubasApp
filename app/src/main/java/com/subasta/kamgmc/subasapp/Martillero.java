package com.subasta.kamgmc.subasapp;

import android.view.View;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;

public class Martillero {
    private RealmList<Subasta> subastasMartillero;

    public RealmList<Subasta> getSubastasMartillero() {

        return subastasMartillero;
    }

    public void setSubastasMartillero(RealmList<Subasta> subastasMartillero) {
        this.subastasMartillero = subastasMartillero;
    }

}
