package ru.aspectnet.hardware.model.data;

/*
    Класс для хранения информации об оборудовании (об одном устройстве)
 */
public class Hardware {
    private String id;
    private String code;
    private String name;
    private String statusCode;
    private String status;
    private String criticalityCode;
    private String criticality;
    private HardwareInfo hardwareInfo; // дополнительная информация

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCriticalityCode() {
        return criticalityCode;
    }

    public void setCriticalityCode(String criticalityCode) {
        this.criticalityCode = criticalityCode;
    }

    public String getCriticality() {
        return criticality;
    }

    public void setCriticality(String criticality) {
        this.criticality = criticality;
    }

    public HardwareInfo getHardwareInfo() {
        return hardwareInfo;
    }

    public void setHardwareInfo(HardwareInfo hardwareInfo) {
        this.hardwareInfo = hardwareInfo;
    }

    public String getId() {
        return id;
    }
}
