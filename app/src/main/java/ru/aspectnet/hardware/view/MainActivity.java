package ru.aspectnet.hardware.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import ru.aspectnet.hardware.R;
import ru.aspectnet.hardware.databinding.ActivityMainBinding;

import android.view.MenuItem;

/*
    Основное активити приложения
 */

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        // слушатель нажатия на кнопку вызывает новое активити с перечнем оборудования
        binding.buttonTask1.setOnClickListener((b) -> {
            Intent intent = new Intent(this, Task1Activity.class);
            this.startActivity(intent);
        });

    }

}