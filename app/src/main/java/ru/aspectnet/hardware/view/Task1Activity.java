package ru.aspectnet.hardware.view;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.Collections;
import java.util.Comparator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.aspectnet.hardware.api.dto.HardwareDto;
import ru.aspectnet.hardware.api.dto.ReturnValueDto;
import ru.aspectnet.hardware.api.services.ReturnValueApi;
import ru.aspectnet.hardware.databinding.ActivityTask1Binding;

import ru.aspectnet.hardware.R;
import ru.aspectnet.hardware.model.convert.HardwareConverter;
import ru.aspectnet.hardware.model.data.Hardware;
import ru.aspectnet.hardware.model.data.HardwarePackage;

public class Task1Activity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityTask1Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTask1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadData();

    }

    /*
        Метод для загрузки, преобразования и отображения данных об оборудовании
     */
    private void loadData() {

        //TODO включить крутилку при загрузке

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
                    HardwarePackage hp = new HardwarePackage();
                    for (HardwareDto hd : rvd.getReturnValue()) {
                        hp.setHardware(new HardwareConverter().convert(hd));
                        Log.d("test", "response " + hd.getName());
                    }
                    displayHardwareTable(hp);
                } else {
                    Log.d("test", "response code " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ReturnValueDto> call, Throwable t) {
                Log.d("test", "failure " + t);
            }
        });

        //TODO отключить крутилку при загрузке
    }

    /*
        Метод для вывода полученных данных на экран
     */
    private void displayHardwareTable(HardwarePackage hp) {
        Collections.sort(hp.getHardwareList(), Comparator.comparing(Hardware::getCode));

        for (Hardware h : hp.getHardwareList()) {

            LayoutInflater inflater = LayoutInflater.from(this);
            LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.task1_table_row, null);
            TextView tv1 = (TextView) ll.findViewById(R.id.col1);
            tv1.setText(h.getCode());

            TextView tv2 = (TextView) ll.findViewById(R.id.col2);
            tv2.setText(h.getName());

            TextView tv3 = (TextView) ll.findViewById(R.id.col3);
            tv3.setText(h.getStatus());

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