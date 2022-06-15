package com.team.sahayogi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Forgot_Password extends AppCompatActivity {
    EditText editTextuserforgettxt;
    Button reset_btn;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        editTextuserforgettxt = findViewById(R.id.userforgettxt);
        reset_btn = findViewById(R.id.reset_btn);
        progress = findViewById(R.id.progress);

        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userforgettxt;
                userforgettxt = String.valueOf(editTextuserforgettxt.getText()).trim();

                if(!userforgettxt.equals("") ) {
                    progress.setVisibility(view.VISIBLE);
                    Toast.makeText(getApplicationContext(), "Please Wait..", Toast.LENGTH_LONG).show();
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[1];
                            field[0] = "username";
                            String[] data = new String[1];
                            data[0] = userforgettxt;
                            PutData putData = new PutData("http://192.168.31.191/SahayogiDB/forgotpassword.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progress.setVisibility(view.GONE);
                                    String result = putData.getResult();
                                    if(result.equals("User Found"))
                                    {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), Password_Reset.class);
                                        intent.putExtra("updatetxt", userforgettxt);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "User Name can not be empty!", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}