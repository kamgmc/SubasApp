package com.subasta.kamgmc.subasapp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;

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

                RealmBitmap image = realm.createObject(RealmBitmap.class);
                image.setBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.fuerte));

                RealmBitmap image1 = realm.createObject(RealmBitmap.class);
                image1.setBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.wolf));

                RealmList<RealmBitmap> images = new RealmList<>();
                images.add(image);

                Subasta subasta = realm.createObject(Subasta.class);
                subasta.setImages(images);
                subasta.setTitle("Discografia");
                subasta.setPujas(pujas);
                subasta.setId(125636);
                subasta.setPujaMinima(250.5);
                subasta.setDescription("Discografia completa de Miranda! Quiero vivir a tu lado");

                RealmList<Subasta> subastas = new RealmList<>();
                subastas.add(subasta);

                Usuario usuario = realm.createObject(Usuario.class);
                usuario.setNombre("Kevin Martinez");
                usuario.setEmail("kamgmc@gmail.com");
                usuario.setPassword("6bf45147ae4dacfc78cd5e99aef8d4fe");
                usuario.setPujas(pujas);
                usuario.setSubastas(subastas);

                pujas.add(puja2);
                images.add(0,image1);

                Subasta subasta1 = realm.createObject(Subasta.class);
                subasta1.setImages(images);
                subasta1.setTitle("WallPaper");
                subasta1.setPujas(pujas);
                subasta1.setId(125648);
                subasta1.setPujaMinima(180);
                subasta1.setDescription("Wallpaper de un lobo solitario");

                subastas.add(subasta1);

               /* Martillero martillero = realm.createObject(Martillero.class);
                martillero.setNombre("Carlos Alonzo");
                martillero.setEmail("calonzo@gmail.com");
                martillero.setPassword("21232f297a57a5a743894a0e4a801fc3");
                martillero.setPujas(pujas);
                martillero.setSubastasMartillero(subastas);*/

                realm.close();
            }
        }).name("RealmDB").build();
        Realm.setDefaultConfiguration(configuration);

        SharedPreferences prefs = getSharedPreferences("Usuario", Context.MODE_PRIVATE);
            if(prefs.getBoolean("inicial",true)){
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("nombre", "");
                editor.putString("email", "");
                editor.putString("password", "");
                editor.putBoolean("logged",false);
                editor.putBoolean("inicial",false);
                editor.apply();
            }
    }
}
