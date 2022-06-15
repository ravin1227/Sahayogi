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

public class Login_user extends AppCompatActivity {
    String gval;
    EditText editTextusertxt, editTextpwdtxt;
    Button login_btn, sl_btn, fg_btn;
    ProgressBar progress;
    TextView textViewupdatetxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        editTextusertxt = findViewById(R.id.usertxt);
        editTextpwdtxt = findViewById(R.id.pwdtxt);
        login_btn = findViewById(R.id.login_btn);
        progress = findViewById(R.id.progress);

        gval = getIntent().getExtras().getString("updatetxt");
        textViewupdatetxt= findViewById(R.id.updatetxt);
        textViewupdatetxt.setText(gval);

        sl_btn = findViewById(R.id.resubmit);
        fg_btn = findViewById(R.id.fgt_btn);
        ////////////////////////////////////////////////////////
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usertxt, pwdtxt, usertype;
                usertxt = String.valueOf(editTextusertxt.getText()).trim();
                pwdtxt = String.valueOf(editTextpwdtxt.getText()).trim();
                usertype = String.valueOf(textViewupdatetxt.getText());

                if(!usertxt.equals("") && !pwdtxt.equals("") && !usertype.equals("") ) {
                    progress.setVisibility(view.VISIBLE);
                    Toast.makeText(getApplicationContext(), "Please Wait..", Toast.LENGTH_LONG).show();
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[2];
                            field[0] = "username";
                            field[1] = "password";
                            String[] data = new String[2];
                            data[0] = usertxt;
                            data[1] = pwdtxt;
                            PutData putData = new PutData("http://192.168.31.191/SahayogiDB/login.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progress.setVisibility(view.GONE);
                                    String result = putData.getResult();
                                    if(result.equals("Login Success") && usertype.equals("Customer"))
                                    {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), Welcome_user.class);
                                        intent.putExtra("updatetxt", usertxt);
                                        intent.putExtra("usertype", usertype);
                                        startActivity(intent);
                                        finish();
                                    }
                                    if(result.equals("Login Success") && usertype.equals("Serviceman"))
                                    {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), Welcome_serviceman.class);
                                        intent.putExtra("updatetxt", usertxt);
                                        intent.putExtra("usertype", usertype);
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
                    Toast.makeText(getApplicationContext(), "All Fields are Required!", Toast.LENGTH_LONG).show();
                }
            }
        });
/////////////////////////////////////////////////////
        sl_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opensignup();
            }
        });
        fg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openForgetpassword();
            }
        });
    }
    public void opensignup() {
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
        finish();
    }
    public void openForgetpassword() {
        Intent intent = new Intent(this, Forgot_Password.class);
        startActivity(intent);
        finish();
    }
}