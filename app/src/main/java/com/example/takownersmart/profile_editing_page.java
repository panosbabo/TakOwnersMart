package com.example.takownersmart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class profile_editing_page extends AppCompatActivity {

    private EditText et_message;
    private Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_editing_page);

        et_message = (EditText) findViewById(R.id.usernameinsert);
        btn_send = (Button) findViewById(R.id.update_prof_btn);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
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