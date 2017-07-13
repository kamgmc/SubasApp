package com.subasta.kamgmc.subasapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;

public class PujaActivity extends AppCompatActivity {
    Realm myRealm;
    Button pujar;
    TextView monto;
    TextView minMonto;
    EditText puja;
    Subasta subasta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puja);

        myRealm = Realm.getDefaultInstance();
        pujar = (Button)findViewById(R.id.btnPujar);
        monto = (TextView)findViewById(R.id.monto);
        puja = (EditText)findViewById(R.id.nuevaPuja);
        minMonto = (TextView) findViewById(R.id.minMonto);

        int id = getIntent().getIntExtra("SubastaID",0);

        subasta = myRealm.where(Subasta.class).equalTo("id",id).findFirst();

        String sMonto = subasta.mejorPuja().getMonto() + " Bs";
        String sMinMonto = "min " + (subasta.mejorPuja().getMonto()+subasta.getPujaMinima()) + " Bs";
        monto.setText(sMonto);
        minMonto.setText(sMinMonto);

        pujar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double pujaMinima = subasta.mejorPuja().getMonto() + subasta.getPujaMinima();
                double nuevaPuja = Double.parseDouble(puja.getText().toString());
                if(nuevaPuja < pujaMinima)
                    Toast.makeText(v.getContext(),"Monto invalido",Toast.LENGTH_LONG).show();
                else {
                    myRealm.beginTransaction();
                    Puja puja = myRealm.createObject(Puja.class);
                    puja.setMonto(nuevaPuja);
                    subasta.getPujas().add(puja);
                    myRealm.commitTransaction();
                    Intent intent = new Intent(v.getContext(), SubastaActivity.class);
                    intent.putExtra("SubastaID", subasta.getId());
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this,Main.class);
        intent.putExtra("SubastaID",subasta.getId());
        startActivity(intent);
    }
}
