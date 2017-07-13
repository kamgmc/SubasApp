package com.subasta.kamgmc.subasapp;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        RealmConfiguration configuration = new RealmConfiguration.Builder().initialData(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Puja puja = realm.createObject(Puja.class);
                puja.setMonto(15000);

                Puja puja2 = realm.createObject(Puja.class);
                puja2.setMonto(2563.30);

                RealmList<Puja> pujas = new RealmList<>();
                pujas.add(puja);

                Subasta subasta = realm.createObject(Subasta.class);
                subasta.setImage(R.drawable.fuerte);
                subasta.setTitle("Discografia");
                subasta.setPujas(pujas);
                subasta.setId(125636);
                subasta.setPujaMinima(50.5);
                subasta.setDescription("Discografia completa de Miranda! Quiero vivir a tu lado");

                pujas.add(puja2);
                Subasta subasta1 = realm.createObject(Subasta.class);
                subasta1.setImage(R.drawable.wolf);
                subasta1.setTitle("WallPaper");
                subasta1.setPujas(pujas);
                subasta1.setId(125648);
                subasta1.setPujaMinima(80);
                subasta1.setDescription("Wallpaper de un lobo solitario");
                realm.close();
            }
        }).name("RealmDB").build();
        Realm.setDefaultConfiguration(configuration);
    }
}
