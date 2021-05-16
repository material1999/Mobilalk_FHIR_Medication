package com.example.fhir_medication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fhir_medication.fragment.MedicationFragment;
import com.example.fhir_medication.fragment.ProfileFragment;
import com.example.fhir_medication.R;
import com.example.fhir_medication.fragment.StatisticsFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

import java.security.AuthProvider;
import java.util.Objects;

public class MedicationActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String LOG_TAG = MedicationActivity.class.getName();
    private FirebaseUser user;

    private BottomNavigationView bottomNavigationView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("FHIR_Medication - Medication");

        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        getWindow().setEnterTransition(new Slide(Gravity.BOTTOM));

        user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        Log.d(LOG_TAG, "user: " + user.getEmail());

        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Bundle bundle = new Bundle();
            bundle.putString("email", user.getEmail());
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, MedicationFragment.class, bundle)
                    .commit();
        }
        setContentView(R.layout.activity_medication);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }


    @SuppressLint({"SetTextI18n", "NonConstantResourceId"})
    @Override
    public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {
        Fragment selectedFragment = null;
        Bundle bundle = new Bundle();
        bundle.putString("email", user.getEmail());
        switch (item.getItemId()) {
            case R.id.medicationPage:
                setTitle("FHIR_Medication - Medication");
                selectedFragment = new MedicationFragment();
                selectedFragment.setArguments(bundle);
                Log.d(LOG_TAG, "Medication");
                break;
            case R.id.statistics:
                setTitle("FHIR_Medication - Statistics");
                selectedFragment = new StatisticsFragment();
                selectedFragment.setArguments(bundle);
                Log.d(LOG_TAG, "Statistics");
                break;
            case R.id.profilePage:
                setTitle("FHIR_Medication - Profile");
                selectedFragment = new ProfileFragment();
                selectedFragment.setArguments(bundle);
                Log.d(LOG_TAG, "Profile");
                break;
            case R.id.logOut:
                Log.d(LOG_TAG, "User logged out successfully");
                FirebaseAuth.getInstance().signOut();
                finish();
                break;
        }
        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container_view, selectedFragment, null)
                    .commit();
        }
        return true;
    }

    public void changePassword(View view) {
        Log.d(LOG_TAG, "Change password");
        EditText oldPasswordET = findViewById(R.id.oldPassword);
        EditText newPasswordET = findViewById(R.id.newPassword);
        EditText newPasswordAgainET = findViewById(R.id.newPasswordAgain);
        String oldPassword = oldPasswordET.getText().toString();
        String newPassword = newPasswordET.getText().toString();
        String newPasswordAgain = newPasswordAgainET.getText().toString();
        Log.d(LOG_TAG, user.getEmail());
        if (oldPassword.isEmpty() || newPassword.isEmpty() || newPasswordAgain.isEmpty()) {
            oldPasswordET.setText("");
            newPasswordET.setText("");
            newPasswordAgainET.setText("");
            Toast.makeText(MedicationActivity.this,
                    "Please fill all 3 fields", Toast.LENGTH_LONG).show();
        } else {
            AuthCredential credential = EmailAuthProvider
                    .getCredential(user.getEmail(), oldPassword);
            user.reauthenticate(credential).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    if (newPassword.equals(newPasswordAgain)) {
                        oldPasswordET.setText("");
                        newPasswordET.setText("");
                        newPasswordAgainET.setText("");
                        user.updatePassword(newPassword);
                        Toast.makeText(MedicationActivity.this,
                                "Password updated successfully", Toast.LENGTH_LONG).show();
                        Log.d(LOG_TAG, "Password updated: " + oldPassword + " --> " + newPassword);
                    } else {
                        oldPasswordET.setText("");
                        newPasswordET.setText("");
                        newPasswordAgainET.setText("");
                        Toast.makeText(MedicationActivity.this,
                                "New passwords don't match", Toast.LENGTH_LONG).show();
                    }
                } else {
                    oldPasswordET.setText("");
                    newPasswordET.setText("");
                    newPasswordAgainET.setText("");
                    Toast.makeText(MedicationActivity.this,
                            "Incorrect old password", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}