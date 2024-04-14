package ru.aspectnet.hardware.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import ru.aspectnet.hardware.R;
import ru.aspectnet.hardware.databinding.ActivityTask3Binding;
import ru.aspectnet.hardware.view.block.HardwareBlock;
import ru.aspectnet.hardware.view.block.HardwareInfoBlock;
import ru.aspectnet.hardware.view.block.OnTableRowClick;

public class Task3Activity extends AppCompatActivity {

    private ActivityTask3Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTask3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // лямбда с операциями, выполняющимися после нажатия на строку таблицы
        OnTableRowClick otrc = (h, ctx) -> {
            // скрываем слой-заглушку
            binding.linearLayoutStubColumn.setVisibility(View.INVISIBLE);
            binding.linearLayoutStubColumn.setLayoutParams(new LinearLayout.LayoutParams(0, 0));

            HardwareInfoBlock hib = new HardwareInfoBlock(binding.formHardwareInfo, h, this);
            hib.loadDataHardwareInfo();
        };
        HardwareBlock hb = new HardwareBlock(binding.preloader.progressBar,
                binding.tableHardware,
                R.layout.task3_table_row,
                otrc,
                this);
        // загружаем данные по REST
        hb.loadDataHardware();
    }

}