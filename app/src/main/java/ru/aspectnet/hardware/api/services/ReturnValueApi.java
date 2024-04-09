package ru.aspectnet.hardware.api.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;
import ru.aspectnet.hardware.api.dto.ReturnValueDto;

public interface ReturnValueApi {

    @POST("dm/rest/noAuth/actions/caede3a6-d7e5-4a50-b283-4b20b07eb3fb/run")
    Call<ReturnValueDto> returnValue();
}
