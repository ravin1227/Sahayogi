package com.team.sahayogi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Add_service extends AppCompatActivity {
    String gval;
    TextView textViewupdatetxt;
    EditText editTextexperience;
    ProgressBar progress;
    Spinner spinnercategory;
    Button addbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        gval = getIntent().getExtras().getString("updatetxt");
        textViewupdatetxt= findViewById(R.id.updatetxt);
        textViewupdatetxt.setText(gval);

        addbtn = findViewById(R.id.addbtn);
        progress = findViewById(R.id.progress);
        editTextexperience = findViewById(R.id.experience);
        spinnercategory = findViewById(R.id.category);

        ArrayAdapter<String> myadapter  = new ArrayAdapter<String>(Add_service.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Category));
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnercategory.setAdapter(myadapter);


        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updatetxt, category, experience;
                updatetxt = String.valueOf(textViewupdatetxt.getText()).trim();
                experience = String.valueOf(editTextexperience.getText()).trim();
                category = String.valueOf(spinnercategory.getSelectedItem()).trim();
                //////////////////////////////////////////////////////////
                if(!experience.equals("") && !category.equals("Select Category") ) {
                    progress.setVisibility(view.VISIBLE);
                    Toast.makeText(getApplicationContext(), "Please Wait..", Toast.LENGTH_LONG).show();
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[3];
                            field[0] = "username";
                            field[1] = "category";
                            field[2] = "experience";
                            //Creating array for data
                            String[] data = new String[3];
                            data[0] = updatetxt;
                            data[1] = category;
                            data[2] = experience;
                            PutData putData = new PutData("http://192.168.31.191/SahayogiDB/addservice.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progress.setVisibility(view.GONE);
                                    String result = putData.getResult();
                                    if(result.equals("Service successfully Added"))
                                    {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), Welcome_serviceman.class);
                                        intent.putExtra("updatetxt", updatetxt);
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

    }
}