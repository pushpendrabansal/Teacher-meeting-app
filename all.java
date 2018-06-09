package com.lnmiit.iotappliaction;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;



public class all extends RecyclerView.Adapter<all.ViewHolder> {

    Context context;
    int id;
    public static final String KEY_ID = "id";
    List<reqdata> subjects;


    public all(List<reqdata> getDataAdapter, Context context) {

        super();
        this.subjects = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.allmeeting, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(all.ViewHolder holder, final int position) {
        final reqdata getDataAdapter1 = subjects.get(position);

        holder.nameTextView.setText(getDataAdapter1.getNames());
        holder.emailTextView.setText(getDataAdapter1.getEmails());
        holder.dataTextView.setText(getDataAdapter1.getDatem());
        holder.timeTextView.setText(getDataAdapter1.getTimem());
        holder.msgTextView.setText(getDataAdapter1.getMsg());



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



        public ViewHolder(View itemView) {

            super(itemView);


            nameTextView = (TextView) itemView.findViewById(R.id.client_name);
            emailTextView = (TextView) itemView.findViewById(R.id.emails2);
            dataTextView = (TextView) itemView.findViewById(R.id.date_fix);
            timeTextView = (TextView) itemView.findViewById(R.id.time_fix);
            msgTextView = (TextView) itemView.findViewById(R.id.msg2);


        }


    }


}
