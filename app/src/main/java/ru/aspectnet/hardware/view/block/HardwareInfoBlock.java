package ru.aspectnet.hardware.view.block;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.aspectnet.hardware.HardwareApplication;
import ru.aspectnet.hardware.R;
import ru.aspectnet.hardware.api.dto.task2.RequestDto;
import ru.aspectnet.hardware.api.dto.task2.ReturnValueHardwareInfoDto;
import ru.aspectnet.hardware.databinding.FormHardwareInfoBinding;
import ru.aspectnet.hardware.model.convert.HardwareInfoConverter;
import ru.aspectnet.hardware.model.data.Hardware;
import ru.aspectnet.hardware.model.data.HardwareInfo;

/*
    Класс для загрузки и вывода на экран подробной информации об оборудовании
 */
public class HardwareInfoBlock {
    private FormHardwareInfoBinding fhib;
    private Hardware h; // объект с информацией об оборудовании (из него берем данные для запроса и в него записываем результат)
    private Context ctx; // контекст

    public HardwareInfoBlock(FormHardwareInfoBinding fhib,
                             Hardware h,
                             Context ctx) {
        this.fhib = fhib;
        this.h = h;
        this.ctx = ctx;
    }

    /*
        Метод для загрузки, преобразования и отображения данных об оборудовании
     */
    public void loadDataHardwareInfo() {

        showProgress();

        Call<ReturnValueHardwareInfoDto> returnValueHardwareInfoDto = HardwareApplication.getInstance().getReturnValueApi().returnValueHardwareInfo(new RequestDto(h.getId()));

        returnValueHardwareInfoDto.enqueue(new Callback<ReturnValueHardwareInfoDto>() {
            @Override
            public void onResponse(Call<ReturnValueHardwareInfoDto> call, Response<ReturnValueHardwareInfoDto> response) {
                try {
                    Thread.sleep(1000);
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
    private void displayHardwareInfoTable() {
        fhib.editTextCode.setText(h.getCode());
        fhib.editTextName.setText(h.getHardwareInfo().getName());
        fhib.editTextHierarchyLevelTypeName.setText(h.getHardwareInfo().getHierarchyLevelTypeName());
        fhib.editTextCostCodeName.setText(h.getHardwareInfo().getCostCodeName());

        fhib.editTextInventoryNumber.setText(h.getHardwareInfo().getInventoryNumber());
        fhib.editTextModel.setText(h.getHardwareInfo().getModel());
        fhib.editTextCommissDate.setText(h.getHardwareInfo().getCommissDate());
        fhib.editTextInitialValue.setText(h.getHardwareInfo().getInitialValue());
        fhib.editTextSerialNumber.setText(h.getHardwareInfo().getSerialNumber());
        fhib.editTextInstallationDate.setText(h.getHardwareInfo().getInstallationDate());

        fhib.checkBoxEcology.setChecked(h.getHardwareInfo().getEcology());
        fhib.checkBoxSafety.setChecked(h.getHardwareInfo().getSafety());
        fhib.editTextDormantCauseDate.setText(h.getHardwareInfo().getDormantCauseName());
        fhib.editTextDormantStartDate.setText(h.getHardwareInfo().getDormantStartDate());
        fhib.editTextDormantEndDate.setText(h.getHardwareInfo().getDormantEndDate());

        int spinnerLayout = h.getStatusCode().equals("withdrawn") ? R.layout.spinner_layout_disabled : R.layout.spinner_layout;

        ArrayList<String> departmentNames = new ArrayList<>();
        departmentNames.add("Служба младшего механика");
        departmentNames.add("Служба главного механика");
        ArrayAdapter<String> adapter = new ArrayAdapter(ctx, spinnerLayout, departmentNames);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        fhib.spinnerDepartmentName.setAdapter(adapter);
        fhib.spinnerDepartmentName.setSelection(departmentNames.indexOf(h.getHardwareInfo().getDepartmentName()));

        ArrayList<String> statusValues = new ArrayList<>();
        statusValues.add("В эксплуатации");
        statusValues.add("Выведено из эксплуатации");
        ArrayAdapter<String> adapter2 = new ArrayAdapter(ctx, spinnerLayout, statusValues);
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        fhib.spinnerStatusValue.setAdapter(adapter2);
        fhib.spinnerStatusValue.setSelection(statusValues.indexOf(h.getHardwareInfo().getStatusValue()));


        findChildAndSetEnabled(fhib.linearLayoutHardwareInfo);

    }

    private void findChildAndSetEnabled(ViewGroup vg) {
        for (int i = 0; i < vg.getChildCount(); i++) {
            View v = vg.getChildAt(i);

            boolean isEnabled = !h.getStatusCode().equals("withdrawn");

            if ((v instanceof EditText)
                    || (v instanceof CheckBox)
                    || (v instanceof Spinner)) {
                v.setEnabled(isEnabled);
            }

            if (v instanceof EditText) {
                if (isEnabled) {
                    ((TextView) v).setTextColor(ContextCompat.getColor(ctx, R.color.text_color_edit_text));
                } else {
                    ((TextView) v).setTextColor(ContextCompat.getColor(ctx, R.color.text_color_edit_text_disabled));
                }
            }

            if (v instanceof ViewGroup) {
                findChildAndSetEnabled((ViewGroup) v);
            }
        }
    }


    /*
        Метод, показывающий прогресс бар перед началом операции
     */
    private void showProgress() {
        fhib.preloader.progressBar.setVisibility(View.VISIBLE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.CENTER;
        fhib.preloader.progressBar.setLayoutParams(params);
        fhib.linearLayoutHardwareInfo.setVisibility(View.INVISIBLE);
    }

    /*
        Метод, скрывающий прогресс бар после завершения операции
     */
    private void hideProgress() {
        fhib.preloader.progressBar.setVisibility(View.INVISIBLE);
        fhib.preloader.progressBar.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
        fhib.linearLayoutHardwareInfo.setVisibility(View.VISIBLE);
    }

    /*
        Метод для вывода тоста с информацией об ошибке
     */
    private void showErrorToast(String message) {
        Toast.makeText(ctx, message, Toast.LENGTH_LONG).show();
    }
}
