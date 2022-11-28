package com.example.takownersmart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.takownersmart.db.ProfileDatabase;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfilePage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfilePage(){
        // require a empty public constructor
    }

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

        TextView mUsernameText;
        TextView mEmailText;
        TextView mAddressText;
        TextView mGuitarText;
        TextView mavatarusernameText;
        TextView mavataremailText;
        Button editprofbtn;

        String usrnm;
        String eml;
        String adrs;
        String gtr;

        // Inflate the layout for this fragment
        View profView = inflater.inflate(R.layout.fragment_profile_page, container, false);
        // User image is initialized
        ImageView userView = (ImageView) profView.findViewById(R.id.userimage);
        userView.setImageResource(R.drawable.small_icon);

        // Calling database from Profile database
        ProfileDatabase db  = ProfileDatabase.getDbInstance(this.getContext());

        // Initializing Views to related Resource id's
        mUsernameText = profView.findViewById(R.id.usernameinsert);
        mEmailText = profView.findViewById(R.id.emailinsert);
        mAddressText = profView.findViewById(R.id.addressinsert);
        mGuitarText = profView.findViewById(R.id.guitarinsert);
        mavatarusernameText = profView.findViewById(R.id.username);
        mavataremailText = profView.findViewById(R.id.emailuser);
        editprofbtn = profView.findViewById(R.id.editprofile_btn);

        // If-Else statement that checks if user hasn't entered details yet.
        // Display Info as Empty
        if(db.profileDao().getprofile().isEmpty()) {
            usrnm = "Empty";
            eml = "Empty";
            adrs = "Empty";
            gtr = "Empty";
        }
        else {
            // Initialization from Profile database to String variables
            usrnm = db.profileDao().getprofile().get(0).personUsername;
            eml = db.profileDao().getprofile().get(0).personEmail;
            adrs = db.profileDao().getprofile().get(0).personAddress;
            gtr = db.profileDao().getprofile().get(0).personGuitar;
        }

        // Displaying Info on the Profile Page
        mUsernameText.setText(usrnm);
        mEmailText.setText(eml);
        mAddressText.setText(adrs);
        mGuitarText.setText(gtr);
        mavatarusernameText.setText(usrnm);
        mavataremailText.setText(eml);

        // Button function listener for the Profile Editing page activity
        editprofbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Creating an Intent for the next activity of the profile editing page
                Intent intent = new Intent(ProfilePage.super.getContext(), ProfileEditing.class);
                startActivity(intent);
//                activityLauncher.launch(intent);
//                FragmentTransaction fr = getFragmentManager().beginTransaction();
//                fr.replace(R.id.frame_layout, new profile_editing());
//                fr.commit();
            }
        });

        return profView;
    }

}