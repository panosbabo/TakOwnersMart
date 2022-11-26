package com.example.takownersmart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

// Guitar List Class extends ArrayAdapter to be used as fetch and display on the specific activity.
public class GuitarList extends ArrayAdapter {

    // All important private variables for each guitar owner
    private String guitarBrand;
    private String[] guitarModel;
    private String[] guitarPrice;
    private Integer[] imageid;
    private String[] guitarOwner;
    private String[] ownerEmail;
    private String[] guitardetail;
    private Activity context;

    // Constructor for each row element
    public GuitarList (Activity context, Integer[] imageid, String guitarBrand, String[] guitarModel, String[] guitarPrice, String[] guitarOwner, String[] ownerEmail, String[] guitardetail) {
        super(context, R.layout.row_item, guitarModel);
        this.context = context;
        this.imageid = imageid;
        this.guitarBrand = guitarBrand;
        this.guitarModel = guitarModel;
        this.guitarPrice = guitarPrice;
        this.guitarOwner = guitarOwner;
        this.ownerEmail = ownerEmail;
        this.guitardetail = guitardetail;
    }

    // Receiving the view in a row pattern
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        // Layout inflated
        LayoutInflater inflater = context.getLayoutInflater();

        // Views initiated
        if (convertView == null)
            row = inflater.inflate(R.layout.row_item, null, true);

        TextView txtguitarBrand = (TextView) row.findViewById(R.id.textViewGuitarBrand);
        TextView txtguitarModel = (TextView) row.findViewById(R.id.textViewGuitarModel);
        TextView txtguitarPrice = (TextView) row.findViewById(R.id.textViewGuitarPrice);
        TextView txtguitarOwner = (TextView) row.findViewById(R.id.textViewGuitarOwner);
        ImageView id_imageGuitar = (ImageView) row.findViewById(R.id.imageViewGuitar);

        // Views displayed
        txtguitarBrand.setText(guitarBrand);
        txtguitarModel.setText(guitarModel[position]);
        txtguitarPrice.setText(guitarPrice[position]);
        txtguitarOwner.setText(guitarOwner[position]);
        id_imageGuitar.setImageResource(imageid[position]);

        // New Intent created when user clicks on each guitar owner item
        row.setOnClickListener(new View.OnClickListener() {

            // This mechanism allows you to call open a new activity in your screen
            Intent intent = new Intent(GuitarList.super.getContext(), DisplayInfo.class);

            @Override
            public void onClick(View v) {
                //You clicked on the Flag label

                // Bundle is the android mechanism to pass parameter to other activity
                Bundle b = new Bundle();

                // Parsing data to the next activity with the use of Bundle
                b.putString("brand", guitarBrand);
                b.putString("model", guitarModel[position]);
                b.putString("price", guitarPrice[position]);
                b.putString("owner", guitarOwner[position]);
                b.putString("ownemail", ownerEmail[position]);
                b.putString("guitardets", guitardetail[position]);


                intent.putExtras(b); //Put your id to your next Intent
                context.startActivity(intent); // Start the new activity
            }
        });
        return row;
    }
}
