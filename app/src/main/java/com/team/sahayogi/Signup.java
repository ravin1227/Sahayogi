package com.team.sahayogi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Signup extends AppCompatActivity {
    private Button cs_btn;
    private Button ss_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        cs_btn = (Button) findViewById(R.id.cs_btn);
        ss_btn = (Button) findViewById(R.id.ss_btn);

        cs_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opensignupcl();
            }
        });
        ss_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opensignupsl();
            }
        });
    }
    public void opensignupcl() {
        String s = cs_btn.getText().toString();
        Intent intent = new Intent(this, Signup_user.class);
        intent.putExtra("supdttxt", s);
        startActivity(intent);
    }
    public void opensignupsl() {
        String s = ss_btn.getText().toString();
        Intent intent = new Intent(this, Signup_user.class);
        intent.putExtra("supdttxt", s);
        startActivity(intent);
    }
}