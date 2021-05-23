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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class EditMedicationActivity extends AppCompatActivity {

    private FirebaseFirestore mFirestore;
    private static CollectionReference mItems;

    MedicationModel editItem;

    Button backButton;
    Button editButton;
    EditText identifier1_IN;
    EditText identifier2_IN;
    EditText code_IN;
    EditText status_IN;
    EditText manufacturer_IN;
    EditText form_IN;
    EditText amount_IN;
    EditText ingredient1Item_IN;
    RadioGroup ingredient1IsActive_IN;
    EditText ingredient1Strength_IN;
    EditText ingredient2Item_IN;
    RadioGroup ingredient2IsActive_IN;
    EditText ingredient2Strength_IN;
    EditText batchLotNumber_IN;
    EditText batchExpirationDate_IN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("FHIR_Medication - Edit medication");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_medication);

        editItem = (MedicationModel)getIntent().getSerializableExtra("item");

        mFirestore = FirebaseFirestore.getInstance();
        mItems = mFirestore.collection("Medication");

        backButton = findViewById(R.id.backButton);
        editButton = findViewById(R.id.editButton);
        identifier1_IN = findViewById(R.id.identifier1);
        identifier2_IN = findViewById(R.id.identifier2);
        code_IN = findViewById(R.id.code);
        status_IN = findViewById(R.id.status);
        manufacturer_IN = findViewById(R.id.manufacturer);
        form_IN = findViewById(R.id.form);
        amount_IN = findViewById(R.id.amount);
        ingredient1Item_IN = findViewById(R.id.ingredient1_item);
        ingredient1IsActive_IN = findViewById(R.id.ingredient1_isActive);
        ingredient1Strength_IN = findViewById(R.id.ingredient1_strength);
        ingredient2Item_IN = findViewById(R.id.ingredient2_item);
        ingredient2IsActive_IN = findViewById(R.id.ingredient2_isActive);
        ingredient2Strength_IN = findViewById(R.id.ingredient2_strength);
        batchLotNumber_IN = findViewById(R.id.batch_lotNumber);
        batchExpirationDate_IN = findViewById(R.id.batch_expirationDate);



        backButton.setOnClickListener(v -> finish());

        editButton.setOnClickListener(v -> {
            String identifier1 = identifier1_IN.getText().toString();
            String identifier2 = identifier2_IN.getText().toString();
            String code = code_IN.getText().toString();
            String status = status_IN.getText().toString();
            String manufacturer = manufacturer_IN.getText().toString();
            String form = form_IN.getText().toString();
            Integer amount = null;
            if (!amount_IN.getText().toString().equals("")) {
                amount = Integer.parseInt(amount_IN.getText().toString());
            }
            String ingredient1Item = ingredient1Item_IN.getText().toString();
            Boolean ingredient1IsActive = null;
            switch (ingredient1IsActive_IN.getCheckedRadioButtonId()) {
                case 0: ingredient1IsActive = true; break;
                case 1: ingredient1IsActive = false; break;
            }
            Integer ingredient1Strength = null;
            if (!ingredient1Strength_IN.getText().toString().equals("")) {
                ingredient1Strength = Integer.parseInt(ingredient1Strength_IN.getText().toString());
            }
            String ingredient2Item = ingredient2Item_IN.getText().toString();
            Boolean ingredient2IsActive = null;
            switch (ingredient2IsActive_IN.getCheckedRadioButtonId()) {
                case 0: ingredient2IsActive = true; break;
                case 1: ingredient2IsActive = false; break;
            }
            Integer ingredient2Strength = null;
            if (!ingredient2Strength_IN.getText().toString().equals("")) {
                ingredient2Strength = Integer.parseInt(ingredient2Strength_IN.getText().toString());
            }
            String batchLotNumber = batchLotNumber_IN.getText().toString();
            Date batchExpirationDate = null;
            if (!batchExpirationDate_IN.getText().toString().equals("")) {
                try {
                    batchExpirationDate = new SimpleDateFormat("dd/MM/yyyy")
                            .parse(batchExpirationDate_IN.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            Integer imageResource = editItem.getImageResource();

            MedicationModel item = new MedicationModel(new ArrayList<>(
                    Arrays.asList(identifier1, identifier2)), code, status, manufacturer, form, amount,
                    new ArrayList<>(Arrays.asList(
                            new IngredientModel(ingredient1Item, ingredient1IsActive, ingredient1Strength),
                            new IngredientModel(ingredient2Item, ingredient2IsActive, ingredient2Strength))),
                    new BatchModel(batchLotNumber, batchExpirationDate), imageResource);

            //mItems.document(editItem._getId()).update("code", item.getCode());
            mItems.document(editItem._getId()).set(item);

            Toast.makeText(EditMedicationActivity.this,
                    "Item edited successfully!", Toast.LENGTH_LONG).show();
            finish();
        });
    }
}