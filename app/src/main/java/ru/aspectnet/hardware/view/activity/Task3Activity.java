package ru.aspectnet.hardware.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.aspectnet.hardware.HardwareApplication;
import ru.aspectnet.hardware.R;
import ru.aspectnet.hardware.api.dto.task1.HardwareDto;
import ru.aspectnet.hardware.api.dto.task1.ReturnValueDto;
import ru.aspectnet.hardware.databinding.ActivityTask3Binding;
import ru.aspectnet.hardware.model.convert.HardwareConverter;
import ru.aspectnet.hardware.model.data.Hardware;
import ru.aspectnet.hardware.model.data.HardwarePackage;
import ru.aspectnet.hardware.view.block.HardwareInfoBlock;

public class Task3Activity extends AppCompatActivity {

    private ActivityTask3Binding binding;

    private HardwarePackage hp; // Объект с информацией о загруженном по REST оборудовании


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTask3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // загружаем данные по REST
        loadDataHardware();
    }

    private void loadDataHardware() {

        binding.progressBar.setVisibility(View.VISIBLE);

        Call<ReturnValueDto> returnValue = HardwareApplication.getInstance().getReturnValueApi().returnValue();

        returnValue.enqueue(new Callback<ReturnValueDto>() {
            @Override
            public void onResponse(Call<ReturnValueDto> call, Response<ReturnValueDto> response) {
                if (response.isSuccessful()) {
                    ReturnValueDto rvd = response.body();
                    hp = new HardwarePackage();
                    for (HardwareDto hd : rvd.getReturnValue()) {
                        hp.setHardware(new HardwareConverter().convert(hd));
                    }
                    displayHardwareTable();
                } else {
                    Log.d("test", "response code " + response.code());
                    showErrorToast("Во время загрузки данных произошла ошибка! Повторите попытку!");
                }
                hideProgress();
            }

            @Override
            public void onFailure(Call<ReturnValueDto> call, Throwable t) {
                Log.d("test", "failure " + t);
                hideProgress();
                showErrorToast("Во время загрузки данных произошла ошибка. Повторите попытку!");
            }
        });

    }

    /*
        Метод для вывода тоста с информацией об ошибке
     */
    private void showErrorToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    /*
        Метод, скрывающий прогресс бар после завершения операции
     */
    private void hideProgress() {
        binding.progressBar.setVisibility(View.INVISIBLE);
        binding.progressBar.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
    }

    /*
        Метод для вывода полученных данных на экран
     */
    private void displayHardwareTable() {
        binding.tableHardware.removeAllViews();
        for (Hardware h : hp.getHardwareList()) {

            LayoutInflater inflater = LayoutInflater.from(this);
            LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.task3_table_row, null);

            ll.setOnClickListener((l) -> {
                HardwareInfoBlock hib = new HardwareInfoBlock(binding.formHardwareInfo, h, this);
                hib.loadDataHardwareInfo();
            });

            TextView tv1 = (TextView) ll.findViewById(R.id.col1);
            tv1.setText(h.getCode());

            TextView tv2 = (TextView) ll.findViewById(R.id.col2);
            tv2.setText(h.getName());

            TextView tv3 = (TextView) ll.findViewById(R.id.col3);
            tv3.setText(h.getStatus());

            int statusColor;
            switch (h.getStatusCode()) {
                case "installed": {
                    statusColor = R.color.status_green;
                    break;
                }
                case "withdrawn": {
                    statusColor = R.color.status_gray;
                    break;
                }
                default: {
                    statusColor = R.color.status_white;
                }
            }
            tv3.setBackground(new ColorDrawable(this.getColor(statusColor)));

            TextView tv4 = (TextView) ll.findViewById(R.id.col4);
            tv4.setText(h.getCriticality());

            int criticalityColor;
            switch (h.getCriticalityCode()) {
                case "1":
                case "2": {
                    criticalityColor = R.color.criticality_red;
                    break;
                }
                case "3": {
                    criticalityColor = R.color.criticality_yellow;
                    break;
                }
                case "4": {
                    criticalityColor = R.color.criticality_green;
                    break;
                }
                default: {
                    criticalityColor = R.color.criticality_gray;
                }
            }
            tv4.setBackground(new ColorDrawable(this.getColor(criticalityColor)));

            binding.tableHardware.addView(ll);
        }
    }
}