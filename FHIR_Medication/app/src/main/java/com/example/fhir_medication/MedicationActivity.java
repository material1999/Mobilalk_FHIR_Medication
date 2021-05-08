package com.example.fhir_medication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MedicationActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String LOG_TAG = MedicationActivity.class.getName();
    private FirebaseUser user;

    private BottomNavigationView bottomNavigationView;
    private TextView textView;
    private FloatingActionButton floatingActionButton;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        getWindow().setEnterTransition(new Slide(Gravity.BOTTOM));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);

        user = FirebaseAuth.getInstance().getCurrentUser();
        Log.d(LOG_TAG, "user: " + user.getEmail());

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        textView = findViewById(R.id.textView);
        textView.setText("Medication");

        floatingActionButton = findViewById(R.id.addMedicationButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: implement medication add
                textView.setText("Add medication");
                Log.d(LOG_TAG, "Adding medication");
            }
        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.medicationPage:
                floatingActionButton.setVisibility(View.VISIBLE);
                Log.d(LOG_TAG, "Medication");
                textView.setText("Medication");
                break;
            case R.id.profilePage:
                Log.d(LOG_TAG, "Profile");
                textView.setText("Profile");
                floatingActionButton.setVisibility(View.INVISIBLE);
                break;
            case R.id.logOut:
                Log.d(LOG_TAG, "User logged out successfully");
                FirebaseAuth.getInstance().signOut();
                finish();
                break;
        }
        return true;
    }

    public void addMedication() {

    }
}