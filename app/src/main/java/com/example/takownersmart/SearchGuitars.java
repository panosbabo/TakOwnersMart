package com.example.takownersmart;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

// Activity for the search page when the user wants to browse guitar owners
public class SearchGuitars extends AppCompatActivity {

    // Private variables to be used to fetch and display the context in the activity
    private ListView listView;
    private Activity context;
    private List<GuitarObject> guitarExampleList = new ArrayList<>();
    private String[] guitarModels;
    private String[] guitarPrice;
    private String[] guitarOwner;
    private String[] ownerEmail;

    // Private string for the intended amazing Japanese Acoustic Guitar Brand named "Takamine"
    private String guitarBrand = "Takamine";

    // Fetching guitar images from drawable
    private Integer imageid[] = {
            R.drawable.p3dc,
            R.drawable.p4dc,
            R.drawable.p5dc,
            R.drawable.p7dc,
            R.drawable.ltd2016,
            R.drawable.ltd2020,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_guitars);

        // Fetching data of allegedly guitar owners from a csv file.
        readDisplayInfo();

        guitarModels = new String[guitarExampleList.size()];
        guitarPrice = new String[guitarExampleList.size()];
        guitarOwner = new String[guitarExampleList.size()];
        ownerEmail = new String[guitarExampleList.size()];

        // Data is being transferred to newly created GuitarList instance.
        transferData();

        // Creating a descent textview for the search page title
        TextView textView = new TextView(this);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setText("TakOwners Guitars List");

        listView = (ListView)findViewById(R.id.list);

        listView.addHeaderView(textView);

        // For populating list data
        GuitarList customGuitarList = new GuitarList(this, imageid, guitarBrand, guitarModels, guitarPrice, guitarOwner, ownerEmail);
        listView.setAdapter(customGuitarList);
    }

    // Function that reads and fetches all relevant data of guitar owners from a csv file
    private void readDisplayInfo() {
        InputStream is = getResources().openRawResource(R.raw.guitar_list);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );
        String line = "";
        try {
            while((line = reader.readLine()) != null) {

                String[] tokens = line.split(",");

                GuitarObject guitar_list = new GuitarObject(
                        tokens[0],
                        tokens[1],
                        tokens[2],
                        tokens[3]
                );
                guitarExampleList.add(guitar_list);
            }
        }
        catch (IOException e) {
            Log.wtf("My activity", "Error reading line " + line, e);
            e.printStackTrace();
        }
    }

    // Function that transfers all data fetched from the csv file to create a new object from GuitarList class.
    private void transferData() {
        for(int i = 0; i < guitarExampleList.size(); i++){
            guitarModels[i] = guitarExampleList.get(i).getGuitarModel();
            guitarPrice[i] = guitarExampleList.get(i).getGuitarPrice();
            guitarOwner[i] = guitarExampleList.get(i).getGuitarOwner();
            ownerEmail[i] = guitarExampleList.get(i).getOwnerEmail();
        }
    }
}