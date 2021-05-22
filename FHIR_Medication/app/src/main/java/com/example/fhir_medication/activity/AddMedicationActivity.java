package com.example.fhir_medication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.fhir_medication.R;

public class AddMedicationActivity extends AppCompatActivity {

    Button backButton;
    Button addButton;
    EditText identifier1;
    EditText identifier2;
    EditText code;
    EditText status;
    EditText manufacturer;
    EditText form;
    EditText amount;
    EditText ingredient1Item;
    RadioGroup ingredient1IsActive;
    EditText ingredient1Strength;
    EditText ingredient2Item;
    RadioGroup ingredient2IsActive;
    EditText ingredient2Strength;
    EditText batch1LotNumber;
    EditText batch1ExpirationDate;
    EditText batch2LotNumber;
    EditText batch2ExpirationDate;

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