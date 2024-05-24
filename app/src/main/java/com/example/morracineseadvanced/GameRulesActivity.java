package com.example.morracineseadvanced;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class GameRulesActivity extends AppCompatActivity {

    private Interactions interactions;
    private ViewPager2 viewPagerRules;
    private ImagePagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gamerules);

        interactions = new Interactions();
        List<Integer>imageRules = new ArrayList<>();
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

        Button btn_play = findViewById(R.id.button_play);
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameRulesActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
    }
}