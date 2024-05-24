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

        adapter = new ImagePagerAdapter(this,imageRules);
        viewPagerRules.setAdapter(adapter);

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameRulesActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
    }

    public void rules(){
        winRules = new HashMap<>();
        loseRules = new HashMap<>();

        winRules.put(R.drawable.rock, "Forbici, Fuoco, Serpente, Umano, Albero, Lupo, Spugna");
        loseRules.put(R.drawable.rock, "Pistola, Fulmine, Diavolo, Er Dragone, Acqua, Aria, Carta");

        winRules.put(R.drawable.fire, "Serpente, Umano, Albero, Lupo, Spugna, Carta, Aria");
        loseRules.put(R.drawable.fire,"Forbici, Sasso, Pistola, Fulmine, Diavolo, Er Dragone, Acqua");
    }
}