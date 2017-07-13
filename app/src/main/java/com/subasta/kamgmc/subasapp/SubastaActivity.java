package com.subasta.kamgmc.subasapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.realm.Realm;

public class SubastaActivity extends AppCompatActivity {

    private ViewPager gallery;
    private Realm myRealm;
    private Toolbar toolbar;
    private TextView title;
    private TextView description;
    private TextView mejorPuja;
    private TextView pujas;
    private Button button;
    private Subasta subasta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subasta);

        myRealm = Realm.getDefaultInstance();
        title = (TextView) findViewById(R.id.title);
        description = (TextView) findViewById(R.id.description);
        mejorPuja = (TextView)findViewById(R.id.mejorPuja);
        pujas = (TextView)findViewById(R.id.pujas);
        button = (Button)findViewById(R.id.btnPujar);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int id = getIntent().getIntExtra("SubastaID",0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        subasta = myRealm.where(Subasta.class).equalTo("id",id).findAll().first();

        gallery = (ViewPager) findViewById(R.id.gallery);
        GalleryAdapter adapter = new GalleryAdapter(this);

        adapter.setImages(subasta.getImages());
        gallery.setAdapter(adapter);

        getSupportActionBar().setTitle(subasta.getTitle());
        title.setText(subasta.getTitle());
        description.setText(subasta.getDescription());
        String monto = subasta.mejorPuja().getMonto()+" Bs";
        mejorPuja.setText(monto);
        pujas.setText(String.valueOf(subasta.getPujas().size()));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),PujaActivity.class);
                intent.putExtra("SubastaID",subasta.getId());
                startActivity(intent);
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, Main.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this,Main.class);
        intent.putExtra("SubastaID",subasta.getId());
        startActivity(intent);
    }
}
