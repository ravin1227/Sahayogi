package com.team.sahayogi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Search_service extends AppCompatActivity {

    String gval;
    TextView textViewupdatetxt, nametxt, emailtxt, addresstxt, gendertxt, phonetxt, citytxt, statetxt, pincodetxt, categorytxt, experiencetxt, textView2 ;
    CardView servicemendetails;
    EditText editTextpntxt;
    ProgressBar progress;
    Spinner spinnercategory;
    Button searchbtn, returnbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_service);

        gval = getIntent().getExtras().getString("updatetxt");
        textViewupdatetxt= findViewById(R.id.updatetxt);
        textViewupdatetxt.setText(gval);

        textView2 = findViewById(R.id.textView2);
        searchbtn = findViewById(R.id.searchbtn);
        progress = findViewById(R.id.progress);
        editTextpntxt = findViewById(R.id.pntxt);
        spinnercategory = findViewById(R.id.category);
        servicemendetails = findViewById(R.id.servicemendetails);
        nametxt = findViewById(R.id.nametxt);
        emailtxt = findViewById(R.id.emailtxt);
        addresstxt = findViewById(R.id.addresstxt);
        gendertxt = findViewById(R.id.gendertxt);
        phonetxt = findViewById(R.id.phonetxt);
        citytxt = findViewById(R.id.citytxt);
        statetxt = findViewById(R.id.statetxt);
        pincodetxt = findViewById(R.id.pincodetxt);
        categorytxt = findViewById(R.id.categorytxt);
        experiencetxt = findViewById(R.id.experiencetxt);
        returnbtn = findViewById(R.id.returnbtn);

        ArrayAdapter<String> myadapter  = new ArrayAdapter<String>(Search_service.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Category));
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnercategory.setAdapter(myadapter);

        returnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUserhome();
            }
        });

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  category, pincode;
                pincode = String.valueOf(editTextpntxt.getText()).trim();
                category = String.valueOf(spinnercategory.getSelectedItem()).trim();

                if (pincode.equals("") && category.equals("Select Category")) {
                    Toast.makeText(Search_service.this, "Please enter Pin Code and Select Category ", Toast.LENGTH_LONG).show();
                    return;
                }
                progress.setVisibility(view.VISIBLE);
                Toast.makeText(getApplicationContext(), "Please Wait..", Toast.LENGTH_LONG).show();
                getServicemanDetails(pincode,category);

            }
        });
    }

    public void openUserhome() {
        String s = String.valueOf(textViewupdatetxt.getText()).trim();
        Intent intent = new Intent(this, Welcome_user.class);
        intent.putExtra("updatetxt", s);
        startActivity(intent);
        finish();
    }

    private void getServicemanDetails(String pin, String cat) {
        String url = "http://192.168.31.191/SahayogiDB/searchservice.php";

        RequestQueue queue = Volley.newRequestQueue(Search_service.this);

        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("name") == null) {

                        Toast.makeText(Search_service.this, "Please enter valid id.", Toast.LENGTH_LONG).show();
                    } else {
                        progress.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),  "Success!", Toast.LENGTH_SHORT).show();
                        nametxt.setText(jsonObject.getString("name"));
                        emailtxt.setText(jsonObject.getString("email"));
                        addresstxt.setText(jsonObject.getString("address"));
                        gendertxt.setText(jsonObject.getString("gender"));
                        phonetxt.setText(jsonObject.getString("phone"));
                        citytxt.setText(jsonObject.getString("city"));
                        statetxt.setText(jsonObject.getString("state"));
                        pincodetxt.setText(jsonObject.getString("pincode"));
                        categorytxt.setText(jsonObject.getString("category"));
                        experiencetxt.setText(jsonObject.getString("experience"));
                        servicemendetails.setVisibility(View.VISIBLE);
                        returnbtn.setVisibility(View.VISIBLE);

                        searchbtn.setVisibility(View.GONE);
                        editTextpntxt.setVisibility(View.GONE);
                        spinnercategory.setVisibility(View.GONE);
                        textView2.setVisibility(View.GONE);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Search_service.this, "Fail to get Servicemen Details" + error, Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            public String getBodyContentType() {

                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();

                params.put("pincode", pin);
                params.put("category", cat);

                return params;
            }
        };
        queue.add(request);


    }
}