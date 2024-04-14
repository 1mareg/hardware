package ru.aspectnet.hardware.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import ru.aspectnet.hardware.HardwareApplication;
import ru.aspectnet.hardware.databinding.ActivityMainBinding;
import ru.aspectnet.hardware.model.data.Hardware;

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

        // слушатель нажатия на кнопку вызывает новое активити с перечнем оборудования
        binding.buttonTask1.setOnClickListener((b) -> {
            Intent intent = new Intent(this, Task1Activity.class);
            this.startActivity(intent);
        });

        binding.buttonTask2.setOnClickListener((b) -> {

            // данные для загрузки в задании №2 без привзяки к активити задания №1
            Hardware h = new Hardware();
            h.setId("65b7cb09-5796-4317-b1a0-2124ded23af0");
            h.setCode("101014");
            h.setName("Конвейер ленточный №4 59747 (отсев ≥12мм) EP 400, B-500, L=19,6 м");
            h.setStatusCode("installed");
            h.setStatus("В эксплуатации");
            h.setCriticalityCode("1");
            h.setCriticality("Очень критично");
            HardwareApplication.getInstance().setHardware(h);

            Intent intent = new Intent(this, Task2Activity.class);
            this.startActivity(intent);
        });

        binding.buttonTask3.setOnClickListener((b) -> {

            // данные для загрузки в задании №2 без привзяки к активити задания №1
            Hardware h = new Hardware();
            h.setId("65b7cb09-5796-4317-b1a0-2124ded23af0");
            h.setCode("101014");
            h.setName("Конвейер ленточный №4 59747 (отсев ≥12мм) EP 400, B-500, L=19,6 м");
            h.setStatusCode("installed");
            h.setStatus("В эксплуатации");
            h.setCriticalityCode("1");
            h.setCriticality("Очень критично");
            HardwareApplication.getInstance().setHardware(h);

            Intent intent = new Intent(this, Task3Activity.class);
            this.startActivity(intent);
        });

    }

}