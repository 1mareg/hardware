package ru.aspectnet.hardware.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;

import ru.aspectnet.hardware.HardwareApplication;
import ru.aspectnet.hardware.databinding.ActivityTask1Binding;

import ru.aspectnet.hardware.R;
import ru.aspectnet.hardware.view.block.HardwareBlock;
import ru.aspectnet.hardware.view.block.OnTableRowClick;

/*
    Активити для отображения таблицы с оборудованием
 */
public class Task1Activity extends AppCompatActivity {

    private ActivityTask1Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTask1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // лямбда с операциями, выполняющимися после нажатия на строку таблицы
        OnTableRowClick otrc = (h, ctx) -> {
            HardwareApplication.getInstance().setHardware(h);
            Intent intent = new Intent(ctx, Task2Activity.class);
            ctx.startActivity(intent);
        };

        HardwareBlock hb = new HardwareBlock(binding.progressBar,
                binding.tableHardware,
                R.layout.task1_table_row,
                otrc,
                this);
        // включаем возможность фильтрации
        hb.setFilterOn();
        // загружаем данные по REST
        hb.loadDataHardware();


        // Слушатель изменений в полях ввода для фильтрации данных
        TextWatcher tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            /*
                После изменения данных в поле ввода автоматически производим фильтрацию
             */
            @Override
            public void afterTextChanged(Editable s) {
                hb.filterTable();
            }
        };

        // устанавливаем слушатели на все поля ввода
        binding.editTextF1.addTextChangedListener(tw);
        binding.editTextF2.addTextChangedListener(tw);
        binding.editTextF3.addTextChangedListener(tw);
        binding.editTextF4.addTextChangedListener(tw);

    }

}