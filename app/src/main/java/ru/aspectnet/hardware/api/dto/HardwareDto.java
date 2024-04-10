package ru.aspectnet.hardware.api.dto;

/*
    DTO - оборудование (1 элемент)
    Класс для десериализации JSON, полученного через API
 */
//TODO Пока что все поля String, т.к. в документации API не указано иное и может придти что угодно
// Можно переделать code в int и criticality_code в byte ориентируясь на имеющиеся данные
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
