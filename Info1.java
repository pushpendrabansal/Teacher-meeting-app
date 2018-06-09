package com.lnmiit.iotappliaction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.util.HashMap;
import java.util.Map;


public class Info1 extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "ProfileActivity";
    public static final String PROFILE_DISPLAY_NAME = "name";
    public static final String PROFILE_EMAIL = "email";
    private GoogleApiClient mGoogleApiClient;

    Button btn_viewm;
    Button btn_fixm;
    Button btn_req;
    Button logout;
    TextView profileUsername;
    TextView profileEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info1);

        mGoogleApiClient = ((MyAppliaction) getApplication()).getGoogleApiClient(Info1.this, this);
        profileUsername = (TextView)findViewById(R.id.profile_name);
        profileEmail = (TextView)findViewById(R.id.profile_email);


        final String profileDisplayName = returnValueFromBundles(MainActivity.PROFILE_DISPLAY_NAME);
        final String profileUserEmail = returnValueFromBundles(MainActivity.PROFILE_USER_EMAIL);

        assert profileUsername != null;
        profileUsername.append("Hello");
        profileUsername.setText(profileDisplayName);
        assert profileEmail != null;
        profileEmail.setText(profileUserEmail);


        btn_viewm = (Button)findViewById(R.id.btn_viewm);
        btn_viewm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent allmeetingintent = new Intent(Info1.this,
                        viewallmeeting.class);
                allmeetingintent.putExtra(PROFILE_EMAIL,profileUserEmail);
                startActivity(allmeetingintent);
                finish();
            }
        });

        btn_fixm = (Button)findViewById(R.id.btn_fixm);
        btn_fixm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg1) {
                Intent fixmeetingintent = new Intent(Info1.this,
                        fixmeeting.class);
                fixmeetingintent.putExtra(PROFILE_DISPLAY_NAME,profileDisplayName);
                fixmeetingintent.putExtra(PROFILE_EMAIL,profileUserEmail);
                startActivity(fixmeetingintent);
                finish();
            }
        });

        btn_req = (Button)findViewById(R.id.btn_req);
        btn_req.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg2) {
                Intent reqmeetingintent = new Intent(Info1.this,
                        req.class);
                reqmeetingintent.putExtra(PROFILE_EMAIL,profileUserEmail);
                startActivity(reqmeetingintent);
                finish();
            }
        });

        logout = (Button)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg3) {
                Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(Status status) {
                                Intent login = new Intent(Info1.this, MainActivity.class);
                                startActivity(login);
                                finish();
                            }
                        });
            }
            
        });
    }

    private String returnValueFromBundles(String key){
        Bundle inBundle = getIntent().getExtras();
        String returnedValue = inBundle.get(key).toString();
        return returnedValue;
    }
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }
    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }
    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }
}
