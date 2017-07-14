package com.subasta.kamgmc.subasapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import io.realm.Realm;


public class AccountFragment extends Fragment {

    private View view;
    private SearchView search;
    private ImageView imgPerfil;
    private TextView nombre;
    private TextView email;
    private Sesion sesion;
    private Realm myRealm;

    public AccountFragment() {
        // Required empty public constructor
    }
    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        sesion = new Sesion(getActivity());
        if(view==null)
        {
            view=inflater.inflate(R.layout.fragment_account, container,false);
        }
        else
        {
            ViewGroup parent = (ViewGroup) view.getParent();
            parent.removeView(view);
        }
        myRealm = Realm.getDefaultInstance();
        imgPerfil = (ImageView)view.findViewById(R.id.img_perfil);
        nombre = (TextView)view.findViewById(R.id.perfil_nombre);
        email = (TextView)view.findViewById(R.id.perfil_email);

        Usuario usuario = sesion.getUser();
        imgPerfil.setImageBitmap(usuario.getImage());
        nombre.setText(usuario.getNombre());
        email.setText(usuario.getEmail());

        return view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        search = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        MenuItem icon = (MenuItem)menu.findItem(R.id.action_search);
        icon.setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
