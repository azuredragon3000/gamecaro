package com.myapp.lovetest_azuredragon3001.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.myapp.lovetest_azuredragon3001.R;
import com.myapp.lovetest_azuredragon3001.minigame.Minigame;
import com.myapp.lovetest_azuredragon3001.minigame.Minigame2;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.main2);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Minigame.class);
            MainActivity.this.startActivity(intent);

        });

        Button button1 = findViewById(R.id.main1);
        button1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Minigame2.class);
            MainActivity.this.startActivity(intent);

        });

    }
}