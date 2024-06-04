package com.example.morracineseadvanced;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameRulesActivity extends AppCompatActivity {

    private ViewPager2 viewPagerRules;
    private ImagePagerAdapter adapter;
    private Interactions interactions;
    private TextView txtWin;
    private TextView txtLose;

    private Map<Integer, String> winRules;
    private Map<Integer, String> loseRules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gamerules);

        Button btn_play = findViewById(R.id.button_play);
        Button btn_back = findViewById(R.id.backButton);

        interactions = new Interactions();
        viewPagerRules = findViewById(R.id.viewPager_rules);
        List<Integer> imageRules = new ArrayList<>();
        imageRules.add(R.drawable.rock);
        imageRules.add(R.drawable.fire);
        imageRules.add(R.drawable.scissors);
        imageRules.add(R.drawable.snake);
        imageRules.add(R.drawable.human);
        imageRules.add(R.drawable.tree);
        imageRules.add(R.drawable.wolf);
        imageRules.add(R.drawable.sponge);
        imageRules.add(R.drawable.paper);
        imageRules.add(R.drawable.air);
        imageRules.add(R.drawable.water);
        imageRules.add(R.drawable.dragon);
        imageRules.add(R.drawable.devil);
        imageRules.add(R.drawable.lightning);
        imageRules.add(R.drawable.gun);

        adapter = new ImagePagerAdapter(this, imageRules);
        viewPagerRules.setAdapter(adapter);
        txtWin = findViewById(R.id.textView_Win);
        txtLose = findViewById(R.id.textView_Lose);

        rules();

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameRulesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameRulesActivity.this, PreGameActivity.class);
                startActivity(intent);
            }
        });

        viewPagerRules.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                int currentImage = imageRules.get(position);
                txtWin.setText(winRules.get(currentImage));
                txtLose.setText(loseRules.get(currentImage));
            }
        });
    }

    public void rules() {
        winRules = new HashMap<>();
        loseRules = new HashMap<>();

        winRules.put(R.drawable.rock, "Forbici, Fuoco, Serpente, Umano, Albero, Lupo, Spugna");
        loseRules.put(R.drawable.rock, "Pistola, Fulmine, Diavolo, Er Dragone, Acqua, Aria, Carta");

        winRules.put(R.drawable.fire, "Serpente, Umano, Albero, Lupo, Spugna, Carta, Aria");
        loseRules.put(R.drawable.fire, "Forbici, Sasso, Pistola, Fulmine, Diavolo, Er Dragone, Acqua");

        winRules.put(R.drawable.scissors, "Fuoco, Serpente, Umano, Albero, Lupo, Spugna, Carta");
        loseRules.put(R.drawable.scissors, "Sasso, Pistola, Fulmine, Diavolo, Er Dragone, Acqua, Aria");

        winRules.put(R.drawable.snake, "Umano, Albero, Lupo, Spugna, Carta, Aria, Acqua");
        loseRules.put(R.drawable.snake, "Fuoco, Forbici, Sasso, Pistola, Fulmine, Diavolo, Er Dragone");

        winRules.put(R.drawable.human, "Albero, Lupo, Spugna, Carta, Aria, Acqua, Er Dragone");
        loseRules.put(R.drawable.human, "Serpente, Fuoco, Forbici, Sasso, Pistola, Fulmine, Diavolo");

        winRules.put(R.drawable.tree, "Umano, Albero, Lupo, Spugna, Carta, Aria, Acqua");
        loseRules.put(R.drawable.tree, "Fuoco, Forbici, Sasso, Pistola, Fulmine, Diavolo, Er Dragone");

        winRules.put(R.drawable.wolf, "Spugna, Carta, Aria, Acqua, Er Dragone, Diavolo, Fulmine");
        loseRules.put(R.drawable.wolf, "Albero, Umano, Serpente, Fuoco, Forbici, Sasso, Pistola");

        winRules.put(R.drawable.sponge, "Carta, Aria, Acqua, Er Dragone, Diavolo, Fulmine, Pistola");
        loseRules.put(R.drawable.sponge, "Lupo, Albero, Umano, Serpente, Fuoco, Forbici, Sasso");

        winRules.put(R.drawable.paper, "Aria, Acqua, Er Dragone, Diavolo, Fulmine, Pistola, Sasso");
        loseRules.put(R.drawable.paper, "Spugna, Lupo, Albero, Umano, Serpente, Fuoco, Forbici");

        winRules.put(R.drawable.air, "Acqua, Er Dragone, Diavolo, Fulmine, Pistola, Sasso, Forbici");
        loseRules.put(R.drawable.air, "Carta, Spugna, Lupo, Albero, Umano, Serpente, Fuoco");

        winRules.put(R.drawable.water, "Er Dragone, Diavolo, Fulmine, Pistola, Sasso, Forbici, Fuoco");
        loseRules.put(R.drawable.water, "Aria, Carta, Spugna, Lupo, Albero, Umano, Serpente");

        winRules.put(R.drawable.dragon, "Diavolo, Fulmine, Pistola, Sasso, Forbici, Fuoco, Serpente");
        loseRules.put(R.drawable.dragon, "Acqua, Aria, Carta, Spugna, Lupo, Albero, Umano");

        winRules.put(R.drawable.devil, "Fulmine, Pistola, Sasso, Forbici, Fuoco, Serpente, Umano");
        loseRules.put(R.drawable.devil, "Er Dragone, Acqua, Aria, Carta, Spugna, Lupo, Albero");

        winRules.put(R.drawable.lightning, "Pistola, Sasso, Forbici, Fuoco, Serpente, Umano, Albero");
        loseRules.put(R.drawable.lightning, "Diavolo, Er Dragone, Acqua, Aria, Carta, Spugna, Lupo");

        winRules.put(R.drawable.gun, "Sasso, Forbici, Fuoco, Serpente, Umano, Albero, Lupo");
        loseRules.put(R.drawable.gun, "Fulmine, Diavolo, Er Dragone, Acqua, Aria, Carta, Spugna");
    }
}
















