package ru.aspectnet.hardware.view;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.aspectnet.hardware.api.dto.task1.HardwareDto;
import ru.aspectnet.hardware.api.dto.task1.ReturnValueDto;
import ru.aspectnet.hardware.api.services.ReturnValueApi;
import ru.aspectnet.hardware.databinding.ActivityTask1Binding;

import ru.aspectnet.hardware.R;
import ru.aspectnet.hardware.model.convert.HardwareConverter;
import ru.aspectnet.hardware.model.data.Hardware;
import ru.aspectnet.hardware.model.data.HardwarePackage;

/*
    Активити для отображения таблицы с оборудованием
 */
public class Task1Activity extends AppCompatActivity {

    private ActivityTask1Binding binding;
    private HardwarePackage hp; // Объект с информацией о загруженном по REST оборудовании

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTask1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // Слушатель изменений в полях ввода для фильтрации данных
        TextWatcher tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            /*
                После изменения данных в поле ввода автоматически производим фильтрацию
             */
            @Override
            public void afterTextChanged(Editable s) {
                filterTable();
            }
        };

        // устанавливаем слушатели на все поля ввода
        binding.editTextF1.addTextChangedListener(tw);
        binding.editTextF2.addTextChangedListener(tw);
        binding.editTextF3.addTextChangedListener(tw);
        binding.editTextF4.addTextChangedListener(tw);

        // загружаем данные по REST
        loadData();
    }

    /*
        Метод для фильтрации данных в таблице
     */
    private void filterTable() {
        if (hp != null) {
            hp.setCodeFilter(binding.editTextF1.getText().toString());
            hp.setNameFilter(binding.editTextF2.getText().toString());
            hp.setStatusFilter(binding.editTextF3.getText().toString());
            hp.setCriticalityFilter(binding.editTextF4.getText().toString());
            displayHardwareTable();
        }
    }

    /*
        Метод для загрузки, преобразования и отображения данных об оборудовании
     */
    private void loadData() {

        binding.progressBar.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://eam-demo.aspectnet.ru/platform/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ReturnValueApi returnValueApi = retrofit.create(ReturnValueApi.class);

        Call<ReturnValueDto> returnValue = returnValueApi.returnValue();

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
                    filterTable();
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
    private void showErrorToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    /*
        Метод, скрывающий прогресс бар после завершения операции
     */
    private void hideProgress(){
        binding.progressBar.setVisibility(View.INVISIBLE);
        binding.progressBar.setLayoutParams(new LinearLayout.LayoutParams(0,0));
    }

    /*
        Метод для вывода полученных данных на экран
     */
    private void displayHardwareTable() {
        binding.tableHardware.removeAllViews();
        for (Hardware h : hp.getHardwareList()) {

            LayoutInflater inflater = LayoutInflater.from(this);
            LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.task1_table_row, null);

            ll.setOnClickListener((l)->{
                HardwareApplication.getInstance().setHardware(h);
                Intent intent = new Intent(this, Task2Activity.class);
                this.startActivity(intent);
            });

            TextView tv1 = (TextView) ll.findViewById(R.id.col1);
            tv1.setText(h.getCode());

            TextView tv2 = (TextView) ll.findViewById(R.id.col2);
            tv2.setText(h.getName());

            TextView tv3 = (TextView) ll.findViewById(R.id.col3);
            tv3.setText(h.getStatus());

            int statusColor;
            switch (h.getStatusCode()) {
                case "installed":{
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