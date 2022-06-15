package com.team.sahayogi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Welcome_serviceman extends AppCompatActivity {
    String gval, utype;
    Button add, updatepr, changepwd, logout;
    TextView textViewupdatetxt, textViewusertype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_serviceman);

        add = findViewById(R.id.add);
        updatepr = findViewById(R.id.updatepr);
        changepwd = findViewById(R.id.changepwd);
        logout = findViewById(R.id.logout);

        gval = getIntent().getExtras().getString("updatetxt");
        textViewupdatetxt= findViewById(R.id.updatetxt);
        textViewupdatetxt.setText(gval);

        utype = getIntent().getExtras().getString("usertype");
        textViewusertype= findViewById(R.id.usertype);
        textViewusertype.setText(utype);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openaddService();
            }
        });
        updatepr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openupdateprofile();
            }
        });
        changepwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openchangepassword();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

    }
    public void openaddService() {
        String s = String.valueOf(textViewupdatetxt.getText()).trim();
        Intent intent = new Intent(this, Add_service.class);
        intent.putExtra("updatetxt", s);
        startActivity(intent);
        finish();
    }
    public void openupdateprofile() {
        String s = String.valueOf(textViewupdatetxt.getText()).trim();
        String u = String.valueOf(textViewusertype.getText()).trim();
        Intent intent = new Intent(this, Update_profile.class);
        intent.putExtra("updatetxt", s);
        intent.putExtra("usertype", u);
        startActivity(intent);
        finish();
    }
    public void openchangepassword() {
        String s = String.valueOf(textViewupdatetxt.getText()).trim();
        Intent intent = new Intent(this, Password_Reset.class);
        intent.putExtra("updatetxt", s);
        startActivity(intent);
        finish();
    }
    public void logout() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}