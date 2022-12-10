package com.example.takownersmart;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.takownersmart.db.Profile;
import com.example.takownersmart.db.ProfileDatabase;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

// Activity that allows the user to insert and edit their personal details.
public class ProfileEditing extends AppCompatActivity {

    //Private variables and "update profile" button
    private Context context;
    private Bitmap myImage;
    private EditText mUsernameEditText;
    private EditText mEmailEditText;
    private EditText mAddressEditText;
    private EditText mGuitarEditText;
    private ImageView my_avatar;
    private Uri selectedImage;
    private static String picturePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            picturePath = savedInstanceState.getString("safar");
        }
        setContentView(R.layout.activity_profile_editing_page);

        // Calling database from Profile
        ProfileDatabase db  = ProfileDatabase.getDbInstance(this.getApplicationContext());

        // Button, EditText and Image views are being allocated to relevant xml identifiers
        Button insrt_img = findViewById(R.id.insert_img_btn);
        Button btn_send = findViewById(R.id.update_prof_btn);
        my_avatar = findViewById(R.id.avataruser_img);
        mUsernameEditText = findViewById(R.id.usernameinsert);
        mEmailEditText = findViewById(R.id.emailinsert);
        mAddressEditText = findViewById(R.id.addressinsert);
        mGuitarEditText = findViewById(R.id.guitarinsert);

        // Sets autofill for more convenient profile update
        if(!db.profileDao().getprofile().isEmpty()) {
            mUsernameEditText.setText(db.profileDao().getprofile().get(0).personUsername);
            mEmailEditText.setText(db.profileDao().getprofile().get(0).personEmail);
            mAddressEditText.setText(db.profileDao().getprofile().get(0).personAddress);
            mGuitarEditText.setText(db.profileDao().getprofile().get(0).personGuitar);
        }

        // Initializing default user avatar image
        my_avatar.setImageResource(R.drawable.ic_baseline_image_24);

        // Function listener when the user clicks on update profile
        insrt_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Running Select image task
                selectImage();
            }
        });

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

    // Reference: Some parts of the code below was taken from an online example -> https://stackoverflow.com/questions/10165302/dialog-to-pick-image-from-gallery-or-from-camera
    // Function called for the user to select image from Gallery or Take Photo
    private void selectImage() {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileEditing.this);
        builder.setTitle("Add Photo");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);
                }
                else if (options[item].equals("Choose from Gallery")) {
                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    // On activity result which fetches image file directory and decodes it to Bitmap and Displays it on the activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                // Temp file creation
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        File photo = new File(Environment.getExternalStorageDirectory(), "temp.jpg");
                        break;
                    }
                }
                try {
                    // Image decoded to Bitmap
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);

                    my_avatar.setImageBitmap(bitmap);
                    String path = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "Phoenix" + File.separator + "default";
                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 2) {
                // Image fetched from Gallery
                selectedImage = data.getData();
                try {
                    myImage = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                    // Image displayed on screen
                    my_avatar.setImageBitmap(myImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
            }
        }
    }
    // Reference Complete
}