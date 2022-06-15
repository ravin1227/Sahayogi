package com.team.sahayogi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button gobtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gobtn = (Button) findViewById(R.id.gobtn);
        gobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openlogin_signup();
            }
        });
    }
    public void openlogin_signup() {
        Intent intent = new Intent(this, Login_Signup.class);
        startActivity(intent);
    }
}