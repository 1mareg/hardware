package ru.aspectnet.hardware.api.dto.task2;

/*
    DTO для запроса по REST
 */
public class RequestDto {
    //TODO задать аннотацией имя в JSON, а поле переименовать в нормальное
    private String obj;

    public RequestDto(String value){
        obj = value;
    }
}
