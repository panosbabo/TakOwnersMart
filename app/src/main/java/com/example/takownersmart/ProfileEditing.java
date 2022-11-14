package com.example.takownersmart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

//import android.arch.persistence.room.Room;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// Activity that allows the user to insert and edit their personal details.
public class ProfileEditing extends AppCompatActivity {

    //Private variables for the "update profile" button
    private Button btn_send;
    private ProfileDao mProfile;

    private EditText mUsernameEditText;
    private EditText mEmailEditText;
    private EditText mAddressEditText;
    private EditText mGuitarEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_editing_page);

        // Button and EditText views are being allocated to relevant xml identifiers
        btn_send = findViewById(R.id.update_prof_btn);

        mProfile = Room.databaseBuilder(this, ProfileDatabase.class, "db-contacts")
                .allowMainThreadQueries()   //Allows room to do operation on main thread
                .build()
                .getsProfile();

        mUsernameEditText = findViewById(R.id.usernameinsert);
        mEmailEditText = findViewById(R.id.emailinsert);
        mAddressEditText = findViewById(R.id.addressinsert);
        mGuitarEditText = findViewById(R.id.guitarinsert);

        // Function listener when the user clicks on update profile
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernamE = mUsernameEditText.getText().toString();
                String emaiL = mEmailEditText.getText().toString().trim();
                String addresS = mAddressEditText.getText().toString();
                String guitaR = mGuitarEditText.getText().toString();

                if (usernamE.isEmpty() || emaiL.isEmpty() || addresS.isEmpty() || guitaR.isEmpty()) {
                    Toast.makeText(ProfileEditing.this, "Please make sure all details are correct.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (!emaiL.matches(emailPattern)) {
                    Toast.makeText(ProfileEditing.this, "Please enter a valid email address.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Profile profile = new Profile();
                profile.setPersonUsername(usernamE);
                profile.setPersonEmail(emaiL);
                profile.setPersonAddress(addresS);
                profile.setPersonGuitar(guitaR);


                Bundle b = new Bundle();

                // Parsing data to the next activity with the use of Bundle
                b.putString("username", usernamE);
                b.putString("email", emaiL);
                b.putString("address", addresS);
                b.putString("guitar", guitaR);

                ProfilePage fragobj = new ProfilePage();
                fragobj.setArguments(b);

                //Insert to database
                try {
                    mProfile.insert(profile);
                    setResult(RESULT_OK);
                    Log.d("Profile ! Username", profile.getPersonUsername());
                    Log.d("Profile ! Email", profile.getPersonEmail());
                    Log.d("Profile ! Address", profile.getPersonAddress());
                    Log.d("Profile ! Guitar", profile.getPersonGuitar());
                    finish();
                } catch (SQLiteConstraintException e) {
                    Toast.makeText(ProfileEditing.this, "Profile has been created", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}