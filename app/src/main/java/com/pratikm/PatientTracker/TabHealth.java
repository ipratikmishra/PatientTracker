package com.pratikm.PatientTracker;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by pratikmishra on 08/04/17.
 */

public class TabHealth extends Fragment {
    Spinner mSpinnerBloodGroup;
    public static String bloodType;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_health_details, container, false);

        mSpinnerBloodGroup = (Spinner)rootView.findViewById(R.id.spinner_blood);
        mSpinnerBloodGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                bloodType = mSpinnerBloodGroup.getSelectedItem().toString();
                ((AddPatientActivity)getActivity()).setBloodType(bloodType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return rootView;
    }

}
