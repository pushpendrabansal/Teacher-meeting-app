package com.lnmiit.iotappliaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    int id;
    public static final String KEY_ID = "id";
    List<reqdata> subjects;


    public RecyclerViewAdapter(List<reqdata> getDataAdapter, Context context) {

        super();
        this.subjects = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.request, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, final int position) {
        final reqdata getDataAdapter1 = subjects.get(position);

        holder.nameTextView.setText(getDataAdapter1.getNames());
        holder.emailTextView.setText(getDataAdapter1.getEmails());
        holder.dataTextView.setText(getDataAdapter1.getDatem());
        holder.timeTextView.setText(getDataAdapter1.getTimem());
        holder.msgTextView.setText(getDataAdapter1.getMsg());


        holder.b_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 id = getDataAdapter1.getId();
                Log.d(KEY_ID, ""+id);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://lusip.lnmiit.ac.in/pro/accept.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(context,response,Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(context,error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put(KEY_ID, String.valueOf(id));
                        return params;
                    }
                };

                RequestQueue requestQueue2 = Volley.newRequestQueue(context);
                requestQueue2.add(stringRequest);




            }
        });
        holder.b_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = getDataAdapter1.getId();

                StringRequest stringRequest2 = new StringRequest(Request.Method.POST, "http://lusip.lnmiit.ac.in/pro/reject.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(context,response,Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(context,error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put(KEY_ID, String.valueOf(id));
                        return params;
                    }
                };

                RequestQueue requestQueue3 = Volley.newRequestQueue(context);
                requestQueue3.add(stringRequest2);




            }
        });
    }





@Override
    public int getItemCount() {

        return subjects.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

            public TextView nameTextView;
            public TextView emailTextView;
            public TextView dataTextView;
            public TextView timeTextView;
            public TextView msgTextView;
            public Button b_accept;
            public Button b_reject;


            public ViewHolder(View itemView) {

                super(itemView);


                nameTextView = (TextView) itemView.findViewById(R.id.client_name);
                emailTextView = (TextView) itemView.findViewById(R.id.emails2);
                dataTextView = (TextView) itemView.findViewById(R.id.date_fix);
                timeTextView = (TextView) itemView.findViewById(R.id.time_fix);
                msgTextView = (TextView) itemView.findViewById(R.id.msg2);
                b_accept = (Button) itemView.findViewById(R.id.b_accept);
                b_reject = (Button) itemView.findViewById(R.id.b_reject);

            }


    }


}
