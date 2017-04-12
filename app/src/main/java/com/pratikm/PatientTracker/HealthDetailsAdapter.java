package com.pratikm.PatientTracker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by pratikmishra on 12/04/17.
 */

public class HealthDetailsAdapter extends RecyclerView.Adapter<HealthDetailsAdapter.ViewHolder> {

    Context context;
    private ArrayList<HashMap<String, String>> healthList;

    public HealthDetailsAdapter(Context context, ArrayList<HashMap<String, String>> healthList) {
        this.healthList = healthList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextViewAge, mTextViewBloodGroup;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.mainLayout_health);
            mTextViewAge = (TextView) itemView.findViewById(R.id.tv_age);
            mTextViewBloodGroup = (TextView) itemView.findViewById(R.id.tv_blood_group);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.health_details_list_item, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(HealthDetailsAdapter.ViewHolder holder, int position) {
        holder.mTextViewAge.setText(healthList.get(position).get("age"));
        holder.mTextViewBloodGroup.setText(healthList.get(position).get("bloodGroup"));
    }

    @Override
    public int getItemCount() {
        return healthList.size();
    }
}
