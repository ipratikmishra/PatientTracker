package com.pratikm.PatientTracker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

/**
 * Created by pratikmishra on 12/04/17.
 */

public class SearchNameAdapter extends RecyclerView.Adapter<SearchNameAdapter.MyMainViewHolder> {

    private Context context;
    private AdapterCallback mAdapterCallback;
    private ArrayList<PatientContactContract> patientList;

    public SearchNameAdapter(Context context, ArrayList<PatientContactContract> patientList) {
        this.patientList = patientList;
        this.context = context;
        this.mAdapterCallback = ((AdapterCallback) context);
    }

    public class MyMainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView mTextViewPatientFirstName, mTextViewPatientLastName, mTextViewPatientSex, mTextViewPatientEmail, mTextViewPatientMobile;
        ImageView mImageViewUser;
        LinearLayout linearLayout;

        public MyMainViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.mainLayout_contact);
            mTextViewPatientFirstName = (TextView) itemView.findViewById(R.id.tv_patient_first_name);
            mTextViewPatientLastName = (TextView) itemView.findViewById(R.id.tv_patient_last_name);
            mTextViewPatientSex = (TextView) itemView.findViewById(R.id.tv_patient_sex);
            mTextViewPatientEmail = (TextView) itemView.findViewById(R.id.tv_patient_email);
            mTextViewPatientMobile = (TextView) itemView.findViewById(R.id.tv_patient_mobile);
            mImageViewUser = (ImageView) itemView.findViewById(R.id.ic_user);
        }

        @Override
        public void onClick(View itemView) {
            Intent i = new Intent(itemView.getContext(), HealthDetailsActivity.class);
            i.putExtra("clickedPatientEmail", mTextViewPatientEmail.getText().toString());
            itemView.getContext().startActivity(i);
        }


        @Override
        public boolean onLongClick(View itemView) {
                    final String Email = mTextViewPatientEmail.getText().toString();
                    AlertDialog.Builder a_builder = new AlertDialog.Builder(itemView.getContext());
                    a_builder.setMessage("Do you want to delete this Patient Record?")
                            .setCancelable(false)
                            .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mAdapterCallback.deleteRecord(Email);
                                    Toast.makeText(context, "Patient Deleted", Toast.LENGTH_SHORT).show();
                                    patientList.remove(getAdapterPosition());
                                    notifyDataSetChanged();
                                }
                            }).setNegativeButton("No",new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            }) ;
                    AlertDialog alert = a_builder.create();
                    alert.setTitle("PatientTracker");
                    alert.show();
            return true;
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

    public void setFilter(ArrayList<PatientContactContract> newList) {
        patientList = new ArrayList<>();
        patientList.addAll(newList);
        notifyDataSetChanged();
    }
    public static interface AdapterCallback {
        void deleteRecord(String Email);
    }
}
