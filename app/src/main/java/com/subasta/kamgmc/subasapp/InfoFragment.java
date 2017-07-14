package com.subasta.kamgmc.subasapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.realm.Realm;

public class InfoFragment extends Fragment {
    private View view;
    private Realm myRealm;

    public InfoFragment() {
        // Required empty public constructor
    }

    public static InfoFragment newInstance() {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view==null)
        {
            view=inflater.inflate(R.layout.fragment_info, container,false);
        }
        else
        {
            ViewGroup parent = (ViewGroup) view.getParent();
            parent.removeView(view);
        }

        myRealm = Realm.getDefaultInstance();

        return view;
    }
}
