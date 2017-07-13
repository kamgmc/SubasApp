package com.subasta.kamgmc.subasapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import io.realm.RealmList;

public class GalleryAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater inflater;
    private RealmList<RealmBitmap> images;

    public void setImages(RealmList<RealmBitmap> images) {
        this.images = images;
    }

    public GalleryAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.subasta_gallery_item,null);
        ImageView image = (ImageView) view.findViewById(R.id.gallery_image);
        image.setImageBitmap(this.images.get(position).getBitmap());

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view,0);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }

}
