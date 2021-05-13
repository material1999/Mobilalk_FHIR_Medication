package com.example.fhir_medication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fhir_medication.helper.NotificationHelper;
import com.example.fhir_medication.R;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getName();
    private static final String PREF_KEY = MainActivity.class.getPackage().toString();
    private static final long LOCATION_REFRESH_TIME = 10;
    private static final float LOCATION_REFRESH_DISTANCE = 10;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    private FirebaseAuth mAuth;
    private SharedPreferences sharedPreferences;
    private NotificationHelper notificationHelper;

    private EditText userNameET;
    private EditText passwordET;
    private TextView locationTV;

    private final LocationListener mLocationListener = new LocationListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onLocationChanged(final Location location) {
            locationTV.setText("Latitude: " + location.getLatitude() +
                    "\nLongitude: " + location.getLongitude());
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) { }

        @Override
        public void onProviderEnabled(String provider) { }

        @Override
        public void onProviderDisabled(String provider) { }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("FHIR_Medication - Login");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameET = findViewById(R.id.editTextUserName);
        passwordET = findViewById(R.id.editTextPassword);
        locationTV = findViewById(R.id.locationText);

        mAuth = FirebaseAuth.getInstance();

        notificationHelper = new NotificationHelper(this);

        sharedPreferences = getSharedPreferences(PREF_KEY, MODE_PRIVATE);
        if (sharedPreferences.contains("username") && sharedPreferences.contains("password")) {
            userNameET.setText(sharedPreferences.getString("username", ""));
            passwordET.setText(sharedPreferences.getString("password", ""));
        }

        checkUserPermission();

        LocationManager mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME,
                LOCATION_REFRESH_DISTANCE, mLocationListener);
    }

    public void login(View view) {
        String userName = userNameET.getText().toString();
        String password = passwordET.getText().toString();

        if (userName.equals("") || password.equals("")) {
            Log.d(LOG_TAG, "Login failed");
            Toast.makeText(MainActivity.this, "Login failed: " +
                    "Username and/or password is empty", Toast.LENGTH_LONG).show();
        } else {
            mAuth.signInWithEmailAndPassword(userName, password).addOnCompleteListener(this, task -> {
                if(task.isSuccessful()){
                    Log.d(LOG_TAG, "Login succeeded: " + userName + " - " + password);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", userName);
                    editor.putString("password", password);
                    editor.apply();
                    notificationHelper.send("Thank you for visiting the medication database!");
                    medicationStart();
                } else {
                    Log.d(LOG_TAG, "Login failed");
                    Toast.makeText(MainActivity.this, "Login failed: " +
                            Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private void medicationStart() {
        Intent intent = new Intent(this, MedicationActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent,
                    ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        } else {
            startActivity(intent);
        }
    }

    void checkUserPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_CODE_ASK_PERMISSIONS);
            }
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onRequestPermissionsResult(int requestCode, String @NotNull [] permissions,
                                           int @NotNull [] grantResults) {
        if (requestCode == REQUEST_CODE_ASK_PERMISSIONS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationTV.setText("Waiting for location information...");
            } else {
                Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOG_TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOG_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", userNameET.getText().toString());
        editor.putString("password", passwordET.getText().toString());
        editor.apply();
        Log.i(LOG_TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Welcome to the login page!", Toast.LENGTH_SHORT).show();
        Log.i(LOG_TAG, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(LOG_TAG, "onRestart");
    }
}