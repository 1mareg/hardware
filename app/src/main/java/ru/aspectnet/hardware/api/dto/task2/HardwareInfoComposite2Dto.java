package ru.aspectnet.hardware.api.dto.task2;

/*
    DTO для составных ключей с 5-ю полями в JSON
    status criticality
 */
public class HardwareInfoComposite2Dto {
    private String id;
    private String code;
    private String value;
    private String description;
    private String enumId;

    public String getValue() {
        return value;
    }
}
