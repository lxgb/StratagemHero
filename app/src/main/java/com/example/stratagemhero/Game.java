package com.example.stratagemhero;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class Game extends AppCompatActivity {

    ArrayList<String> gameStoreCombo = new ArrayList<>();
    int gameStoreComboIndex = 0;
    String receivedPosition;
    AtomicBoolean isCompleted;
    int addCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_game);

        Bundle receivedBundle = getIntent().getExtras();
        TextView tvGameStratagemName;
        LinearLayout stratagemColorBarTop, stratagemColorBarBottom, gameComboContainer;
        ImageButton gameUpBtn, gameDownBtn, gameLeftBtn, gameRightBtn;
        ImageView gameComboBtn1,
                gameComboBtn2,
                gameComboBtn3,
                gameComboBtn4,
                gameComboBtn5,
                gameComboBtn6,
                gameComboBtn7,
                gameComboBtn8,
                gameStratagemIcon;

        tvGameStratagemName = findViewById(R.id.tvGameStratagemName);
        stratagemColorBarTop = findViewById(R.id.stratagemColorBarTop);
        stratagemColorBarBottom = findViewById(R.id.stratagemColorBarBottom);
        gameComboContainer = findViewById(R.id.gameComboContainer);
        gameStratagemIcon = findViewById(R.id.gameStratagemIcon);

        gameUpBtn = findViewById(R.id.gameUpBtn);
        gameDownBtn = findViewById(R.id.gameDownBtn);
        gameLeftBtn = findViewById(R.id.gameLeftBtn);
        gameRightBtn = findViewById(R.id.gameRightBtn);

        gameComboBtn1 = findViewById(R.id.gameComboBtn1);
        gameComboBtn2 = findViewById(R.id.gameComboBtn2);
        gameComboBtn3 = findViewById(R.id.gameComboBtn3);
        gameComboBtn4 = findViewById(R.id.gameComboBtn4);
        gameComboBtn5 = findViewById(R.id.gameComboBtn5);
        gameComboBtn6 = findViewById(R.id.gameComboBtn6);
        gameComboBtn7 = findViewById(R.id.gameComboBtn7);
        gameComboBtn8 = findViewById(R.id.gameComboBtn8);

        String stratagemName = receivedBundle.getString("key_stratagem_name");
        String stratagemColor = receivedBundle.getString("key_stratagem_color");
        int[] stratagemCombos = receivedBundle.getIntArray("key_stratagem_combo_images");
        int stratagemIcon = receivedBundle.getInt("key_stratagem_icon");
        int stratagemComboCount = receivedBundle.getInt("key_stratagem_combo_count");
        ArrayList<String> stratagemComboPatterns = receivedBundle.getStringArrayList("key_stratagem_combo_patterns");
        receivedPosition = Integer.toString(receivedBundle.getInt("key_index_position"));
        isCompleted = new AtomicBoolean(receivedBundle.getBoolean("key_is_completed", false));

        // check if ArrayList contents are properly received from the other activity
        Log.i("ArrayList", stratagemComboPatterns + " Count: " + stratagemComboPatterns.size());

        tvGameStratagemName.setText(stratagemName);
        stratagemColorBarTop.setBackgroundColor(Color.parseColor(stratagemColor));
        stratagemColorBarBottom.setBackgroundColor(Color.parseColor(stratagemColor));
        gameStratagemIcon.setImageResource(stratagemIcon);

        // handler for image display depending on the count of patterns that need to be met
        switch (stratagemComboCount) {
            case 1:
                gameComboBtn1.setImageResource(stratagemCombos[0]);
                gameComboContainer.removeView(gameComboBtn2);
                gameComboContainer.removeView(gameComboBtn3);
                gameComboContainer.removeView(gameComboBtn4);
                gameComboContainer.removeView(gameComboBtn5);
                gameComboContainer.removeView(gameComboBtn6);
                gameComboContainer.removeView(gameComboBtn7);
                gameComboContainer.removeView(gameComboBtn8);
            case 2:
                gameComboBtn1.setImageResource(stratagemCombos[0]);
                gameComboBtn2.setImageResource(stratagemCombos[1]);
                gameComboContainer.removeView(gameComboBtn3);
                gameComboContainer.removeView(gameComboBtn4);
                gameComboContainer.removeView(gameComboBtn5);
                gameComboContainer.removeView(gameComboBtn6);
                gameComboContainer.removeView(gameComboBtn7);
                gameComboContainer.removeView(gameComboBtn8);
            case 3:
                gameComboBtn1.setImageResource(stratagemCombos[0]);
                gameComboBtn2.setImageResource(stratagemCombos[1]);
                gameComboBtn3.setImageResource(stratagemCombos[2]);
                gameComboContainer.removeView(gameComboBtn4);
                gameComboContainer.removeView(gameComboBtn5);
                gameComboContainer.removeView(gameComboBtn6);
                gameComboContainer.removeView(gameComboBtn7);
                gameComboContainer.removeView(gameComboBtn8);
            case 4:
                gameComboBtn1.setImageResource(stratagemCombos[0]);
                gameComboBtn2.setImageResource(stratagemCombos[1]);
                gameComboBtn3.setImageResource(stratagemCombos[2]);
                gameComboBtn4.setImageResource(stratagemCombos[3]);
                gameComboContainer.removeView(gameComboBtn5);
                gameComboContainer.removeView(gameComboBtn6);
                gameComboContainer.removeView(gameComboBtn7);
                gameComboContainer.removeView(gameComboBtn8);
            case 5:
                gameComboBtn1.setImageResource(stratagemCombos[0]);
                gameComboBtn2.setImageResource(stratagemCombos[1]);
                gameComboBtn3.setImageResource(stratagemCombos[2]);
                gameComboBtn4.setImageResource(stratagemCombos[3]);
                gameComboBtn5.setImageResource(stratagemCombos[4]);
                gameComboContainer.removeView(gameComboBtn6);
                gameComboContainer.removeView(gameComboBtn7);
                gameComboContainer.removeView(gameComboBtn8);
            case 6:
                gameComboBtn1.setImageResource(stratagemCombos[0]);
                gameComboBtn2.setImageResource(stratagemCombos[1]);
                gameComboBtn3.setImageResource(stratagemCombos[2]);
                gameComboBtn4.setImageResource(stratagemCombos[3]);
                gameComboBtn5.setImageResource(stratagemCombos[4]);
                gameComboBtn6.setImageResource(stratagemCombos[5]);
                gameComboContainer.removeView(gameComboBtn7);
                gameComboContainer.removeView(gameComboBtn8);
            case 7:
                gameComboBtn1.setImageResource(stratagemCombos[0]);
                gameComboBtn2.setImageResource(stratagemCombos[1]);
                gameComboBtn3.setImageResource(stratagemCombos[2]);
                gameComboBtn4.setImageResource(stratagemCombos[3]);
                gameComboBtn5.setImageResource(stratagemCombos[4]);
                gameComboBtn6.setImageResource(stratagemCombos[5]);
                gameComboBtn7.setImageResource(stratagemCombos[6]);
                gameComboContainer.removeView(gameComboBtn8);
            case 8:
                gameComboBtn1.setImageResource(stratagemCombos[0]);
                gameComboBtn2.setImageResource(stratagemCombos[1]);
                gameComboBtn3.setImageResource(stratagemCombos[2]);
                gameComboBtn4.setImageResource(stratagemCombos[3]);
                gameComboBtn5.setImageResource(stratagemCombos[4]);
                gameComboBtn6.setImageResource(stratagemCombos[5]);
                gameComboBtn7.setImageResource(stratagemCombos[6]);
                gameComboBtn8.setImageResource(stratagemCombos[7]);
        }

        // up button input handler
        gameUpBtn.setOnClickListener(v -> {
            gameStoreCombo.add("U");
            if (gameUpBtn.isPressed()) {
                try {
                    switch (gameStoreComboIndex) {
                        case 0:
                            if (!Objects.equals(gameStoreCombo.get(0), stratagemComboPatterns.get(0))) {
                                gameComboBtn1.setColorFilter(Color.parseColor("#fff54242")); // red
                            } else {
                                gameComboBtn1.setColorFilter(Color.parseColor("#ff48f542")); // green
                            }
                            gameStoreComboIndex++;
                            break;
                        case 1:
                            if (!Objects.equals(gameStoreCombo.get(1), stratagemComboPatterns.get(1))) {
                                gameComboBtn2.setColorFilter(Color.parseColor("#fff54242")); // red
                            } else {
                                gameComboBtn2.setColorFilter(Color.parseColor("#ff48f542")); // green
                            }
                            gameStoreComboIndex++;
                            break;
                        case 2:
                            if (!Objects.equals(gameStoreCombo.get(2), stratagemComboPatterns.get(2))) {
                                gameComboBtn3.setColorFilter(Color.parseColor("#fff54242")); // red
                            } else {
                                gameComboBtn3.setColorFilter(Color.parseColor("#ff48f542")); // green
                            }
                            gameStoreComboIndex++;
                            break;
                        case 3:
                            if (!Objects.equals(gameStoreCombo.get(3), stratagemComboPatterns.get(3))) {
                                gameComboBtn4.setColorFilter(Color.parseColor("#fff54242")); // red
                            } else {
                                gameComboBtn4.setColorFilter(Color.parseColor("#ff48f542")); // green
                            }
                            gameStoreComboIndex++;
                            break;
                        case 4:
                            if (!Objects.equals(gameStoreCombo.get(4), stratagemComboPatterns.get(4))) {
                                gameComboBtn5.setColorFilter(Color.parseColor("#fff54242")); // red
                            } else {
                                gameComboBtn5.setColorFilter(Color.parseColor("#ff48f542")); // green
                            }
                            gameStoreComboIndex++;
                            break;
                        case 5:
                            if (!Objects.equals(gameStoreCombo.get(5), stratagemComboPatterns.get(5))) {
                                gameComboBtn6.setColorFilter(Color.parseColor("#fff54242")); // red
                            } else {
                                gameComboBtn6.setColorFilter(Color.parseColor("#ff48f542")); // green
                            }
                            gameStoreComboIndex++;
                            break;
                        case 6:
                            if (!Objects.equals(gameStoreCombo.get(6), stratagemComboPatterns.get(6))) {
                                gameComboBtn7.setColorFilter(Color.parseColor("#fff54242")); // red
                            } else {
                                gameComboBtn7.setColorFilter(Color.parseColor("#ff48f542")); // green
                            }
                            gameStoreComboIndex++;
                            break;
                        case 7:
                            if (!Objects.equals(gameStoreCombo.get(7), stratagemComboPatterns.get(7))) {
                                gameComboBtn8.setColorFilter(Color.parseColor("#fff54242")); // red
                            } else {
                                gameComboBtn8.setColorFilter(Color.parseColor("#ff48f542")); // green
                            }
                            gameStoreComboIndex++;
                            break;
                    }
                    Log.i("gameStoreComboIndex", "Index Count: " + gameStoreComboIndex);

                    if (gameStoreComboIndex == stratagemComboCount && stratagemComboPatterns.equals(gameStoreCombo) && !isCompleted.get()) {
                        Log.i("Game", "User successfully matched the given patterns.");
                        // temporarily display finished stratagem combos as green
                        isCompleted.set(true);
                        addCount++;
                        processIntent();
                    }

                    else if (gameStoreComboIndex == stratagemComboCount && !stratagemComboPatterns.equals(gameStoreCombo) && isCompleted.get()) {
                        Log.i("Game", "User failed to match the patterns but is already marked completed.");
                        processIntent();
                    }

                    else if (gameStoreComboIndex == stratagemComboCount && stratagemComboPatterns.equals(gameStoreCombo) && isCompleted.get()) {
                        Log.i("Game", "User already matched the given patterns, marked as completed.");
                        processIntent();
                    }

                    else if (gameStoreComboIndex == stratagemComboCount && !stratagemComboPatterns.equals(gameStoreCombo) && !isCompleted.get()) {
                        Log.i("Game", "User failed to match some portions of the given pattern.");
                        // temporarily otherwise, display red
                        isCompleted.set(false);
                        processIntent();
                    }
                }

                catch (IndexOutOfBoundsException e) {
                    Log.e("IndexOutOfBoundsException", "Index is not found or gameStoreComboIndex is calling index beyond ArrayList length.");
                }
            }
        });

        // down button input handler
        gameDownBtn.setOnClickListener(v -> {
            gameStoreCombo.add("D");
            if (gameDownBtn.isPressed()) {
                try {
                    switch (gameStoreComboIndex) {
                        case 0:
                            if (!Objects.equals(gameStoreCombo.get(0), stratagemComboPatterns.get(0))) {
                                gameComboBtn1.setColorFilter(Color.parseColor("#fff54242")); // red
                            } else {
                                gameComboBtn1.setColorFilter(Color.parseColor("#ff48f542")); // green
                            }
                            gameStoreComboIndex++;
                            break;
                        case 1:
                            if (!Objects.equals(gameStoreCombo.get(1), stratagemComboPatterns.get(1))) {
                                gameComboBtn2.setColorFilter(Color.parseColor("#fff54242")); // red
                            } else {
                                gameComboBtn2.setColorFilter(Color.parseColor("#ff48f542")); // green
                            }
                            gameStoreComboIndex++;
                            break;
                        case 2:
                            if (!Objects.equals(gameStoreCombo.get(2), stratagemComboPatterns.get(2))) {
                                gameComboBtn3.setColorFilter(Color.parseColor("#fff54242")); // red
                            } else {
                                gameComboBtn3.setColorFilter(Color.parseColor("#ff48f542")); // green
                            }
                            gameStoreComboIndex++;
                            break;
                        case 3:
                            if (!Objects.equals(gameStoreCombo.get(3), stratagemComboPatterns.get(3))) {
                                gameComboBtn4.setColorFilter(Color.parseColor("#fff54242")); // red
                            } else {
                                gameComboBtn4.setColorFilter(Color.parseColor("#ff48f542")); // green
                            }
                            gameStoreComboIndex++;
                            break;
                        case 4:
                            if (!Objects.equals(gameStoreCombo.get(4), stratagemComboPatterns.get(4))) {
                                gameComboBtn5.setColorFilter(Color.parseColor("#fff54242")); // red
                            } else {
                                gameComboBtn5.setColorFilter(Color.parseColor("#ff48f542")); // green
                            }
                            gameStoreComboIndex++;
                            break;
                        case 5:
                            if (!Objects.equals(gameStoreCombo.get(5), stratagemComboPatterns.get(5))) {
                                gameComboBtn6.setColorFilter(Color.parseColor("#fff54242")); // red
                            } else {
                                gameComboBtn6.setColorFilter(Color.parseColor("#ff48f542")); // green
                            }
                            gameStoreComboIndex++;
                            break;
                        case 6:
                            if (!Objects.equals(gameStoreCombo.get(6), stratagemComboPatterns.get(6))) {
                                gameComboBtn7.setColorFilter(Color.parseColor("#fff54242")); // red
                            } else {
                                gameComboBtn7.setColorFilter(Color.parseColor("#ff48f542")); // green
                            }
                            gameStoreComboIndex++;
                            break;
                        case 7:
                            if (!Objects.equals(gameStoreCombo.get(7), stratagemComboPatterns.get(7))) {
                                gameComboBtn8.setColorFilter(Color.parseColor("#fff54242")); // red
                            } else {
                                gameComboBtn8.setColorFilter(Color.parseColor("#ff48f542")); // green
                            }
                            gameStoreComboIndex++;
                            break;
                    }
                    Log.i("gameStoreComboIndex", "Index Count: " + gameStoreComboIndex);

                    if (gameStoreComboIndex == stratagemComboCount && stratagemComboPatterns.equals(gameStoreCombo) && !isCompleted.get()) {
                        Log.i("Game", "User successfully matched the given patterns.");
                        // temporarily display finished stratagem combos as green
                        isCompleted.set(true);
                        addCount++;
                        processIntent();
                    }

                    else if (gameStoreComboIndex == stratagemComboCount && !stratagemComboPatterns.equals(gameStoreCombo) && isCompleted.get()) {
                        Log.i("Game", "User failed to match the patterns but is already marked completed.");
                        processIntent();
                    }

                    else if (gameStoreComboIndex == stratagemComboCount && stratagemComboPatterns.equals(gameStoreCombo) && isCompleted.get()) {
                        Log.i("Game", "User already matched the given patterns, marked as completed.");
                        processIntent();
                    }

                    else if (gameStoreComboIndex == stratagemComboCount && !stratagemComboPatterns.equals(gameStoreCombo) && !isCompleted.get()) {
                        Log.i("Game", "User failed to match some portions of the given pattern.");
                        // temporarily otherwise, display red
                        isCompleted.set(false);
                        processIntent();
                    }

                }

                catch (IndexOutOfBoundsException e) {
                    Log.e("IndexOutOfBoundsException", "Index is not found or gameStoreComboIndex is calling index beyond ArrayList length.");
                }
            }
        });

        // left button input handler
        gameLeftBtn.setOnClickListener(v -> {
            gameStoreCombo.add("L");
            if (gameLeftBtn.isPressed()) {
                try {
                    switch (gameStoreComboIndex) {
                        case 0:
                            if (!Objects.equals(gameStoreCombo.get(0), stratagemComboPatterns.get(0))) {
                                gameComboBtn1.setColorFilter(Color.parseColor("#fff54242")); // red
                            } else {
                                gameComboBtn1.setColorFilter(Color.parseColor("#ff48f542")); // green
                            }
                            gameStoreComboIndex++;
                            break;
                        case 1:
                            if (!Objects.equals(gameStoreCombo.get(1), stratagemComboPatterns.get(1))) {
                                gameComboBtn2.setColorFilter(Color.parseColor("#fff54242")); // red
                            } else {
                                gameComboBtn2.setColorFilter(Color.parseColor("#ff48f542")); // green
                            }
                            gameStoreComboIndex++;
                            break;
                        case 2:
                            if (!Objects.equals(gameStoreCombo.get(2), stratagemComboPatterns.get(2))) {
                                gameComboBtn3.setColorFilter(Color.parseColor("#fff54242")); // red
                            } else {
                                gameComboBtn3.setColorFilter(Color.parseColor("#ff48f542")); // green
                            }
                            gameStoreComboIndex++;
                            break;
                        case 3:
                            if (!Objects.equals(gameStoreCombo.get(3), stratagemComboPatterns.get(3))) {
                                gameComboBtn4.setColorFilter(Color.parseColor("#fff54242")); // red
                            } else {
                                gameComboBtn4.setColorFilter(Color.parseColor("#ff48f542")); // green
                            }
                            gameStoreComboIndex++;
                            break;
                        case 4:
                            if (!Objects.equals(gameStoreCombo.get(4), stratagemComboPatterns.get(4))) {
                                gameComboBtn5.setColorFilter(Color.parseColor("#fff54242")); // red
                            } else {
                                gameComboBtn5.setColorFilter(Color.parseColor("#ff48f542")); // green
                            }
                            gameStoreComboIndex++;
                            break;
                        case 5:
                            if (!Objects.equals(gameStoreCombo.get(5), stratagemComboPatterns.get(5))) {
                                gameComboBtn6.setColorFilter(Color.parseColor("#fff54242")); // red
                            } else {
                                gameComboBtn6.setColorFilter(Color.parseColor("#ff48f542")); // green
                            }
                            gameStoreComboIndex++;
                            break;
                        case 6:
                            if (!Objects.equals(gameStoreCombo.get(6), stratagemComboPatterns.get(6))) {
                                gameComboBtn7.setColorFilter(Color.parseColor("#fff54242")); // red
                            } else {
                                gameComboBtn7.setColorFilter(Color.parseColor("#ff48f542")); // green
                            }
                            gameStoreComboIndex++;
                            break;
                        case 7:
                            if (!Objects.equals(gameStoreCombo.get(7), stratagemComboPatterns.get(7))) {
                                gameComboBtn8.setColorFilter(Color.parseColor("#fff54242")); // red
                            } else {
                                gameComboBtn8.setColorFilter(Color.parseColor("#ff48f542")); // green
                            }
                            gameStoreComboIndex++;
                            break;
                    }
                    Log.i("gameStoreComboIndex", "Index Count: " + gameStoreComboIndex);

                    if (gameStoreComboIndex == stratagemComboCount && stratagemComboPatterns.equals(gameStoreCombo) && !isCompleted.get()) {
                        Log.i("Game", "User successfully matched the given patterns.");
                        // temporarily display finished stratagem combos as green
                        isCompleted.set(true);
                        addCount++;
                        processIntent();
                    }

                    else if (gameStoreComboIndex == stratagemComboCount && !stratagemComboPatterns.equals(gameStoreCombo) && isCompleted.get()) {
                        Log.i("Game", "User failed to match the patterns but is already marked completed.");
                        processIntent();
                    }

                    else if (gameStoreComboIndex == stratagemComboCount && stratagemComboPatterns.equals(gameStoreCombo) && isCompleted.get()) {
                        Log.i("Game", "User already matched the given patterns, marked as completed.");
                        processIntent();
                    }

                    else if (gameStoreComboIndex == stratagemComboCount && !stratagemComboPatterns.equals(gameStoreCombo) && !isCompleted.get()) {
                        Log.i("Game", "User failed to match some portions of the given pattern.");
                        // temporarily otherwise, display red
                        isCompleted.set(false);
                        processIntent();
                    }
                }

                catch (IndexOutOfBoundsException e) {
                    Log.e("IndexOutOfBoundsException", "Index is not found or gameStoreComboIndex is calling index beyond ArrayList length.");
                }
            }
        });

        // right button input handler
        gameRightBtn.setOnClickListener(v -> {
            gameStoreCombo.add("R");
            if (gameRightBtn.isPressed()) {
                try {
                    switch (gameStoreComboIndex) {
                        case 0:
                            if (!Objects.equals(gameStoreCombo.get(0), stratagemComboPatterns.get(0))) {
                                gameComboBtn1.setColorFilter(Color.parseColor("#fff54242")); // red
                            } else {
                                gameComboBtn1.setColorFilter(Color.parseColor("#ff48f542")); // green
                            }
                            gameStoreComboIndex++;
                            break;
                        case 1:
                            if (!Objects.equals(gameStoreCombo.get(1), stratagemComboPatterns.get(1))) {
                                gameComboBtn2.setColorFilter(Color.parseColor("#fff54242")); // red
                            } else {
                                gameComboBtn2.setColorFilter(Color.parseColor("#ff48f542")); // green
                            }
                            gameStoreComboIndex++;
                            break;
                        case 2:
                            if (!Objects.equals(gameStoreCombo.get(2), stratagemComboPatterns.get(2))) {
                                gameComboBtn3.setColorFilter(Color.parseColor("#fff54242")); // red
                            } else {
                                gameComboBtn3.setColorFilter(Color.parseColor("#ff48f542")); // green
                            }
                            gameStoreComboIndex++;
                            break;
                        case 3:
                            if (!Objects.equals(gameStoreCombo.get(3), stratagemComboPatterns.get(3))) {
                                gameComboBtn4.setColorFilter(Color.parseColor("#fff54242")); // red
                            } else {
                                gameComboBtn4.setColorFilter(Color.parseColor("#ff48f542")); // green
                            }
                            gameStoreComboIndex++;
                            break;
                        case 4:
                            if (!Objects.equals(gameStoreCombo.get(4), stratagemComboPatterns.get(4))) {
                                gameComboBtn5.setColorFilter(Color.parseColor("#fff54242")); // red
                            } else {
                                gameComboBtn5.setColorFilter(Color.parseColor("#ff48f542")); // green
                            }
                            gameStoreComboIndex++;
                            break;
                        case 5:
                            if (!Objects.equals(gameStoreCombo.get(5), stratagemComboPatterns.get(5))) {
                                gameComboBtn6.setColorFilter(Color.parseColor("#fff54242")); // red
                            } else {
                                gameComboBtn6.setColorFilter(Color.parseColor("#ff48f542")); // green
                            }
                            gameStoreComboIndex++;
                            break;
                        case 6:
                            if (!Objects.equals(gameStoreCombo.get(6), stratagemComboPatterns.get(6))) {
                                gameComboBtn7.setColorFilter(Color.parseColor("#fff54242")); // red
                            } else {
                                gameComboBtn7.setColorFilter(Color.parseColor("#ff48f542")); // green
                            }
                            gameStoreComboIndex++;
                            break;
                        case 7:
                            if (!Objects.equals(gameStoreCombo.get(7), stratagemComboPatterns.get(7))) {
                                gameComboBtn8.setColorFilter(Color.parseColor("#fff54242")); // red
                            } else {
                                gameComboBtn8.setColorFilter(Color.parseColor("#ff48f542")); // green
                            }
                            gameStoreComboIndex++;
                            break;
                    }
                    Log.i("gameStoreComboIndex", "Index Count: " + gameStoreComboIndex);

                    if (gameStoreComboIndex == stratagemComboCount && stratagemComboPatterns.equals(gameStoreCombo) && !isCompleted.get()) {
                        Log.i("Game", "User successfully matched the given patterns.");
                        // temporarily display finished stratagem combos as green
                        isCompleted.set(true);
                        addCount++;
                        processIntent();
                    }

                    else if (gameStoreComboIndex == stratagemComboCount && !stratagemComboPatterns.equals(gameStoreCombo) && isCompleted.get()) {
                        Log.i("Game", "User failed to match the patterns but is already marked completed.");
                        processIntent();
                    }

                    else if (gameStoreComboIndex == stratagemComboCount && stratagemComboPatterns.equals(gameStoreCombo) && isCompleted.get()) {
                        Log.i("Game", "User already matched the given patterns, marked as completed.");
                        processIntent();
                    }

                    else if (gameStoreComboIndex == stratagemComboCount && !stratagemComboPatterns.equals(gameStoreCombo) && !isCompleted.get()) {
                        Log.i("Game", "User failed to match some portions of the given pattern.");
                        // temporarily otherwise, display red
                        isCompleted.set(false);
                        processIntent();
                    }
                }

                catch (IndexOutOfBoundsException e) {
                    Log.e("IndexOutOfBoundsException", "Index is not found or gameStoreComboIndex is calling index beyond ArrayList length.");
                }
            }
        });
    }

    // handle content delivery to other activity by calling an intent method
    public void processIntent() {
        Boolean parseBoolean = Boolean.parseBoolean(String.valueOf(isCompleted));

        Log.i("Finish", "Requirements met, finishing activity. Returning user back to MainMenu");
        Intent intent = new Intent();
        intent.putExtra("key_return_index", receivedPosition);
        intent.putExtra("key_return_completion", parseBoolean);
        intent.putExtra("key_add_count", addCount);

        Log.i("isCompleted (Game.java)", String.valueOf(parseBoolean));

        setResult(RESULT_OK, intent);
        finish();
    }
}