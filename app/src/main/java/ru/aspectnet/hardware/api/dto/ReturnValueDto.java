package ru.aspectnet.hardware.api.dto;

import java.util.ArrayList;

import lombok.Getter;

/*
    DTO - оборудование (весь возвращаемый JSON)
 */
public class ReturnValueDto {
    private ArrayList<HardwareDto> returnValue;

    public ArrayList<HardwareDto> getReturnValue() {
        return returnValue;
    }
}
