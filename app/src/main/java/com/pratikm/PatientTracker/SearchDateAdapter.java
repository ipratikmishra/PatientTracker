package com.pratikm.PatientTracker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pratikmishra on 15/04/17.
 */

public class SearchDateAdapter extends RecyclerView.Adapter<SearchDateAdapter.ViewHolder> {
    Context context;
    private ArrayList<PatientHealthContract> healthList;

    public SearchDateAdapter(Context context, ArrayList<PatientHealthContract> healthList) {
        this.healthList = healthList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextViewAge, mTextViewBloodGroup, mTextViewCondition, mTextViewMedication, mTextViewNotes, mTextViewDateVisit, mTextViewEmail;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.mainLayout_health_by_date);
            mTextViewAge = (TextView) itemView.findViewById(R.id.tv_patient_age_value);
            mTextViewBloodGroup = (TextView) itemView.findViewById(R.id.tv_patient_blood_value);
            mTextViewCondition = (TextView) itemView.findViewById(R.id.tv_patient_condition_value);
            mTextViewMedication = (TextView) itemView.findViewById(R.id.tv_patient_medication_value);
            mTextViewNotes = (TextView) itemView.findViewById(R.id.tv_patient_note_value);
            mTextViewDateVisit = (TextView) itemView.findViewById(R.id.tv_patient_DateVisit);
            mTextViewEmail = (TextView) itemView.findViewById(R.id.tv_patient_email_id);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.health_by_date_list_item, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(SearchDateAdapter.ViewHolder holder, int position) {
        PatientHealthContract healthListItem = healthList.get(position);
        holder.mTextViewAge.setText(healthListItem.getAge());
        holder.mTextViewBloodGroup.setText(healthListItem.getBloodGroup());
        holder.mTextViewCondition.setText(healthListItem.getCondition());
        holder.mTextViewMedication.setText(healthListItem.getMedication());
        holder.mTextViewNotes.setText(healthListItem.getNotes());
        holder.mTextViewDateVisit.setText(healthListItem.getDateVisit());
        holder.mTextViewEmail.setText(healthListItem.getEmail());
    }


    @Override
    public int getItemCount() {
        return healthList.size();
    }
}