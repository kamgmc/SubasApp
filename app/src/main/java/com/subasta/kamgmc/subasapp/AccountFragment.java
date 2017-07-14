package com.subasta.kamgmc.subasapp;

import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;


public class AccountFragment extends Fragment {

    private View view;
    private SearchView search;
    private ImageView imgPerfil;
    private TextView nombre;
    private TextView email;
    private Sesion sesion;
    private ListView listSubasta;
    private Realm myRealm;
    private RealmList<Subasta> subastas;

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
        listSubasta = (ListView)view.findViewById(R.id.list_mis_subasta);

        Usuario usuario = sesion.getUser();
        imgPerfil.setImageBitmap(usuario.getImage());
        nombre.setText(usuario.getNombre());
        email.setText(usuario.getEmail());

        subastas = sesion.getUser().getSubastas();

        SubastaAdapter adapter = new SubastaAdapter(getActivity(),R.layout.subasta_list_row,subastas);

        listSubasta = (ListView) view.findViewById(R.id.list_mis_subasta);
        listSubasta.setAdapter(adapter);

        listSubasta.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Subasta subasta = (Subasta) parent.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(),SubastaActivity.class);
                intent.putExtra("SubastaID",subasta.getId());

                startActivityForResult(intent,1001);
            }
        });

        return view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        search = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        MenuItem icon = (MenuItem)menu.findItem(R.id.action_search);
        icon.setVisible(false);
        MenuItem close = (MenuItem)menu.findItem(R.id.endSession);
        close.setVisible(true);
        super.onCreateOptionsMenu(menu, inflater);
    }

}
