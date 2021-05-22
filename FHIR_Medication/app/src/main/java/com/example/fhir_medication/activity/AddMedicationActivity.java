package com.example.fhir_medication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.fhir_medication.R;
import com.example.fhir_medication.model.BatchModel;
import com.example.fhir_medication.model.IngredientModel;
import com.example.fhir_medication.model.MedicationModel;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddMedicationActivity extends AppCompatActivity {

    private FirebaseFirestore mFirestore;
    private static CollectionReference mItems;

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

        mFirestore = FirebaseFirestore.getInstance();
        mItems = mFirestore.collection("Medication");

        backButton = findViewById(R.id.backButton);
        addButton = findViewById(R.id.addButton);

        backButton.setOnClickListener(v -> {
            finish();
        });

        addButton.setOnClickListener(v -> {
            MedicationModel item11 = new MedicationModel(new ArrayList<>(
                    Arrays.asList("med0311", "medication0311")), "asd",
                    null, "asd", "asd",
                    null,
                    new ArrayList<>(Arrays.asList(
                            new IngredientModel("asd", null, 500))),
                    new BatchModel("123",
                            new GregorianCalendar(2017, Calendar.MAY, 22).getTime()),
                    null);
            mItems.add(item11);
            Toast.makeText(AddMedicationActivity.this,
                    "Item added successfully", Toast.LENGTH_LONG).show();
            finish();
        });
    }
}