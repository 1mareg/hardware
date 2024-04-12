package ru.aspectnet.hardware.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import ru.aspectnet.hardware.R;
import ru.aspectnet.hardware.api.dto.task1.HardwareDto;
import ru.aspectnet.hardware.api.dto.task1.ReturnValueDto;
import ru.aspectnet.hardware.api.dto.task2.RequestDto;
import ru.aspectnet.hardware.api.dto.task2.ReturnValueHardwareInfoDto;
import ru.aspectnet.hardware.databinding.ActivityTask3Binding;
import ru.aspectnet.hardware.model.convert.HardwareConverter;
import ru.aspectnet.hardware.model.convert.HardwareInfoConverter;
import ru.aspectnet.hardware.model.data.Hardware;
import ru.aspectnet.hardware.model.data.HardwareInfo;
import ru.aspectnet.hardware.model.data.HardwarePackage;

public class Task3Activity extends AppCompatActivity {

    private ActivityTask3Binding binding;

    private HardwarePackage hp; // Объект с информацией о загруженном по REST оборудовании

    private Hardware h; // Объект с информацией об оборудовании

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTask3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // загружаем данные по REST
        loadDataHardware();
    }

    private void loadDataHardware(){

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

    private void hideProgressRight(){
        binding.formHardwareInfo.progressBar.setVisibility(View.INVISIBLE);
        binding.formHardwareInfo.progressBar.setLayoutParams(new LinearLayout.LayoutParams(0,0));
        binding.formHardwareInfo.linearLayoutHardwareInfo.setVisibility(View.VISIBLE);
    }

    /*
        Метод для вывода полученных данных на экран
     */
    private void displayHardwareTable() {
        binding.tableHardware.removeAllViews();
        for (Hardware h : hp.getHardwareList()) {

            LayoutInflater inflater = LayoutInflater.from(this);
            LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.task3_table_row, null);

            ll.setOnClickListener((l)->{
                this.h = h;
                loadDataHardwareInfo();
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

    /*
        Метод для загрузки, преобразования и отображения данных об оборудовании
     */
    private void loadDataHardwareInfo() {

        binding.formHardwareInfo.linearLayoutHardwareInfo.setVisibility(View.INVISIBLE);
        binding.formHardwareInfo.progressBar.setVisibility(View.VISIBLE);

        Call<ReturnValueHardwareInfoDto> returnValueHardwareInfoDto = HardwareApplication.getInstance().getReturnValueApi().returnValueHardwareInfo(new RequestDto(h.getId()));

        returnValueHardwareInfoDto.enqueue(new Callback<ReturnValueHardwareInfoDto>() {
            @Override
            public void onResponse(Call<ReturnValueHardwareInfoDto> call, Response<ReturnValueHardwareInfoDto> response) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (response.isSuccessful()) {
                    ReturnValueHardwareInfoDto rvhid = response.body();
                    HardwareInfo hi = new HardwareInfoConverter().convert(rvhid);
                    h.setHardwareInfo(hi);
                    displayHardwareInfoTable();
                } else {
                    showErrorToast("Во время загрузки данных произошла ошибка! Повторите попытку!");
                }
                hideProgressRight();
            }

            @Override
            public void onFailure(Call<ReturnValueHardwareInfoDto> call, Throwable t) {
                hideProgressRight();
                showErrorToast("Во время загрузки данных произошла ошибка. Повторите попытку!");
            }
        });
    }

    /*
    Метод для вывода информации на экран
 */
    private void displayHardwareInfoTable(){
        binding.formHardwareInfo.editTextCode.setText(h.getCode());
        binding.formHardwareInfo.editTextName.setText(h.getHardwareInfo().getName());
        binding.formHardwareInfo.editTextDepartmentName.setText(h.getHardwareInfo().getDepartmentName());
        binding.formHardwareInfo.editTextStatusValue.setText(h.getHardwareInfo().getStatusValue());
        binding.formHardwareInfo.editTextHierarchyLevelTypeName.setText(h.getHardwareInfo().getHierarchyLevelTypeName());
        binding.formHardwareInfo.editTextCostCodeName.setText(h.getHardwareInfo().getCostCodeName());

        binding.formHardwareInfo.editTextInventoryNumber.setText(h.getHardwareInfo().getInventoryNumber());
        binding.formHardwareInfo.editTextModel.setText(h.getHardwareInfo().getModel());
        binding.formHardwareInfo.editTextCommissDate.setText(h.getHardwareInfo().getCommissDate());
        binding.formHardwareInfo.editTextInitialValue.setText(h.getHardwareInfo().getInitialValue());
        binding.formHardwareInfo.editTextSerialNumber.setText(h.getHardwareInfo().getSerialNumber());
        binding.formHardwareInfo.editTextInstallationDate.setText(h.getHardwareInfo().getInstallationDate());

        binding.formHardwareInfo.editTextEcology.setText(h.getHardwareInfo().getEcology() ? "true" : "false");
        binding.formHardwareInfo.editTextSafety.setText(h.getHardwareInfo().getSafety() ? "true" : "false");
        binding.formHardwareInfo.editTextDormantCauseDate.setText(h.getHardwareInfo().getDormantCauseName());
        binding.formHardwareInfo.editTextDormantStartDate.setText(h.getHardwareInfo().getDormantStartDate());
        binding.formHardwareInfo.editTextDormantEndDate.setText(h.getHardwareInfo().getDormantEndDate());
    }
}