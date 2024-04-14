package ru.aspectnet.hardware.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import ru.aspectnet.hardware.HardwareApplication;
import ru.aspectnet.hardware.databinding.ActivityTask2Binding;

import ru.aspectnet.hardware.model.data.Hardware;
import ru.aspectnet.hardware.view.block.HardwareInfoBlock;

/*
    Активити для отображения карточки оборудования
 */

public class Task2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityTask2Binding binding = ActivityTask2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Hardware h = HardwareApplication.getInstance().getHardware();

        // загружаем данные по REST
        HardwareInfoBlock hib = new HardwareInfoBlock(binding.formHardwareInfo, h, this);
        hib.loadDataHardwareInfo();
    }

}