package ru.aspectnet.hardware.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.aspectnet.hardware.HardwareApplication;
import ru.aspectnet.hardware.api.dto.task2.RequestDto;
import ru.aspectnet.hardware.api.dto.task2.ReturnValueHardwareInfoDto;
import ru.aspectnet.hardware.databinding.ActivityTask2Binding;

import ru.aspectnet.hardware.model.convert.HardwareInfoConverter;
import ru.aspectnet.hardware.model.data.Hardware;
import ru.aspectnet.hardware.model.data.HardwareInfo;
import ru.aspectnet.hardware.model.data.HardwarePackage;
import ru.aspectnet.hardware.view.block.HardwareInfoBlock;

public class Task2Activity extends AppCompatActivity {

    private ActivityTask2Binding binding;

    private HardwarePackage hp; // Объект с информацией о загруженном по REST оборудовании

    private Hardware h; // Объект с информацией об оборудовании

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTask2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        h = HardwareApplication.getInstance().getHardware();

        // загружаем данные по REST
        HardwareInfoBlock hib = new HardwareInfoBlock(binding.formHardwareInfo, h, this);
        hib.loadDataHardwareInfo();
    }

}