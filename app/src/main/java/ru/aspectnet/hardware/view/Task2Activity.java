package ru.aspectnet.hardware.view;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

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
import ru.aspectnet.hardware.model.data.HardwareInfo;
import ru.aspectnet.hardware.model.data.HardwarePackage;

public class Task2Activity extends AppCompatActivity {

    private ActivityTask2Binding binding;

    private HardwarePackage hp; // Объект с информацией о загруженном по REST оборудовании

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTask2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // загружаем данные по REST
        loadData();

    }

    /*
    Метод для загрузки, преобразования и отображения данных об оборудовании
 */
    private void loadData() {

        //binding.progressBar.setVisibility(View.VISIBLE);

        //TODO Создавать единственный объект ретрофит на приложение
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://eam-demo.aspectnet.ru/platform/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ReturnValueApi returnValueApi = retrofit.create(ReturnValueApi.class);

        Call<ReturnValueHardwareInfoDto> returnValueHardwareInfoDto = returnValueApi.returnValueHardwareInfo(new RequestDto("65b7cb09-5796-4317-b1a0-2124ded23af0"));

        returnValueHardwareInfoDto.enqueue(new Callback<ReturnValueHardwareInfoDto>() {
            @Override
            public void onResponse(Call<ReturnValueHardwareInfoDto> call, Response<ReturnValueHardwareInfoDto> response) {
                if (response.isSuccessful()) {
                    ReturnValueHardwareInfoDto rvhid = response.body();
                    Log.d("test",rvhid.toString());
                    HardwareInfo hi = new HardwareInfoConverter().convert(rvhid);

                    Log.d("test", hi.getName());

                    /*hp = new HardwarePackage();
                    for (HardwareDto hd : rvd.getReturnValue()) {
                        hp.setHardware(new HardwareConverter().convert(hd));
                    }*/
                    //displayHardwareTable();
                    //filterTable();
                } else {
                    Log.d("test", "response code " + response.code());
                    //showErrorToast("Во время загрузки данных произошла ошибка! Повторите попытку!");
                }
                //hideProgress();
            }

            @Override
            public void onFailure(Call<ReturnValueHardwareInfoDto> call, Throwable t) {
                Log.d("test", "failure " + t);
                //hideProgress();
                //showErrorToast("Во время загрузки данных произошла ошибка. Повторите попытку!");
            }
        });
    }

}