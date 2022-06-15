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

public class Update_profile extends AppCompatActivity {
    String gval, utype;
    TextView textViewupdatetxt, textViewusertype;
    EditText editTextaddtxt, editTextcttxt, editTextsttxt, editTextpntxt;
    Button btnupdate;
    ProgressBar progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        gval = getIntent().getExtras().getString("updatetxt");
        textViewupdatetxt= findViewById(R.id.updatetxt);
        textViewupdatetxt.setText(gval);

        utype = getIntent().getExtras().getString("usertype");
        textViewusertype= findViewById(R.id.usertype);
        textViewusertype.setText(utype);

        editTextaddtxt = findViewById(R.id.addtxt);
        editTextcttxt = findViewById(R.id.cttxt);
        editTextsttxt = findViewById(R.id.sttxt);
        editTextpntxt = findViewById(R.id.pntxt);
        btnupdate = findViewById(R.id.btnupdate);
        progress = findViewById(R.id.progress);


        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updatetxt, addtxt, cttxt, sttxt, pntxt, usertype;
                updatetxt = String.valueOf(textViewupdatetxt.getText()).trim();
                addtxt = String.valueOf(editTextaddtxt.getText()).trim();
                cttxt = String.valueOf(editTextcttxt.getText()).trim();
                sttxt = String.valueOf(editTextsttxt.getText()).trim();
                pntxt = String.valueOf(editTextpntxt.getText()).trim();
                usertype = String.valueOf(textViewusertype.getText()).trim();

                //////////////////////////////////////////////////////////
                if(!addtxt.equals("") && !cttxt.equals("") && !sttxt.equals("") && !pntxt.equals("")) {
                    progress.setVisibility(view.VISIBLE);
                    Toast.makeText(getApplicationContext(), "Please Wait..", Toast.LENGTH_LONG).show();
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[5];
                            field[0] = "username";
                            field[1] = "address";
                            field[2] = "city";
                            field[3] = "state";
                            field[4] = "pincode";
                            //Creating array for data
                            String[] data = new String[5];
                            data[0] = updatetxt;
                            data[1] = addtxt;
                            data[2] = cttxt;
                            data[3] = sttxt;
                            data[4] = pntxt;
                            PutData putData = new PutData("http://192.168.31.191/SahayogiDB/updateprofile.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progress.setVisibility(view.GONE);
                                    String result = putData.getResult();
                                    if(result.equals("Details successfully Updated!") && usertype.equals("Customer"))
                                    {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), Welcome_user.class);
                                        intent.putExtra("updatetxt", updatetxt);
                                        intent.putExtra("usertype", usertype);
                                        startActivity(intent);
                                        finish();
                                    }
                                    if(result.equals("Details successfully Updated!") && usertype.equals("Serviceman"))
                                    {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), Welcome_serviceman.class);
                                        intent.putExtra("updatetxt", updatetxt);
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
                    Toast.makeText(getApplicationContext(), "All Fields are Required! Or Password not matched! ", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}