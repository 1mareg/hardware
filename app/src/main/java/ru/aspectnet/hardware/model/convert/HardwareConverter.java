package ru.aspectnet.hardware.model.convert;

import ru.aspectnet.hardware.api.dto.HardwareDto;
import ru.aspectnet.hardware.model.data.Hardware;

/*
    Класс для преобразования данных из HardwareDto в Hardware
 */
public class HardwareConverter {
    public Hardware convert(HardwareDto hd){
        Hardware h = new Hardware();
        h.setId(hd.getId());
        h.setCode(hd.getCode());
        h.setName(hd.getName());
        h.setStatusCode(hd.getStatus_code());
        h.setStatus(hd.getStstus());
        h.setCriticalityCode(hd.getCriticality_code());
        h.setCriticality(hd.getCriticality());
        return h;
    }
}
