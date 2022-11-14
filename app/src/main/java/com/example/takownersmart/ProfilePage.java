package com.example.takownersmart;




import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.takownersmart.Profile;

import java.util.List;





import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.room.Ignore;
import androidx.room.Room;

public class ProfilePage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static final String TAG = "MainActivity";
    private TextView txt_result;
    private Button btn_get_result;

    private TextView mUsernameText;
    private TextView mEmailText;
    private TextView mAddressText;
    private TextView mGuitarText;
    private TextView mavatarusernameText;
    private TextView mavataremailText;

    private String usrnm;
    private String eml;
    private String adrs;
    private String gtr;

//    private String zusrnm;
//    private String zeml;
//    private String zadrs;
//    private String zgtr;


    private Profile profileDets;
    private ProfileDao profdao;

    public ProfilePage(){
        // require a empty public constructor
    }

//    public ProfilePage(String usrnm, String eml, String adrs, String gtr) {
//        this.usrnm = usrnm;
//        this.eml = eml;
//        this.adrs = adrs;
//        this.gtr = gtr;
//    }

    // TODO: Rename and change types and number of parameters
    public ProfilePage newInstance(String param1, String param2) {
        ProfilePage fragment = new ProfilePage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);

        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View profView = inflater.inflate(R.layout.fragment_profile_page, container, false);
        // User image is initialized
        ImageView userView = (ImageView) profView.findViewById(R.id.userimage);
        userView.setImageResource(R.drawable.small_icon);

//        Log.d("Profile ! Username", getArguments().getString("username"));
//        Log.d("Profile ! Email", getArguments().getString("email"));
//        Log.d("Profile ! Address", getArguments().getString("address"));
//        Log.d("Profile ! Guitar", getArguments().getString("guitar"));

//        if((getArguments().getString("username") == null) || (getArguments().getString("email") == null) ||
//                (getArguments().getString("address") == null) || (getArguments().getString("guitar") == null)) {
//            usrnm = "Empty";
//            eml = "Empty";
//            adrs = "Empty";
//            gtr = "Empty";
//        }
//        else {
//            usrnm = getArguments().getString("username");
//            eml = getArguments().getString("email");
//            adrs = getArguments().getString("address");
//            gtr = getArguments().getString("guitar");
//        }

//        usrnm = getArguments().getString("username");
//        eml = getArguments().getString("email");
//        adrs = getArguments().getString("address");
//        gtr = getArguments().getString("guitar");

//        zusrnm = getArguments().getString("username");
//        zeml = getArguments().getString("email");
//        zadrs = getArguments().getString("address");
//        zgtr = getArguments().getString("guitar");

//        ProfilePage newprof = new ProfilePage(zusrnm, zeml, zadrs, zgtr);

        mUsernameText = profView.findViewById(R.id.usernameinsert);
        mEmailText = profView.findViewById(R.id.emailinsert);
        mAddressText = profView.findViewById(R.id.addressinsert);
        mGuitarText = profView.findViewById(R.id.guitarinsert);
        mavatarusernameText = profView.findViewById(R.id.username);
        mavataremailText = profView.findViewById(R.id.emailuser);

        if(usrnm == null) {
            usrnm = "Empty";
            eml = "Empty";
            adrs = "Empty";
            gtr = "Empty";

//            mUsernameText.setText("Empty");
//            mEmailText.setText("Empty");
//            mAddressText.setText("Empty");
//            mGuitarText.setText("Empty");
//            mavatarusernameText.setText("Empty");
//            mavataremailText.setText("Empty");
        }
        else {
//            usrnm = getArguments().getString("username");
//            eml = getArguments().getString("email");
//            adrs = getArguments().getString("address");
//            gtr = getArguments().getString("guitar");
        }
        mUsernameText.setText(usrnm);
        mEmailText.setText(eml);
        mAddressText.setText(adrs);
        mGuitarText.setText(gtr);
        mavatarusernameText.setText(usrnm);
        mavataremailText.setText(eml);


        Button btn = (Button)profView.findViewById(R.id.editprofile_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfilePage.super.getContext(), ProfileEditing.class);
                startActivity(intent);
//                activityLauncher.launch(intent);
//                FragmentTransaction fr = getFragmentManager().beginTransaction();
//                fr.replace(R.id.frame_layout, new profile_editing());
//                fr.commit();
            }
        });

        txt_result = profView.findViewById(R.id.usernameinsert);

        return profView;
    }

}