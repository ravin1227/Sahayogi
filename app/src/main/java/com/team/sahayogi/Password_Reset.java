package com.team.sahayogi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Password_Reset extends AppCompatActivity {
    String gval;
    TextView textViewupdatetxt;
    EditText editTextpassre, editTextrepassre;
    Button resubmit;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        gval = getIntent().getExtras().getString("updatetxt");
        textViewupdatetxt= findViewById(R.id.updatetxt);
        textViewupdatetxt.setText(gval);

        editTextpassre = findViewById(R.id.passre);
        editTextrepassre = findViewById(R.id.repassre);
        resubmit = findViewById(R.id.resubmit);
        progressBar = findViewById(R.id.progress);

        resubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String passre, repassre, updatetxt;
                passre = String.valueOf(editTextpassre.getText()).trim();
                repassre = String.valueOf(editTextrepassre.getText()).trim();
                updatetxt = String.valueOf(textViewupdatetxt.getText()).trim();

                if(!passre.equals("") && !repassre.equals("")  ) {
                    progressBar.setVisibility(view.VISIBLE);
                    Toast.makeText(getApplicationContext(), "Please Wait..", Toast.LENGTH_LONG).show();
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[2];
                            field[0] = "username";
                            field[1] = "password";
                            String[] data = new String[2];
                            data[0] = updatetxt;
                            data[1] = passre;
                            PutData putData = new PutData("http://192.168.31.191/SahayogiDB/passwordupdate.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(view.GONE);
                                    String result = putData.getResult();
                                    if(result.equals("Password successfully changed"))
                                    {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), Login.class);
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
                    Toast.makeText(getApplicationContext(), "All fields are required!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}