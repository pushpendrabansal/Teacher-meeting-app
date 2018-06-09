package com.lnmiit.iotappliaction;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class viewallmeeting extends AppCompatActivity {

    List<reqdata> subjectsList1;

    RecyclerView recyclerView1;

    RecyclerView.LayoutManager recyclerViewlayoutManager1;

    RecyclerView.Adapter recyclerViewadapter1;

    String GET_JSON_FROM_SERVER_NAME = "subjects";

    JsonArrayRequest jsonArrayRequest1 ;

    RequestQueue requestQueue1 ;

    ArrayList<String> SubjectNames1;

    String HTTP_JSON_URL;

    public static final String KEY_ID = "id";

    public String ida;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_card);

        final String profile_email = returnValueFromBundles(Info1.PROFILE_EMAIL);
        String email = profile_email;
        String url = "http://lusip.lnmiit.ac.in/pro/all.php?emailt="+email;
        HTTP_JSON_URL = url;


        subjectsList1 = new ArrayList<>();

        recyclerView1 = (RecyclerView) findViewById(R.id.recyclerview);

        recyclerView1.setHasFixedSize(true);

        recyclerViewlayoutManager1 = new LinearLayoutManager(this);

        recyclerView1.setLayoutManager(recyclerViewlayoutManager1);



        SubjectNames1 = new ArrayList<>();

        JSON_DATA_WEB_CALL();


    }
    public void JSON_DATA_WEB_CALL(){

        jsonArrayRequest1 = new JsonArrayRequest(HTTP_JSON_URL,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        JSON_PARSE_DATA_AFTER_WEBCALL(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue1 = Volley.newRequestQueue(this);

        requestQueue1.add(jsonArrayRequest1);
    }


    public void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray array){

        for(int i = 0; i<array.length(); i++) {

            reqdata GetDataAdapter2 = new reqdata();

            JSONObject json = null;
            try {
                json = array.getJSONObject(i);

                GetDataAdapter2.setNames(json.getString(config.KEY_NAMES));
                GetDataAdapter2.setEmails(json.getString(config.KEY_EMAILS));
                GetDataAdapter2.setDatem(json.getString(config.DATEM));
                GetDataAdapter2.setTimem(json.getString(config.TIMEM));
                GetDataAdapter2.setMsg(json.getString(config.KEY_MSG));
                GetDataAdapter2.setId(json.getInt(config.KEY_ID));

                SubjectNames1.add(json.getString(GET_JSON_FROM_SERVER_NAME));


            } catch (JSONException e) {

                e.printStackTrace();
            }
            subjectsList1.add(GetDataAdapter2);
        }

        recyclerViewadapter1 = new all(subjectsList1, this);

        recyclerView1.setAdapter(recyclerViewadapter1);
    }



    private String returnValueFromBundles(String key){
        Bundle inBundle = getIntent().getExtras();
        String returnedValue = inBundle.get(key).toString();
        return returnedValue;
    }

}