package ru.aspectnet.hardware.api.services;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import ru.aspectnet.hardware.api.dto.task1.ReturnValueDto;
import ru.aspectnet.hardware.api.dto.task2.RequestDto;
import ru.aspectnet.hardware.api.dto.task2.ReturnValueHardwareInfoDto;

/*
    Интерфейс для полученяи данных через REST API
 */

public interface ReturnValueApi {

    @POST("dm/rest/noAuth/actions/caede3a6-d7e5-4a50-b283-4b20b07eb3fb/run")
    Call<ReturnValueDto> returnValue();

    @POST("dm/rest/noAuth/actions/22bedbbd-9b7b-43d3-8f2f-e53f90b1faf9/run")
    Call<ReturnValueHardwareInfoDto> returnValueHardwareInfo(@Body RequestDto data);
}
