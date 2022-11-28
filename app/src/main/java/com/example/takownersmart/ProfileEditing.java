package com.example.takownersmart;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.takownersmart.db.Profile;
import com.example.takownersmart.db.ProfileDatabase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Activity that allows the user to insert and edit their personal details.
public class ProfileEditing extends AppCompatActivity {

    //Private variables and "update profile" button
    private Button btn_send;
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

        // Calling database from Profile
        ProfileDatabase db  = ProfileDatabase.getDbInstance(this.getApplicationContext());

        mUsernameEditText = findViewById(R.id.usernameinsert);
        mEmailEditText = findViewById(R.id.emailinsert);
        mAddressEditText = findViewById(R.id.addressinsert);
        mGuitarEditText = findViewById(R.id.guitarinsert);

        // Executor service to allow image fetching to an alternative thread
        ExecutorService service = Executors.newSingleThreadExecutor();
        // Creates a handle to recover the result from the
        Handler handler = new Handler(Looper.getMainLooper());

        // Function listener when the user clicks on update profile
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Variable creation to allow data parsed from user's inserts
                String usernamE = mUsernameEditText.getText().toString();
                String emaiL = mEmailEditText.getText().toString().trim();
                String addresS = mAddressEditText.getText().toString();
                String guitaR = mGuitarEditText.getText().toString();

                // Constraint to force the user enter all required details
                if (usernamE.isEmpty() || emaiL.isEmpty() || addresS.isEmpty() || guitaR.isEmpty()) {
                    Toast.makeText(ProfileEditing.this, "Please make sure all details are correct.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Constraint to enter a valid email address
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (!emaiL.matches(emailPattern)) {
                    Toast.makeText(ProfileEditing.this, "Please enter a valid email address.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Instantiation of a Profile object
                Profile profile = new Profile();

                // If statement that checks if user has already enter profile details
                if (db.profileDao().getprofile().isEmpty()) {
                    try {
                        // Assigning user's insert to Profile object
                        profile.personUsername = usernamE;
                        profile.personEmail = emaiL;
                        profile.personAddress = addresS;
                        profile.personGuitar = guitaR;

                        // Calling Dao Commands for Profile INSERT
                        db.profileDao().insertProfile(profile);

                        // Setting Result OK
                        setResult(RESULT_OK);

                        // Message displayed for successful profile creation
                        Toast.makeText(ProfileEditing.this, "Profile has been created", Toast.LENGTH_SHORT).show();
                        // TODO: Create alternative thread for the image fetch
//                        service.execute(new Runnable() {
//                            @Override
//                            public void run() {
//                                handler.post(new Runnable() {
//                                    @Override
//                                    public void run() {
//
//                                    }
//                                });
//                            }
//                        });
                        // Returning to previous activity as a refresh
                        finish();
                        Intent intent = new Intent(ProfileEditing.this, MainActivity.class);
                        startActivity(intent);
                    } catch (SQLiteConstraintException e) {
                        // Message displayed for catching error of profile creation
                        Toast.makeText(ProfileEditing.this, "Profile has not been created", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    try {
                        /* Indexing profile to the first instance of the Profile Objects
                         This way, the user updates the first instance every time they
                         change their profile details. */
                        profile = db.profileDao().getprofile().get(0);

                        // Assigning user's insert to Profile object
                        profile.personUsername = usernamE;
                        profile.personEmail = emaiL;
                        profile.personAddress = addresS;
                        profile.personGuitar = guitaR;

                        // Calling Dao Commands for Profile UPDATE
                        db.profileDao().updateProfile(profile);

                        // Setting Result OK
                        setResult(RESULT_OK);

                        // Message displayed for successful profile update
                        Toast.makeText(ProfileEditing.this, "Profile has been updated", Toast.LENGTH_SHORT).show();
                        // TODO: Create alternative thread for the image fetch
//                        service.execute(new Runnable() {
//                            @Override
//                            public void run() {
//                                handler.post(new Runnable() {
//                                    @Override
//                                    public void run() {
//
//                                    }
//                                });
//                            }
//                        });
                        // Returning to previous activity as a refresh
                        finish();
                        Intent intent = new Intent(ProfileEditing.this, MainActivity.class);
                        startActivity(intent);
                    } catch (SQLiteConstraintException e) {
                        // Message displayed for catching error of profile update
                        Toast.makeText(ProfileEditing.this, "Profile has not been updated", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}