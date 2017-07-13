package com.subasta.kamgmc.subasapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.ByteArrayOutputStream;
import io.realm.RealmObject;

public class RealmBitmap extends RealmObject{
    private byte[] bitmap;

    public Bitmap getBitmap() {
        byte[] bitmapData = this.bitmap;
        return BitmapFactory.decodeByteArray(bitmapData, 0, bitmapData.length);
    }

    public void setBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        this.bitmap = stream.toByteArray();
    }
}
