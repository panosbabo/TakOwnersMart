package com.example.takownersmart;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.takownersmart.db.ProfileDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchPage extends Fragment {

    public SearchPage() {
        // Required empty public constructor
    }

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

        ProfileDatabase db = ProfileDatabase.getDbInstance(this.getContext());



        if(db.profileDao().getprofile().isEmpty()) {
            Toast.makeText(getContext(), "Please Enter Profile details first.", Toast.LENGTH_SHORT).show();
        }
        else {
            // Creating a new Intent for the next activity
            Intent intent = new Intent(SearchPage.super.getContext(), SearchGuitars.class);
            startActivity(intent);
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

        // Initializing Views to the related R.id's
        non_registered = (TextView) view.findViewById(R.id.non_registered_view);
        registered = (TextView) view.findViewById(R.id.registered_view);

        /* If-Else statement to check whether the user has entered profile details
        or not and display the related content in each case. */
        if (db.profileDao().getprofile().isEmpty()) {
            registered.setVisibility(View.GONE);
            non_registered.setVisibility(View.VISIBLE);
        }
        else {
            // Creating a descent textview for the wish list page title
            registered.setVisibility(View.VISIBLE);
            non_registered.setVisibility(View.GONE);
        }

        return view;
    }
}