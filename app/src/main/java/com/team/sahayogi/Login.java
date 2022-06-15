package com.team.sahayogi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {
    private Button cl_btn;
    private Button sl_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cl_btn = (Button) findViewById(R.id.cl_btn);
        sl_btn = (Button) findViewById(R.id.resubmit);

        cl_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openlogincl();
            }
        });
        sl_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opensignupul();
            }
        });

    }

    public void openlogincl() {
        String s = cl_btn.getText().toString();
        Intent intent = new Intent(this, Login_user.class);
        intent.putExtra("updatetxt", s);
        startActivity(intent);
    }
    public void opensignupul() {
        String s = sl_btn.getText().toString();
        Intent intent = new Intent(this, Login_user.class);
        intent.putExtra("updatetxt", s);
        startActivity(intent);
    }
}