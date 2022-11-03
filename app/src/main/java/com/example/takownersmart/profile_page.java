package com.example.takownersmart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class profile_page extends Fragment {

    ImageView userImage;



    public profile_page(){
        // require a empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView =inflater.inflate(R.layout.fragment_profile_page, container, false);
        ImageView userView = (ImageView) rootView.findViewById(R.id.userimage);
        // Inflate the layout for this fragment
        userView.setImageResource(R.drawable.small_icon);


        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK) {
                int result = data.getIntExtra("result", 0);
                //result is the code of the picked image
                //code to change profile picture goes here
            }
        }

    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        View rootView =inflater.inflate(R.layout.fragment_profile_page, container, false);
//        ImageView userView = (ImageView) rootView.findViewById(R.id.userimage);
//        // Inflate the layout for this fragment
//        userView.setImageResource(R.drawable.ic_baseline_home_24);
//
//        return rootView;
//    }
}