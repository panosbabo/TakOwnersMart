package com.example.takownersmart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class profile_page extends Fragment {

    public profile_page(){
        // require a empty public constructor
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View profView = inflater.inflate(R.layout.fragment_profile_page, container, false);
        ImageView userView = (ImageView) profView.findViewById(R.id.userimage);

        // Inflate the layout for this fragment
        userView.setImageResource(R.drawable.small_icon);

        Button btn = (Button)profView.findViewById(R.id.editprofile_btn);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_layout, new ProfileEditing());
                fr.commit();
            }
        });

//        View profitView = inflater.inflate(R.layout.activity_profile_editing, container, false);



        return profView;
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
}