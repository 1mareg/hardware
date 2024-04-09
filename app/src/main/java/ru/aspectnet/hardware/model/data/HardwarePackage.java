package ru.aspectnet.hardware.model.data;

import java.util.ArrayList;

/*
    Класс для хранения информации обо всем оборудовании, загруженном в приложение
 */
public class HardwarePackage {
    ArrayList<Hardware> hardwareList = new ArrayList<>();

    public ArrayList<Hardware> getHardwareList() {
        return hardwareList;
    }

    public void setHardware(Hardware hardware) {
        this.hardwareList.add(hardware);
    }
}
