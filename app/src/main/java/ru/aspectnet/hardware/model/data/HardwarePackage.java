package ru.aspectnet.hardware.model.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
    Класс для хранения информации обо всем оборудовании, загруженном в приложение
 */
public class HardwarePackage {
    ArrayList<Hardware> hardwareList = new ArrayList<>();

    public ArrayList<Hardware> getHardwareList() {
        Collections.sort(hardwareList, Comparator.comparing(Hardware::getCode));
        return hardwareList;
    }

    public void setHardware(Hardware hardware) {
        this.hardwareList.add(hardware);
    }
}
