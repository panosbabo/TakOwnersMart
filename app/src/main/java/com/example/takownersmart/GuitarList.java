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

public class GuitarList extends ArrayAdapter {

    private String guitarBrand;
    private String[] guitarModel;
    private String[] guitarPrice;
    private Integer[] imageid;
    private Activity context;

    // Constructor for each row element
    public GuitarList (Activity context, Integer[] imageid, String guitarBrand, String[] guitarModel, String[] guitarPrice) {
        super(context, R.layout.row_item, guitarModel);
        this.context = context;
        this.imageid = imageid;
        this.guitarBrand = guitarBrand;
        this.guitarModel = guitarModel;
        this.guitarPrice = guitarPrice;


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
        ImageView id_imageGuitar = (ImageView) row.findViewById(R.id.imageViewGuitar);

        // Views displayed
        txtguitarBrand.setText(guitarBrand);
        txtguitarModel.setText(guitarModel[position]);
        txtguitarPrice.setText(guitarPrice[position]);
        id_imageGuitar.setImageResource(imageid[position]);

        // Toast made when user clicks on Country label
        txtguitarBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //You clicked on the Country label
                Toast.makeText(context.getApplicationContext(), "You Selected on the Brand label of " + guitarBrand, Toast.LENGTH_SHORT).show();
            }
        });

        // Toast made when user clicks on Capital label
        txtguitarModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //You clicked on the Capital label
                Toast.makeText(context.getApplicationContext(), "You Selected on the Model of " + guitarModel[position], Toast.LENGTH_SHORT).show();
            }
        });

        // New Intent created when user clicks on Flag image
        id_imageGuitar.setOnClickListener(new View.OnClickListener() {

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


                intent.putExtras(b); //Put your id to your next Intent
                context.startActivity(intent); // Start the new activity
            }
        });
        return row;
    }
}
