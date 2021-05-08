package com.example.fhir_medication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getName();
    private static final String PREF_KEY = MainActivity.class.getPackage().toString();

    private FirebaseAuth mAuth;
    private SharedPreferences sharedPreferences;

    private EditText userNameET;
    private EditText passwordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameET = findViewById(R.id.editTextUserName);
        passwordET = findViewById(R.id.editTextPassword);

        mAuth = FirebaseAuth.getInstance();

        sharedPreferences = getSharedPreferences(PREF_KEY, MODE_PRIVATE);
        if (sharedPreferences.contains("username") && sharedPreferences.contains("password")) {
            userNameET.setText(sharedPreferences.getString("username", ""));
            passwordET.setText(sharedPreferences.getString("password", ""));
        }
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
                    medicationStart();
                } else {
                    Log.d(LOG_TAG, "Login failed");
                    Toast.makeText(MainActivity.this, "Login failed: " +
                            task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private void medicationStart() {
        Intent intent = new Intent(this, MedicationActivity.class);
        startActivity(intent);
    }
}