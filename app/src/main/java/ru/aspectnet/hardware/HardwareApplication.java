package ru.aspectnet.hardware;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.aspectnet.hardware.api.services.ReturnValueApi;
import ru.aspectnet.hardware.model.data.Hardware;

/*
    Класс приложения
 */

public class HardwareApplication extends Application {
    private static HardwareApplication instance;
    Hardware hardware; // информация о выбранном оборудовании
    ReturnValueApi returnValueApi; // объект для запросов по REST

    public HardwareApplication() {
        super.onCreate();
        instance = this;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://eam-demo.aspectnet.ru/platform/api/dm/rest/noAuth/actions/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        returnValueApi = retrofit.create(ReturnValueApi.class);
    }

    public static HardwareApplication getInstance() {
        return instance;
    }

    public Hardware getHardware() {
        return hardware;
    }

    public void setHardware(Hardware hardware) {
        this.hardware = hardware;
    }

    public ReturnValueApi getReturnValueApi() {
        return returnValueApi;
    }
}
