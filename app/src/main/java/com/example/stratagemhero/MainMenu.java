package com.example.stratagemhero;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stratagemhero.stratagem_handler.Stratagem;
import com.example.stratagemhero.stratagem_handler.StratagemAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;


public class MainMenu extends AppCompatActivity implements RecyclerViewStratagemInterface {
    int storeCount;
    TextView tvComboCounter;
    RecyclerView rvStratagems;
    ArrayList<Stratagem> alStratagems;
    Button btnRestart;
//    LinearLayout stratagemContainer;
    private StratagemAdapter stratagemAdapter;

    private StratagemAdapter savedStratagemAdapter;

    private ActivityResultLauncher<Intent> launcher;

    LinearLayoutManager layoutManager = new LinearLayoutManager(MainMenu.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main_menu);

        btnRestart = findViewById(R.id.btnRestart);
        rvStratagems = findViewById(R.id.rvStratagems);
        tvComboCounter = findViewById(R.id.tvComboCounter);
        tvComboCounter.setText(String.format("%s: 0%n", getResources().getString(R.string.correctComboText)));

        rvStratagems.setLayoutManager(layoutManager);

        alStratagems = new ArrayList<>();

        // fetch previous data change if user has made progress
        fetchRecyclerViewContent();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // define restart button functionality
        btnRestart.setOnClickListener(v -> {
            storeCount = 0;
            tvComboCounter.setText(String.format("%s: %s%n", getResources().getString(R.string.correctComboText), storeCount));

            SharedPreferences sharedPreferences = getSharedPreferences("spRecyclerView", MODE_PRIVATE);
            sharedPreferences.edit().clear().apply();

            alStratagems.clear();
            initialiseConstructor();

            stratagemAdapter = new StratagemAdapter(alStratagems, this);
            rvStratagems.setAdapter(stratagemAdapter);
            stratagemAdapter.notifyDataSetChanged();

            Toast toast = Toast.makeText(MainMenu.this, "All existing progress has been reset.", Toast.LENGTH_SHORT);
            toast.show();
        });

        // call method to reflect changes coming from Game.java, defining user completion behaviour
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();

                String stringIndex = data.getStringExtra("key_return_index");
                int receivedIndex = Integer.parseInt(stringIndex);
                storeCount += data.getIntExtra("key_add_count", 0);

                boolean isCompleted = data.getBooleanExtra("key_return_completion", false);

                if (!isCompleted) {
                    alStratagems.get(receivedIndex).setCompleted(false);
                    alStratagems.get(receivedIndex).setStratagemColor("#ffff6363");
                }

                else {
                    alStratagems.get(receivedIndex).setCompleted(true);
                    alStratagems.get(receivedIndex).setStratagemColor("#ff48f542");
                    tvComboCounter.setText(String.format("%s: %s%n", getResources().getString(R.string.correctComboText), storeCount));
                }

                Log.d("stringIndex", alStratagems.get(receivedIndex).fetchStratagemColor());
                stratagemAdapter.notifyItemChanged(receivedIndex);

                saveRecyclerViewContent();

                if (storeCount == alStratagems.size()) {
                    Intent intent = new Intent(MainMenu.this, CongratulationScreen.class);
                    intent.putExtra("key_combos_completed", storeCount);

                    startActivity(intent);
                }
            }
        });
    }

    // method to store progress for RecyclerView content
    private void saveRecyclerViewContent() {
        Gson gson = new Gson();
        String json = gson.toJson(alStratagems);

        SharedPreferences sharedPreferences = getSharedPreferences("spRecyclerView", MODE_PRIVATE);

        sharedPreferences
                .edit().putString("key_recycler_view_state", json)
                .putInt("key_correct_combo_count", storeCount)
                .apply();
    }

    // method to recall previously made progress for RecyclerView content
    public void fetchRecyclerViewContent() {
        Log.d("SharedPreferences", "fetchRecyclerViewContent method has been called.");
        SharedPreferences sharedPreferences = getSharedPreferences("spRecyclerView", MODE_PRIVATE);
        String serialisedObject = sharedPreferences.getString("key_recycler_view_state", null);
        storeCount = sharedPreferences.getInt("key_correct_combo_count", 0);

        if (serialisedObject != null) {
            Log.d("SharedPreferences", "Saved progress found, fetching previous content.");
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Stratagem>>(){}.getType();
            alStratagems = new ArrayList<>(gson.fromJson(serialisedObject, type));

            stratagemAdapter = new StratagemAdapter(alStratagems, this);
            rvStratagems.setAdapter(stratagemAdapter);

            Toast toast = Toast.makeText(MainMenu.this, "Welcome back.", Toast.LENGTH_SHORT);
            toast.show();
        }

        else {
            Log.d("SharedPreferences", "No saved progress found, displaying initial set.");
            initialiseConstructor();

            Toast toast = Toast.makeText(MainMenu.this, "Welcome to Stratagem Hero.", Toast.LENGTH_SHORT);
            toast.show();
        }

        stratagemAdapter.notifyDataSetChanged();
        tvComboCounter.setText(String.format("%s: %s%n", getResources().getString(R.string.correctComboText), storeCount));

    }

    // override item click functionality context to send Intent data to Game.java
    @Override
    public void onItemClick(int position) {
        Log.i("Button clicked", "Clicked position " + position);
        Intent intent = new Intent(MainMenu.this, Game.class);

        intent.putExtra("key_stratagem_name", alStratagems.get(position).fetchStratagemName());
        intent.putExtra("key_stratagem_icon", alStratagems.get(position).fetchStratagemIcon());
        intent.putExtra("key_stratagem_color", alStratagems.get(position).fetchStratagemColor());
        intent.putExtra("key_stratagem_combo_images", alStratagems.get(position).fetchComboImages());
        intent.putExtra("key_stratagem_combo_count", alStratagems.get(position).fetchComboCount());
        intent.putExtra("key_stratagem_combo_patterns", alStratagems.get(position).fetchComboPattern());
        intent.putExtra("key_index_position", position);
        intent.putExtra("key_is_completed", alStratagems.get(position).fetchIsCompleted());

        launcher.launch(intent);
    }

    // call this method to initialise the constructor, useful especially whenever the user presses the restart button
    public void initialiseConstructor() {
        alStratagems.add(new Stratagem("REINFORCE", R.drawable.reinforce_icon, "#FFF5CB42",
                new int[]{R.drawable.arrow_up,
                        R.drawable.arrow_down,
                        R.drawable.arrow_right,
                        R.drawable.arrow_left,
                        R.drawable.arrow_up,
                        R.drawable.transparent,
                        R.drawable.transparent,
                        R.drawable.transparent},
                5, new ArrayList<>(Arrays.asList("U", "D", "R", "L", "U")), false));
        alStratagems.add(new Stratagem("RESUPPLY", R.drawable.resupply_icon, "#FFF5CB42",
                new int[]{R.drawable.arrow_down,
                        R.drawable.arrow_down,
                        R.drawable.arrow_up,
                        R.drawable.arrow_right,
                        R.drawable.transparent,
                        R.drawable.transparent,
                        R.drawable.transparent,
                        R.drawable.transparent,
                        R.drawable.transparent},
                4, new ArrayList<>(Arrays.asList("D", "D", "U", "R")), false));
        alStratagems.add(new Stratagem("EXPENDABLE ANTI TANK", R.drawable.expendable_anti_tank_icon, "#FF23B5CC",
                new int[]{R.drawable.arrow_down,
                        R.drawable.arrow_down,
                        R.drawable.arrow_left,
                        R.drawable.arrow_up,
                        R.drawable.arrow_right,
                        R.drawable.transparent,
                        R.drawable.transparent,
                        R.drawable.transparent},
                5, new ArrayList<>(Arrays.asList("D", "D", "L", "U", "R")), false));
        alStratagems.add(new Stratagem("EAGLE AIRSTRIKE", R.drawable.eagle_airstrike_icon, "#FFFF352E",
                new int[]{R.drawable.arrow_up,
                        R.drawable.arrow_right,
                        R.drawable.arrow_down,
                        R.drawable.arrow_right,
                        R.drawable.transparent,
                        R.drawable.transparent,
                        R.drawable.transparent,
                        R.drawable.transparent,
                        R.drawable.transparent},
                4, new ArrayList<>(Arrays.asList("U", "R", "D", "R")), false));
        alStratagems.add(new Stratagem("EAGLE 500KG BOMB", R.drawable.eagle_500kg_bomb_icon, "#FFFF352E",
                new int[]{R.drawable.arrow_up,
                        R.drawable.arrow_right,
                        R.drawable.arrow_down,
                        R.drawable.arrow_down,
                        R.drawable.arrow_down,
                        R.drawable.transparent,
                        R.drawable.transparent,
                        R.drawable.transparent},
                5, new ArrayList<>(Arrays.asList("U", "R", "D", "D", "D")), false));
        alStratagems.add(new Stratagem("HELLBOMB", R.drawable.hellbomb_icon, "#FFF5CB42",
                new int[]{R.drawable.arrow_down,
                        R.drawable.arrow_up,
                        R.drawable.arrow_left,
                        R.drawable.arrow_down,
                        R.drawable.arrow_up,
                        R.drawable.arrow_right,
                        R.drawable.arrow_down,
                        R.drawable.arrow_up},
                8, new ArrayList<>(Arrays.asList("D", "U", "L", "D", "U", "R", "D", "U")), false));

        Collections.shuffle(alStratagems);

        stratagemAdapter = new StratagemAdapter(alStratagems, this);
        rvStratagems.setAdapter(stratagemAdapter);
    }
}