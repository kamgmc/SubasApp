package com.subasta.kamgmc.subasapp;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import io.realm.Realm;

public class Sesion {
    private Usuario user;
    private Realm myRealm;

    public Usuario getUser() {
        return user;
    }

    public Sesion(Context context) {
        myRealm = Realm.getDefaultInstance();
        SharedPreferences prefs = context.getSharedPreferences("Usuario", Context.MODE_PRIVATE);
        String email = prefs.getString("email","");
        String password = prefs.getString("password","");
        this.user = myRealm.where(Usuario.class).equalTo("email",email).equalTo("password",password).findFirst();
    }

    public void iniciar(Context context,Usuario usuario){
        SharedPreferences prefs = context.getSharedPreferences("Usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("nombre", usuario.getNombre());
        editor.putString("email", usuario.getEmail());
        editor.putString("password", usuario.getPassword());
        editor.putBoolean("logged",true);
        editor.apply();
        this.user = usuario;
    }

    public void cerrar(Context context){
        SharedPreferences prefs = context.getSharedPreferences("Usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("nombre", "");
        editor.putString("email", "");
        editor.putString("password", "");
        editor.putBoolean("logged",false);
        editor.apply();
        this.user = null;
    }
    public boolean isLogged(){
        return this.user != null;
    }

}
