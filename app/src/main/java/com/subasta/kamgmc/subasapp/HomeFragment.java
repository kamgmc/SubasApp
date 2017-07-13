package com.subasta.kamgmc.subasapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import io.realm.Realm;
import io.realm.RealmResults;

public class HomeFragment extends Fragment {

    private View view;
    private ListView listSubasta;
    private Realm myRealm;

    public HomeFragment() {
        // Required empty public constructor
    }
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(view==null)
        {
            view=inflater.inflate(R.layout.fragment_home, container,false);
        }
        else
        {
            ViewGroup parent = (ViewGroup) view.getParent();
            parent.removeView(view);
        }

        myRealm = Realm.getDefaultInstance();
        RealmResults<Subasta> subastas = myRealm.where(Subasta.class).findAll();

        SubastaAdapter adapter = new SubastaAdapter(getActivity(),R.layout.subasta_list_row,subastas);

        listSubasta = (ListView) view.findViewById(R.id.list_subasta);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001) {
            RealmResults<Subasta> subastas = myRealm.where(Subasta.class).findAll();
            SubastaAdapter adapter = new SubastaAdapter(getActivity(),R.layout.subasta_list_row,subastas);
            listSubasta = (ListView) view.findViewById(R.id.list_subasta);
            listSubasta.setAdapter(adapter);
        }
    }
}
