package com.example.law.guidemobilelegendsver1;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Belal on 7/22/2015.
 */
public class CustomList extends ArrayAdapter<String> {
    private String[] id_hero;
    private String[] name;

    private Bitmap[] bitmaps;
    private Activity context;

    public CustomList(Activity context, String[] id_hero, String[] name, Bitmap[] bitmaps) {
        super(context, R.layout.list_item, id_hero);
        this.context = context;
        this.id_hero = id_hero;
        this.name= name;

        this.bitmaps= bitmaps;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_item, null, true);
        TextView txtnama = (TextView) listViewItem.findViewById(R.id.name);
       // TextView txtstatus = (TextView) listViewItem.findViewById(R.id.status);
        ImageView image = (ImageView) listViewItem.findViewById(R.id.imageDownloaded);
    //    txtstatus.setText(id_hero[position]);
        txtnama.setText(name[position]);

        image.setImageBitmap(bitmaps[position]);
       //image.setImageBitmap(Bitmap.createScaledBitmap(bitmaps[position],100,100,true));
      // image.setImageBitmap(Bitmap.createScaledBitmap(bitmaps[position],100,50,false));
        return  listViewItem;
    }
}