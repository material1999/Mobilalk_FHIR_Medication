package com.example.fhir_medication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.fhir_medication.R;
import com.example.fhir_medication.model.MedicationModel;

public class EditMedicationActivity extends AppCompatActivity {

    private static final String LOG_TAG = EditMedicationActivity.class.getName();

    MedicationModel item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_medication);
        item = (MedicationModel)getIntent().getSerializableExtra("item");
    }
}