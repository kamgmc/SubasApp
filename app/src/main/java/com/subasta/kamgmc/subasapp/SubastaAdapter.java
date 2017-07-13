package com.subasta.kamgmc.subasapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import io.realm.RealmResults;

public class SubastaAdapter extends ArrayAdapter<Subasta>{
    private Context myContext;
    private int myLayoutResourceID;
    private RealmResults<Subasta>  myData = null;

    public SubastaAdapter(Context context,int layoutResourceID, RealmResults<Subasta> data){
        super(context,layoutResourceID,data);

        this.myContext = context;
        this.myLayoutResourceID = layoutResourceID;
        this.myData = data;
    }
    @NonNull
    public View getView(int position,View convertView, ViewGroup parent){
        View row = convertView;
        SubastaHolder holder = null;

        if(row == null){
            LayoutInflater inflater = ((Activity)myContext).getLayoutInflater();
            row = inflater.inflate(myLayoutResourceID,parent,false);

            holder = new SubastaHolder();
            holder.image = (ImageView)row.findViewById(R.id.img_subasta);
            holder.title = (TextView)row.findViewById(R.id.title_subasta);
            holder.description = (TextView)row.findViewById(R.id.desc_subasta);
            holder.monto = (TextView)row.findViewById(R.id.monto_subasta);
            row.setTag(holder);
        }
        else{
            holder = (SubastaHolder)row.getTag();
        }

        Subasta subasta = myData.get(position);
        holder.title.setText(subasta.getTitle());
        holder.description.setText(subasta.getDescription());
        holder.image.setImageBitmap(subasta.getImages().get(0).getBitmap());
        String monto = subasta.mejorPuja().getMonto() + " Bs";
        holder.monto.setText(monto);

        return row;
    }

    static class SubastaHolder{
        TextView title;
        ImageView image;
        TextView description;
        TextView monto;
    }
}
