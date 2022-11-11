package com.example.takownersmart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

// Activity that allows the user to insert and edit their personal details.
public class ProfileEditing extends AppCompatActivity {

    //Private variables for the "update profile" button
    private EditText et_message;
    private Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_editing_page);

        // Button and EditText views are being allocated to relevant xml identifiers
        et_message = findViewById(R.id.usernameinsert);
        btn_send = findViewById(R.id.update_prof_btn);

        // Function listener when the user clicks on update profile
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // New intent is created
                Intent intent = new Intent();
                // Fetching user's edit text
                String text = et_message.getText().toString();
                if (text != "") {
                    intent.putExtra("result", et_message.getText().toString());
                    setResult(78, intent);
                }
                else{
                    setResult(77, intent);
                }
                finish();
            }
        });
    }
}