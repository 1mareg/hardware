package ru.aspectnet.hardware.api.dto.task1;

/*
    DTO - оборудование (1 элемент)
    Класс для десериализации JSON, полученного через API
 */
public class HardwareDto {
    private String id;
    private String code;
    private String name;
    private String status_code;
    private String ststus;
    private String criticality_code;
    private String criticality;

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getStatus_code() {
        return status_code;
    }

    public String getStstus() {
        return ststus;
    }

    public String getCriticality_code() {
        return criticality_code;
    }

    public String getCriticality() {
        return criticality;
    }
}
