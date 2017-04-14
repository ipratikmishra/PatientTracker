package com.pratikm.PatientTracker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by pratikmishra on 12/04/17.
 */

public class SearchNameAdapter extends RecyclerView.Adapter<SearchNameAdapter.MyMainViewHolder> {

    private Context context;
    private ArrayList<PatientContactContract> patientList;

    public SearchNameAdapter(Context context, ArrayList<PatientContactContract> patientList) {
        this.patientList = patientList;
        this.context = context;
    }

    public class MyMainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mTextViewPatientFirstName, mTextViewPatientLastName, mTextViewPatientSex, mTextViewPatientEmail, mTextViewPatientMobile;
        ImageView mImageViewUser;
        LinearLayout linearLayout;

        public MyMainViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.mainLayout);
            mTextViewPatientFirstName = (TextView) itemView.findViewById(R.id.tv_patient_first_name);
            mTextViewPatientLastName = (TextView) itemView.findViewById(R.id.tv_patient_last_name);
            mTextViewPatientSex = (TextView) itemView.findViewById(R.id.tv_patient_sex);
            mTextViewPatientEmail = (TextView) itemView.findViewById(R.id.tv_patient_email);
            mTextViewPatientMobile = (TextView) itemView.findViewById(R.id.tv_patient_mobile);
            mImageViewUser = (ImageView) itemView.findViewById(R.id.ic_user);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, HealthDetailsActivity.class);
            context.startActivity(new Intent(context, HealthDetailsActivity.class));
        }
    }


    @Override
    public MyMainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item, parent, false);
        MyMainViewHolder holder = new MyMainViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyMainViewHolder holder, final int position) {
        PatientContactContract currentPatient = patientList.get(position);
        holder.mTextViewPatientFirstName.setText(currentPatient.getFirstName());
        holder.mTextViewPatientLastName.setText(currentPatient.getLastName());
        holder.mTextViewPatientSex.setText(currentPatient.getSex());
        holder.mTextViewPatientEmail.setText(currentPatient.getEmailAddress());
        holder.mTextViewPatientMobile.setText(currentPatient.getMobile());
    }



    @Override
    public int getItemCount() {
        return patientList.size();
    }

}