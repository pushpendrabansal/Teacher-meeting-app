package com.lnmiit.iotappliaction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by punit on 4/23/2017.
 */
public class fixmeeting extends AppCompatActivity implements View.OnClickListener{


    public static final String REGISTER_URL = "http://lusip.lnmiit.ac.in/pro/meetingsend.php";
    public static final String KEY_DATEM = "datem";
    public static final String KEY_TIMEM = "timem";
    public static final String KEY_EMAILT = "emailt";
    public static final String KEY_EMAILS = "emails";
    public static final String KEY_NAMES = "names";
    public static final String KEY_MSG = "msg";


  EditText email;
    EditText in_msg;
   TextView time1;
    TextView date1;

    String emails ;
  String names;
    Button submitbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fix_meeting);

        String profile_email = returnValueFromBundles(Info1.PROFILE_EMAIL);
        String profile_name = returnValueFromBundles(Info1.PROFILE_DISPLAY_NAME);

        emails= profile_email;
        names = profile_name;

        email = (EditText) findViewById(R.id.email);
        in_msg = (EditText) findViewById(R.id.in_msg);
        submitbutton= (Button) findViewById(R.id.submitbutton);
        time1= (TextView) findViewById(R.id.time1);
        date1= (TextView) findViewById(R.id.date1);
        submitbutton.setOnClickListener(this);
    }
    @Override
     public void onClick(View v) {
        registerUser();
    }
    private void registerUser() {
        final String datem = date1.getText().toString().trim();
        final String timem = time1.getText().toString().trim();
        final String emailt= email.getText().toString().trim();
        final String msg=in_msg.getText().toString().trim();

        StringRequest stringRequest3 = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(fixmeeting.this,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(fixmeeting.this,error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_DATEM, datem);
                params.put(KEY_TIMEM, timem);
                params.put(KEY_EMAILT, emailt);
                params.put(KEY_EMAILS, emails);
                params.put(KEY_NAMES, names);
                params.put(KEY_MSG, msg);

                return params;
            }
            };

            RequestQueue requestQueue3 = Volley.newRequestQueue(this);
            requestQueue3.add(stringRequest3);



            }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
    private String returnValueFromBundles(String key){
        Bundle inBundle = getIntent().getExtras();
        String returnedValue = inBundle.get(key).toString();
        return returnedValue;
    }

}