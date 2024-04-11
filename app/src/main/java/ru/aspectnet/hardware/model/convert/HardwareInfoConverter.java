package ru.aspectnet.hardware.model.convert;

/*
    Класс для преобразования данных из HardwareInfoDto (сущность, полученная по REST) в HardwareInfo (класс данных для использования в приложении)
 */

import ru.aspectnet.hardware.api.dto.task1.HardwareDto;
import ru.aspectnet.hardware.api.dto.task2.HardwareInfoDto;
import ru.aspectnet.hardware.api.dto.task2.ReturnValueHardwareInfoDto;
import ru.aspectnet.hardware.model.data.Hardware;
import ru.aspectnet.hardware.model.data.HardwareInfo;

public class HardwareInfoConverter {
    public HardwareInfo convert(ReturnValueHardwareInfoDto rvhid){
        HardwareInfo hi = new HardwareInfo();
        HardwareInfoDto hid = rvhid.getReturnValue();
        // hi.setCode(hid.); // TODO код надо взять из друго объекта
        hi.setName(hid.getName());
        hi.setDepartmentName(hid.getDepartment().getName());
        hi.setStatusValue(hid.getStatus().getValue());
        hi.setHierarchyLevelTypeName(hid.getHierarchyLevelType().getName());
        hi.setCostCodeName(hid.getCostCode().getName());

        hi.setInventoryNumber(hid.getInventoryNumber());
        hi.setModel(hid.getModel());
        hi.setCommissDate(hid.getCommissDate());
        hi.setInitialValue(hid.getInitialValue());
        hi.setSerialNumber(hid.getSerialNumber());
        hi.setInstallationDate(hid.getInstallationDate());

        hi.setEcology(hid.isEcology());
        hi.setSafety(hid.isSafety());
        if (hid.getDormantCause() != null) {
            hi.setDormantCauseName(hid.getDormantCause().getName());
        }
        hi.setDormantStartDate(hid.getDormantStartDate());
        hi.setDormantEndDate(hid.getDormantEndDate());
        hi.setStatusCode(hid.getStatus().getCode());
        return hi;
    }
}
