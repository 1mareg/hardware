package ru.aspectnet.hardware.view.block;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.aspectnet.hardware.HardwareApplication;
import ru.aspectnet.hardware.R;
import ru.aspectnet.hardware.api.dto.task1.HardwareDto;
import ru.aspectnet.hardware.api.dto.task1.ReturnValueDto;
import ru.aspectnet.hardware.model.convert.HardwareConverter;
import ru.aspectnet.hardware.model.data.Hardware;
import ru.aspectnet.hardware.model.data.HardwarePackage;

/*
    Класс для загрузки и вывода на экран подробной информации об оборудовании
 */
public class HardwareBlock {
    private LinearLayout progressBar; // прелоадер
    private LinearLayout tableHardware; // слой, содержащий таблицу
    private int resource; // xml, по которому формируется строка таблицы
    private OnTableRowClick otrc; // функция, выполняющаяся при нажатии на строку таблицы
    private Context ctx; // контекст
    private boolean filterOn = false; // признак необходимости фильтрации данных
    private HardwarePackage hp; // Объект с информацией о загруженном по REST оборудовании

    public HardwareBlock(LinearLayout progressBar,
                         LinearLayout tableHardware,
                         int resource,
                         OnTableRowClick otrc,
                         Context ctx) {
        this.progressBar = progressBar;
        this.tableHardware = tableHardware;
        this.resource = resource;
        this.otrc = otrc;
        this.ctx = ctx;
    }

    public void setFilterOn() {
        this.filterOn = true;
    }

    /*
            Метод для загрузки, преобразования и отображения данных об оборудовании
         */
    public void loadDataHardware() {

        showProgress();

        Call<ReturnValueDto> returnValue = HardwareApplication.getInstance().getReturnValueApi().returnValue();

        returnValue.enqueue(new Callback<ReturnValueDto>() {
            @Override
            public void onResponse(Call<ReturnValueDto> call, Response<ReturnValueDto> response) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
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
        Метод для вывода информации на экран
     */
    private void displayHardwareTable() {

        tableHardware.removeAllViews();
        for (Hardware h : hp.getHardwareList()) {

            LayoutInflater inflater = LayoutInflater.from(ctx);
            LinearLayout ll = (LinearLayout) inflater.inflate(resource, null);

            ll.setOnClickListener((l) -> {
                otrc.onClick(h,ctx);
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
            tv3.setBackground(new ColorDrawable(ctx.getColor(statusColor)));

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
            tv4.setBackground(new ColorDrawable(ctx.getColor(criticalityColor)));

            tableHardware.addView(ll);
        }
    }


    /*
        Метод, показывающий прогресс бар перед началом операции
     */
    private void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.CENTER;
        progressBar.setLayoutParams(params);
        tableHardware.setVisibility(View.INVISIBLE);
    }

    /*
        Метод, скрывающий прогресс бар после завершения операции
     */
    private void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        progressBar.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
        tableHardware.setVisibility(View.VISIBLE);
    }

    /*
        Метод для вывода тоста с информацией об ошибке
     */
    private void showErrorToast(String message) {
        Toast.makeText(ctx, message, Toast.LENGTH_LONG).show();
    }

    /*
        Метод для фильтрации данных в таблице
     */
    public void filterTable() {
        if (filterOn && (hp != null)) {
            EditText et1 = ((Activity) ctx).findViewById(R.id.editTextF1);
            hp.setCodeFilter(et1.getText().toString());

            EditText et2 = ((Activity) ctx).findViewById(R.id.editTextF2);
            hp.setNameFilter(et2.getText().toString());

            EditText et3 = ((Activity) ctx).findViewById(R.id.editTextF3);
            hp.setStatusFilter(et3.getText().toString());

            EditText et4 = ((Activity) ctx).findViewById(R.id.editTextF4);
            hp.setCriticalityFilter(et4.getText().toString());

            displayHardwareTable();
        }
    }

}
