package com.example.takownersmart;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.takownersmart.db.ProfileDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchPage extends Fragment {

    public SearchPage() {
        // Required empty public constructor
    }

    // Private variables to be used to fetch and display the context in the activity
    private final List<GuitarObject> guitarExampleList = new ArrayList<>();
    private String[] guitarModels;
    private String[] guitarPrice;
    private String[] guitarOwner;
    private String[] ownerEmail;
    private String[] guitarDetails;

    // Private string for the intended amazing Japanese Acoustic Guitar Brand named "Takamine"
    private static final String guitarBrand = "Takamine";

    // Fetching guitar images from drawable
    private Integer imageid[] = {
            R.drawable.p3dc,
            R.drawable.p4dc,
            R.drawable.p5dc,
            R.drawable.p7dc,
            R.drawable.ltd2016,
            R.drawable.ltd2020,
    };

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment settings_page.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchPage newInstance(String param1, String param2) {
        SearchPage fragment = new SearchPage();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Calling Profile Database
        ProfileDatabase db = ProfileDatabase.getDbInstance(this.getContext());

        /* If-Else statement to check whether the user has entered profile details
        or not and display a message suggesting the user to enter details. */
        if(db.profileDao().getprofile().isEmpty()) {
            Toast.makeText(getContext(), "Please Enter Profile details first.", Toast.LENGTH_SHORT).show();
        }
        else {
            // Creating a new Intent for the next activity
//            Intent intent = new Intent(SearchPage.super.getContext(), SearchGuitars.class);
//            startActivity(intent);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_page, container, false);

        // Calling Profile Database
        ProfileDatabase db = ProfileDatabase.getDbInstance(this.getContext());

        // Initializing TextViews for the relative message
        TextView non_registered;
        TextView registered;

        // Initializing a list view for the guitar list
        ListView listView;

        // Fetching data of allegedly guitar owners from a csv file.
        readDisplayInfo();

        // Initializing GuitarList attributes to the amount of owners in the csv file.
        guitarModels = new String[guitarExampleList.size()];
        guitarPrice = new String[guitarExampleList.size()];
        guitarOwner = new String[guitarExampleList.size()];
        ownerEmail = new String[guitarExampleList.size()];
        guitarDetails = new String[guitarExampleList.size()];

        // Data is being transferred to newly created GuitarList instance.
        transferData();

        // Reference: Done a Similar example in Lab 4

        // Creating a descent textview for the search page title
//        TextView textView = new TextView(getContext());
//        textView.setTypeface(Typeface.DEFAULT_BOLD);
//        textView.setText("TakOwners Guitars List");

        listView = (ListView) view.findViewById(R.id.list);

//        listView.addHeaderView(textView);

        // For populating list data
        GuitarList customGuitarList = new GuitarList(getActivity(), imageid, guitarBrand, guitarModels, guitarPrice, guitarOwner, ownerEmail, guitarDetails);
        // Reference Complete

        // Initializing Views to the related R.id's
        non_registered = (TextView) view.findViewById(R.id.non_registered_view);

        /* If-Else statement to check whether the user has entered profile details
        or not and display the related content in each case. */
        if (db.profileDao().getprofile().isEmpty()) {
            non_registered.setVisibility(View.VISIBLE);

        }
        else {
            // Creating a descent textview for the wish list page title
            non_registered.setVisibility(View.GONE);
            listView.setAdapter(customGuitarList);
        }

        return view;
    }

    // Function that reads and fetches all relevant data of guitar owners from a csv file
    private void readDisplayInfo() {
        InputStream is = getResources().openRawResource(R.raw.guitar_list);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, StandardCharsets.UTF_8)
        );
        String line = "";
        try {
            while((line = reader.readLine()) != null) {

                String[] tokens = line.split(",");

                GuitarObject guitar_list = new GuitarObject(
                        tokens[0],
                        tokens[1],
                        tokens[2],
                        tokens[3],
                        tokens[4]
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
            guitarDetails[i] = guitarExampleList.get(i).getGuitarDetails();
        }
    }
}