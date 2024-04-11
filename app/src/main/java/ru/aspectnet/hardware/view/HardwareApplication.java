package ru.aspectnet.hardware.view;

import android.app.Application;
import android.util.Log;

import ru.aspectnet.hardware.model.data.Hardware;

/*
    Класс приложения
 */

public class HardwareApplication extends Application {
    private static HardwareApplication instance;
    Hardware hardware; // информация о выбранном оборудовании

    public HardwareApplication() {
        super.onCreate();
        instance = this;
        Log.d("test","Приложение запущено");
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
}
