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
    private ArrayList<PatientHealthContract> healthList;

    public HealthDetailsAdapter(Context context, ArrayList<PatientHealthContract> healthList) {
        this.healthList = healthList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextViewAge, mTextViewBloodGroup, mTextViewCondition, mTextViewMedication, mTextViewNotes, mTextViewDateVisit;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.mainLayout_health);
            mTextViewAge = (TextView) itemView.findViewById(R.id.tv_patient_age_value);
            mTextViewBloodGroup = (TextView) itemView.findViewById(R.id.tv_patient_blood_value);
            mTextViewCondition = (TextView) itemView.findViewById(R.id.tv_patient_condition_value);
            mTextViewMedication = (TextView) itemView.findViewById(R.id.tv_patient_medication_value);
            mTextViewNotes = (TextView) itemView.findViewById(R.id.tv_patient_note_value);
            mTextViewDateVisit = (TextView) itemView.findViewById(R.id.tv_patient_DateVisit);
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
        PatientHealthContract healthListItem = healthList.get(position);
        holder.mTextViewAge.setText(healthListItem.getAge());
        holder.mTextViewBloodGroup.setText(healthListItem.getBloodGroup());
        holder.mTextViewCondition.setText(healthListItem.getCondition());
        holder.mTextViewMedication.setText(healthListItem.getMedication());
        holder.mTextViewNotes.setText(healthListItem.getNotes());
        holder.mTextViewDateVisit.setText(healthListItem.getDateVisit());
    }

    @Override
    public int getItemCount() {
        return healthList.size();
    }
}
