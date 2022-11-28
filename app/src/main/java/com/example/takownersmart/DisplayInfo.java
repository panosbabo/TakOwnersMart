package com.example.takownersmart;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.takownersmart.db.Guitar;
import com.example.takownersmart.db.WishListDatabase;

// Activity that displays more detailed info whenever the user clicks on each guitar item
public class DisplayInfo extends AppCompatActivity {

    // Creating private variable for activity context
    private Activity context;
    private String brand = "";
    private String model = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_info);

        // Read the variables from the bundle
        Bundle b = getIntent().getExtras();
        brand = b.getString("brand");
        model = b.getString("model");
        String price = b.getString("price");
        String owner = b.getString("owner");
        String ownemail = b.getString("ownemail");
        String guitardet = b.getString("guitardets");

        // Link our widget to the variables
        TextView display_brand = (TextView) findViewById(R.id.display_brand_view);
        TextView display_model = (TextView) findViewById(R.id.display_model_view);
        TextView display_price = (TextView) findViewById(R.id.display_price_view);
        TextView display_owner = (TextView) findViewById(R.id.display_owner_view);
        TextView display_owneremail = (TextView) findViewById(R.id.display_owneremail_view);
        TextView display_guitardetails = (TextView) findViewById(R.id.display_display_guitarDetails_view);

        // Display the values on the screen
        display_model.setText(model);
        display_brand.setText(brand);
        display_price.setText(price);
        display_owner.setText(owner);
        display_owneremail.setText(ownemail);
        display_guitardetails.setText(guitardet);


        // Button initialization for more detailed information & add to wishlist button
        Button button_info = ((Button) findViewById(R.id.button_info));
        Button wish_add = ((Button) findViewById(R.id.addTo_wishlist));

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

        // Button function implemented to search on the web when the user clicks on more details information
        wish_add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Calling method to insert an item to wishlist database
                saveItemList(brand, model, price, owner, ownemail);
            }
        });
    }

    // Function that inserts the item to the database
    private void saveItemList(String guitarBrand, String guitarModel, String guitarPrice, String guitarOwner, String ownerEmail) {
        // Calling Database
        WishListDatabase db = WishListDatabase.getDbInstance(this.getApplicationContext());

        // Creating a new instance of the object
        Guitar guitar = new Guitar();

        // Passing selected item details to the database attributes
        guitar.guitarBrand = guitarBrand;
        guitar.guitarModel = guitarModel;
        guitar.guitarPrice = guitarPrice;
        guitar.guitarOwner = guitarOwner;
        guitar.ownerEmail = ownerEmail;

        // For loop that goes through database to check if a guitar is already in the wishlist
        for (int i = 0; i < db.wishDao().getAllGuitars().size(); i++) {
            // If statement that checks if an item already exists in the database
            if (db.wishDao().getAllGuitars().get(i).guitarModel.equals(guitar.guitarModel)){
                // Message displayed if an item already exists in the wishlist
                Toast.makeText(getApplicationContext(), "This item is already in the WishList", Toast.LENGTH_SHORT).show();
                break;
            }
        }

        // Check boolean variable
        boolean check = false;

        // For loop that checks if the guitar is already in the list
        for (int i = 0; i < db.wishDao().getAllGuitars().size(); i++) {
            // If statement that checks whether it is the list or not
            if (db.wishDao().getAllGuitars().get(i).guitarModel.equals(guitar.guitarModel)) {
                check = true;
                break;
            }
        }

        // If statement that checks if boolean is false, so it can proceed with INSERT command from DAO
        if(!check) {
            // Message displayed when user adds an item to the wishlist
            Toast.makeText(getApplicationContext(), guitar.guitarBrand + " " + guitar.guitarModel + " added to My WishList", Toast.LENGTH_SHORT).show();

            // Calling Database Access Object command for insert
            db.wishDao().insertGuitar(guitar);
            // Returning to previous activity
            finish();
        }
    }
}