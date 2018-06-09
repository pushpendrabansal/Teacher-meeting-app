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


public class req extends AppCompatActivity {

    List<reqdata> subjectsList;

    RecyclerView recyclerView;

    RecyclerView.LayoutManager recyclerViewlayoutManager;

    RecyclerView.Adapter recyclerViewadapter;

    ProgressBar progressBar;

    String GET_JSON_FROM_SERVER_NAME = "subjects";

    JsonArrayRequest jsonArrayRequest ;

    RequestQueue requestQueue ;

    View ChildView ;

    int GetItemPosition ;

    ArrayList<String> SubjectNames;


    String HTTP_JSON_URL;

    public static final String KEY_ID = "id";

    public String ida;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_card2);

        final String profile_email = returnValueFromBundles(Info1.PROFILE_EMAIL);
        String email = profile_email;
        String url = config.DATA_URL+email;
        HTTP_JSON_URL = url;


        subjectsList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);



        recyclerView.setHasFixedSize(true);

        recyclerViewlayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(recyclerViewlayoutManager);



        SubjectNames = new ArrayList<>();

        JSON_DATA_WEB_CALL();


    }
    public void JSON_DATA_WEB_CALL(){

        jsonArrayRequest = new JsonArrayRequest(HTTP_JSON_URL,

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

        requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(jsonArrayRequest);
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

                SubjectNames.add(json.getString(GET_JSON_FROM_SERVER_NAME));


            } catch (JSONException e) {

                e.printStackTrace();
            }
            subjectsList.add(GetDataAdapter2);
        }

        recyclerViewadapter = new RecyclerViewAdapter(subjectsList, this);

        recyclerView.setAdapter(recyclerViewadapter);
    }



    private String returnValueFromBundles(String key){
        Bundle inBundle = getIntent().getExtras();
        String returnedValue = inBundle.get(key).toString();
        return returnedValue;
    }

}