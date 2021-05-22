package com.example.fhir_medication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.fhir_medication.R;

public class AddMedicationActivity extends AppCompatActivity {

    Button backButton;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("FHIR_Medication - Add medication");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medication);

        backButton = findViewById(R.id.backButton);
        addButton = findViewById(R.id.addButton);

        backButton.setOnClickListener(v -> {
            finish();
        });

        addButton.setOnClickListener(v -> {
            //TODO: create medication and add item to firestore
        });
    }
}