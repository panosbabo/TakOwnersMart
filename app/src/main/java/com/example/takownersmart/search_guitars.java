package com.example.takownersmart;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class search_guitars extends AppCompatActivity {

    private ListView listView;

    // Private data for the Countries and capital names
    private String guitarBrand = "Takamine";

    private String guitarModels[] = {
            "P3DC",
            "P4DC",
            "P5DC",
            "P7DC",
            "LTD2016",
            "LTD2020"
    };

    private String guitarPrice[] = {
            "1,259€",
            "1,399€",
            "1,499€",
            "2,899€",
            "2,883€",
            "2,799€"
    };

    // Fetching flag images from drawable
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

        TextView textView = new TextView(this);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setText("TakOwners Guitars List");

        ListView listView=(ListView)findViewById(R.id.list);

        listView.addHeaderView(textView);

        // For populating list data
        GuitarList customGuitarList = new GuitarList(this, imageid, guitarBrand, guitarModels, guitarPrice);
        listView.setAdapter(customGuitarList);
    }
}