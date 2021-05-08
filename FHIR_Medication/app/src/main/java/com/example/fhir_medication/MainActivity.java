package com.example.fhir_medication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getName();

    EditText userNameET;
    EditText passwordET;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameET = findViewById(R.id.editTextUserName);
        passwordET = findViewById(R.id.editTextPassword);

        mAuth = FirebaseAuth.getInstance();
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