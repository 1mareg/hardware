package ru.aspectnet.hardware.view;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.aspectnet.hardware.api.dto.task1.HardwareDto;
import ru.aspectnet.hardware.api.dto.task1.ReturnValueDto;
import ru.aspectnet.hardware.api.dto.task2.RequestDto;
import ru.aspectnet.hardware.api.dto.task2.ReturnValueHardwareInfoDto;
import ru.aspectnet.hardware.api.services.ReturnValueApi;
import ru.aspectnet.hardware.databinding.ActivityTask2Binding;

import ru.aspectnet.hardware.R;
import ru.aspectnet.hardware.model.convert.HardwareConverter;
import ru.aspectnet.hardware.model.convert.HardwareInfoConverter;
import ru.aspectnet.hardware.model.data.Hardware;
import ru.aspectnet.hardware.model.data.HardwareInfo;
import ru.aspectnet.hardware.model.data.HardwarePackage;

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
        loadData();

    }

    /*
        Метод для загрузки, преобразования и отображения данных об оборудовании
     */
    private void loadData() {

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
                hideProgress();
            }

            @Override
            public void onFailure(Call<ReturnValueHardwareInfoDto> call, Throwable t) {
                hideProgress();
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

    /*
        Метод, скрывающий прогресс бар после завершения операции
     */
    private void hideProgress(){
        binding.formHardwareInfo.progressBar.setVisibility(View.INVISIBLE);
        binding.formHardwareInfo.progressBar.setLayoutParams(new LinearLayout.LayoutParams(0,0));
        binding.formHardwareInfo.linearLayoutHardwareInfo.setVisibility(View.VISIBLE);
    }

    /*
        Метод для вывода тоста с информацией об ошибке
     */
    private void showErrorToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}