package com.example.takownersmart;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class DisplayInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_info);

        // Read the variables from the bundle
        Bundle b = getIntent().getExtras();
        String brand = b.getString("brand");
        String model = b.getString("model");

        // Link our widget to the variables
        TextView display_brand = (TextView) findViewById(R.id.display_brand);
        TextView display_model = (TextView) findViewById(R.id.display_model);

        // Display the values on the screen
        display_model.setText(model);
        display_brand.setText(brand);

        // Button initialization
        Button button_info = ((Button) findViewById(R.id.button_info));

        // Button function implemented when the user clicks on
        button_info.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String query = brand + "+" + model;

//                String escapedQuery;
//                escapedQuery = URLEncoder.encode(query, "UTF-8");

                Uri uri = Uri.parse("http://www.google.com/search?q=" + query);

                Intent intent = new Intent(Intent.ACTION_VIEW, uri);

                startActivity(intent);
            }

        });
    }
}