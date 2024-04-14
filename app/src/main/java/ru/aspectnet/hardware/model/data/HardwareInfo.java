package ru.aspectnet.hardware.model.data;

/*
    Класс для хранения подробной информации об оборудовании (об одном устройстве) для обработки
 */
public class HardwareInfo {
    private String name;                // наименование
    private String departmentName;      // отдел
    private String statusValue;         // статус
    private String hierarchyLevelTypeName;  // тип актива в структуре
    private String costCodeName;        // код затрат

    private String inventoryNumber;     // инвентарный номер
    private String model;               // модель
    private String commissDate;         // дата ввода в эксплуатацию
    private String initialValue;        // первичная стоимость
    private String serialNumber;        // серийный номер
    private String installationDate;    // дата установки

    private boolean ecology;             // экология
    private boolean safety;              // безопасность
    private String dormantCauseName;    // причина остановки
    private String dormantStartDate;    // начало неактивности
    private String dormantEndDate;      // окончание неактивности

    private String statusCode;          // код статуса

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    public String getHierarchyLevelTypeName() {
        return hierarchyLevelTypeName;
    }

    public void setHierarchyLevelTypeName(String hierarchyLevelTypeName) {
        this.hierarchyLevelTypeName = hierarchyLevelTypeName;
    }

    public String getCostCodeName() {
        return costCodeName;
    }

    public void setCostCodeName(String costCodeName) {
        this.costCodeName = costCodeName;
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCommissDate() {
        return commissDate;
    }

    public void setCommissDate(String commissDate) {
        this.commissDate = commissDate;
    }

    public String getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(String initialValue) {
        this.initialValue = initialValue;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(String installationDate) {
        this.installationDate = installationDate;
    }

    public boolean getEcology() {
        return ecology;
    }

    public void setEcology(boolean ecology) {
        this.ecology = ecology;
    }

    public boolean getSafety() {
        return safety;
    }

    public void setSafety(boolean safety) {
        this.safety = safety;
    }

    public String getDormantCauseName() {
        return dormantCauseName;
    }

    public void setDormantCauseName(String dormantCauseName) {
        this.dormantCauseName = dormantCauseName;
    }

    public String getDormantStartDate() {
        return dormantStartDate;
    }

    public void setDormantStartDate(String dormantStartDate) {
        this.dormantStartDate = dormantStartDate;
    }

    public String getDormantEndDate() {
        return dormantEndDate;
    }

    public void setDormantEndDate(String dormantEndDate) {
        this.dormantEndDate = dormantEndDate;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
