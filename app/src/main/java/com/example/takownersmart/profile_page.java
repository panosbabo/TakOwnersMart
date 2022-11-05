package com.example.takownersmart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class profile_page extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView usernametxt;

    public profile_page(){
        // require a empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public profile_page newInstance(String param1, String param2) {
        profile_page fragment = new profile_page();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

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
//                Intent intent = new Intent(profile_page.super.getContext(), profile_editing_page.class);
//                activityLauncher.launch(intent);
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_layout, new profile_editing());
                fr.commit();
            }
        });

        usernametxt = profView.findViewById(R.id.usernameinsert);
        Bundle bundle = this.getArguments();
        if (bundle == null) {
            usernametxt = profView.findViewById(R.id.usernametext);
        }
        else {
            String data = bundle.getString("key");
            usernametxt.setText(data);
        }

        return profView;
    }
}