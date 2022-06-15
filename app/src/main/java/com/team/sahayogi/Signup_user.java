package com.team.sahayogi;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Signup_user extends AppCompatActivity {
    String sgval;
    EditText editTextnametxt, editTextphntxt, editTextemailtxt, editTextusernametxt, editTextpwdtxt, editTextcnfpwdtxt, editTextdobtxt, editTextaddtxt, editTextcttxt, editTextsttxt, editTextpntxt;
    Button btnsigup, login_btn;
    ProgressBar progressBar;
    Spinner spinnergender;
    Calendar myCalendar;
    TextView textViewsupdttxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_user);


        editTextnametxt = findViewById(R.id.nametxt);
        editTextphntxt = findViewById(R.id.phntxt);
        editTextemailtxt = findViewById(R.id.emailtxt);
        editTextusernametxt = findViewById(R.id.usernametxt);
        editTextpwdtxt = findViewById(R.id.pwdtxt);
        editTextcnfpwdtxt = findViewById(R.id.cnfpwdtxt);
        editTextdobtxt = findViewById(R.id.dobtxt);
        editTextaddtxt = findViewById(R.id.addtxt);
        editTextcttxt = findViewById(R.id.cttxt);
        editTextsttxt = findViewById(R.id.sttxt);
        editTextpntxt = findViewById(R.id.pntxt);
        btnsigup = findViewById(R.id.btnsigup);
        login_btn = findViewById(R.id.login_btn);
        progressBar = findViewById(R.id.progress);
        spinnergender = findViewById(R.id.gender);
        myCalendar = Calendar.getInstance();

        ArrayAdapter<String> myadapter  = new ArrayAdapter<String>(Signup_user.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Gender));
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnergender.setAdapter(myadapter);

        sgval = getIntent().getExtras().getString("supdttxt");
        textViewsupdttxt = findViewById(R.id.supdttxt);
        textViewsupdttxt.setText(sgval);

        //////////////

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDate();
            }
        };
        editTextdobtxt.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Signup_user.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        }));
        /////////////

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openlogin();
            }
        });

        btnsigup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nametxt, phntxt, emailtxt, usernametxt, pwdtxt, cnfpwdtxt, dobtxt, addtxt, cttxt, sttxt, pntxt, gender, supdttxt;
                nametxt = String.valueOf(editTextnametxt.getText()).trim();
                phntxt = String.valueOf(editTextphntxt.getText()).trim();
                emailtxt = String.valueOf(editTextemailtxt.getText()).trim();
                usernametxt = String.valueOf(editTextusernametxt.getText()).trim();
                pwdtxt = String.valueOf(editTextpwdtxt.getText()).trim();
                cnfpwdtxt = String.valueOf(editTextcnfpwdtxt.getText()).trim();
                addtxt = String.valueOf(editTextaddtxt.getText()).trim();
                cttxt = String.valueOf(editTextcttxt.getText()).trim();
                sttxt = String.valueOf(editTextsttxt.getText()).trim();
                pntxt = String.valueOf(editTextpntxt.getText()).trim();
                dobtxt = String.valueOf(editTextdobtxt.getText()).trim();
                supdttxt = String.valueOf(textViewsupdttxt.getText()).trim();
                gender = String.valueOf(spinnergender.getSelectedItem()).trim();
                //////////////////////////////////////////////////////////
                if(!nametxt.equals("") && !phntxt.equals("") && !usernametxt.equals("") && !pwdtxt.equals("") &&
                        !addtxt.equals("") && !cttxt.equals("") && !sttxt.equals("") && !pntxt.equals("") &&
                        !dobtxt.equals("") && !emailtxt.equals("") && !supdttxt.equals("") && !gender.equals("Select Gender")
                        && pwdtxt.equals(cnfpwdtxt) ) {


                    progressBar.setVisibility(view.VISIBLE);
                    Toast.makeText(getApplicationContext(), "Please Wait..", Toast.LENGTH_LONG).show();
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[12];
                            field[0] = "name";
                            field[1] = "phone";
                            field[2] = "email";
                            field[3] = "username";
                            field[4] = "password";
                            field[5] = "address";
                            field[6] = "city";
                            field[7] = "state";
                            field[8] = "pincode";
                            field[9] = "dob";
                            field[10] = "usertype";
                            field[11] = "gender";
                            //Creating array for data
                            String[] data = new String[12];
                            data[0] = nametxt;
                            data[1] = phntxt;
                            data[2] = emailtxt;
                            data[3] = usernametxt;
                            data[4] = pwdtxt;
                            data[5] = addtxt;
                            data[6] = cttxt;
                            data[7] = sttxt;
                            data[8] = pntxt;
                            data[9] = dobtxt;
                            data[10] = supdttxt;
                            data[11] = gender;
                            PutData putData = new PutData("http://192.168.31.191/SahayogiDB/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(view.GONE);
                                    String result = putData.getResult();
                                    if(result.equals("Sign Up Success"))
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
                    Toast.makeText(getApplicationContext(), "All Fields are Required! Or Password not matched! ", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void openlogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }
    private void updateDate() {
        String dateFormat = "yyyy/dd/MM";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);

        editTextdobtxt.setText(sdf.format(myCalendar.getTime()));
    }
}