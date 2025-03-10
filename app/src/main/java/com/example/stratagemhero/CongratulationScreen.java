package com.example.stratagemhero;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class CongratulationScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_congratulation_screen);

        TextView tvDisplayCompleteAmount = findViewById(R.id.tvDisplayCompleteAmount);

        Button btnBack = findViewById(R.id.btnBack);
        Button btnRestart = findViewById(R.id.btnCompleteRestart);

        Bundle receivedBundle = getIntent().getExtras();
        int overallCount = receivedBundle.getInt("key_combos_completed");

        tvDisplayCompleteAmount.setText(String.format("%s: %s%n", getResources().getString(R.string.tvDisplayOverall), overallCount));

        // returns user to MainMenu.java without resetting data
        btnBack.setOnClickListener(v -> {
            getOnBackPressedDispatcher().onBackPressed();
        });

        // sends user back to MainMenu.java with its data reset
        btnRestart.setOnClickListener(v -> {
            Toast toast = Toast.makeText(CongratulationScreen.this, "All existing progress has been reset.", Toast.LENGTH_SHORT);
            toast.show();
            Intent intent = new Intent(CongratulationScreen.this, MainMenu.class);

            // clear SharedPreferences data
            SharedPreferences sharedPreferences = getSharedPreferences("spRecyclerView", MODE_PRIVATE);
            sharedPreferences.edit().clear().apply();

            startActivity(intent);
        });
    }
}