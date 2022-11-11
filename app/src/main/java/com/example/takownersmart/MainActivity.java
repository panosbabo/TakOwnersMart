package com.example.takownersmart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.takownersmart.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Inflating an activity main binding that will allow the use of a bottom nav bar
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.add(R.id.frame_layout, new profile_editing());
//        fragmentTransaction.commit();

        // Setting home pagefragment as the main page after the main activity is initialized
        changeScreen(new HomePage());

        // Listener for the bottom nav bar items
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            // Switch statement that changes fragment whichever item the user has clicked on
            switch (item.getItemId()) {
                case R.id.home:
                    changeScreen(new HomePage());
                    break;
                case R.id.profile:
                    changeScreen(new ProfilePage());
                    break;
                case R.id.search:
                    changeScreen(new SearchPage());
                    break;
            }
            return true;
        });

    }

    // Function that allows to change between fragments.
    private void changeScreen(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
/*
Fish signature
      /`·.¸
     /¸...¸`:·
 ¸.·´  ¸   `·.¸.·´)
: © ):´;ΙΧΘΥΣ  ¸ †{
 `·.¸ `·  ¸.·´\`·¸)
     `\\´´\¸.·´
*/
}