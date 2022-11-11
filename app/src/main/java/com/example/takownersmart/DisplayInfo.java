package com.example.takownersmart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// Activity that displays more detailed info whenever the user clicks on each guitar item
public class DisplayInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_info);

        // Read the variables from the bundle
        Bundle b = getIntent().getExtras();
        String brand = b.getString("brand");
        String model = b.getString("model");
        String price = b.getString("price");
        String owner = b.getString("owner");
        String ownemail = b.getString("ownemail");

        // Link our widget to the variables
        TextView display_brand = (TextView) findViewById(R.id.display_brand_view);
        TextView display_model = (TextView) findViewById(R.id.display_model_view);
        TextView display_price = (TextView) findViewById(R.id.display_price_view);
        TextView display_owner = (TextView) findViewById(R.id.display_owner_view);
        TextView display_owneremail = (TextView) findViewById(R.id.display_owneremail_view);

        // Display the values on the screen
        display_model.setText(model);
        display_brand.setText(brand);
        display_price.setText(price);
        display_owner.setText(owner);
        display_owneremail.setText(ownemail);


        // Button initialization for more detailed information
        Button button_info = ((Button) findViewById(R.id.button_info));

        // Button function implemented to search on the web when the user clicks on more details information
        button_info.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // String query that catches the specific guitar model data
                String query = brand + "+" + model;

                // Parsing guitar data to a uri for web search
                Uri uri = Uri.parse("http://www.google.com/search?q=" + query);

                // New intent created
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);

                // Start of the next activity
                startActivity(intent);
            }

        });
    }
}