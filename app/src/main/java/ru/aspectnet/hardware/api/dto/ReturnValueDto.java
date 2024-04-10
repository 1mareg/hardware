package ru.aspectnet.hardware.api.dto;

import java.util.ArrayList;

/*
    DTO - оборудование (весь возвращаемый JSON)
    Класс для десериализации JSON, полученного через API
 */
public class ReturnValueDto {
    private ArrayList<HardwareDto> returnValue;

    public ArrayList<HardwareDto> getReturnValue() {
        return returnValue;
    }
}
