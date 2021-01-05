package com.myapp.lovetest_azuredragon3000.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.myapp.lovetest_azuredragon3000.R;
import com.myapp.lovetest_azuredragon3000.minigame.Minigame;

public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.main2);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Minigame.class);
            MainActivity.this.startActivity(intent);

        });

    }
}