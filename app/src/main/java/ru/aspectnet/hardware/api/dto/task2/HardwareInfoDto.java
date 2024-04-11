package ru.aspectnet.hardware.api.dto.task2;

/*
    DTO для основной части JSON с информацией об оборудовании
 */
public class HardwareInfoDto {
    private String id;
    private int revision;
    private String name;
    private String schema;
    private String schemaId;
    private String updater;
    private String updateDate;
    private String creator;
    private String createDate;
    private int schemaRev;
    private String state;
    private String classification;
    private String lifetimeMax;
    private boolean rollupCostsPosition;
    private String normSpec;
    private String externalSystemAssets;
    private String designDrawing;
    private String externalSystemEquipment;
    private boolean failureCycle;
    private HardwareInfoComposite1Dto area;
    private String performanceMeasureUnit;
    private HardwareInfoComposite1Dto strategy;
    private HardwareInfoComposite1Dto damageType;
    private HardwareInfoComposite1Dto costCode;
    private boolean ecology;
    private HardwareInfoComposite1Dto ibo;
    private String responsible;
    private boolean vehicle;
    private HardwareInfoComposite1Dto overhaulCostCode;
    private boolean rollupCostsSystem;
    private boolean dependToPosition;
    private boolean rollupCostsObject;
    private boolean dependToSystem;
    private String assetSignificance;
    private String redaction;
    private boolean lifecycle;
    private boolean assetTemplate;
    private boolean autoNum;
    private String approved;
    private String lifetimeMin;
    private String reason;
    private String regNumber;
    private String templateCode;
    private String redactionStatus;
    private String workOrderTemplate;
    private boolean dependToObject;
    private HardwareInfoComposite1Dto HierarchyLevelType;
    private boolean editAssetConditionIndex;
    private String failureCurves;
    private HardwareInfoComposite1Dto assetFunction;
    private String decommissDate;
    private String assetConditionIndex;
    private HardwareInfoComposite1Dto parentObject;
    private String rcmProject;
    private boolean useRcmAnalysis;
    private boolean assetCondition;
    private String equipmentFailureValue;
    private HardwareInfoComposite1Dto department;
    private String item;
    private HardwareInfoComposite1Dto riskLevel;
    private boolean editEquipmentFailure;
    private String warehouse;
    private String purchaseOrder;
    private String constructionObjectCode;
    private String areaCodeProdRep;
    private String currentValue;
    private String purchaseDate;
    private String model;
    private String ownershipType;
    private String gisMap;
    private String yLocation;
    private HardwareInfoComposite2Dto status;
    private String dormantStartDate;
    private String objectGisId;
    private boolean notCreateWo;
    private String decommissCause;
    private String maxPerformance;
    private String minPerformance;
    private String normBase;
    private String xLocation;
    private String fuel;
    private String initialValue;
    private String gisLayer;
    private String dormantEndDate;
    private boolean production;
    private String suffixNum;
    private String installationDate;
    private String length;
    private HardwareInfoComposite1Dto dormantCause;
    private String geoCoordinate;
    private String prevReductionObject;
    private HardwareInfoComposite2Dto criticality;
    private String lengthMeasureUnit;
    private String primarySystem;
    private String prefixNum;
    private String lengthNum;
    private HardwareInfoComposite1Dto parentPosition;
    private boolean safety;
    private String serialNumber;
    private String code;
    private boolean notUse;
    private String commissDate;
    private String inventoryNumber;
    private String supplier;
    private String manufacturer;
    private HardwareInfoComposite1Dto location;
    private boolean linear;
    private HardwareInfoComposite1Dto _organization_;
    private String creatorUsername;
    private String updaterUsername;
    private HardwareInfoComposite1Dto _characteristics_;

    public String getName() {
        return name;
    }

    public HardwareInfoComposite1Dto getDepartment() {
        return department;
    }

    public HardwareInfoComposite2Dto getStatus() {
        return status;
    }

    public HardwareInfoComposite1Dto getHierarchyLevelType() {
        return HierarchyLevelType;
    }

    public HardwareInfoComposite1Dto getCostCode() {
        return costCode;
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public String getModel() {
        return model;
    }

    public String getCommissDate() {
        return commissDate;
    }

    public String getInitialValue() {
        return initialValue;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getId() {
        return id;
    }

    public boolean isEcology() {
        return ecology;
    }

    public String getDormantStartDate() {
        return dormantStartDate;
    }

    public String getDormantEndDate() {
        return dormantEndDate;
    }

    public String getInstallationDate() {
        return installationDate;
    }

    public HardwareInfoComposite1Dto getDormantCause() {
        return dormantCause;
    }

    public boolean isSafety() {
        return safety;
    }

    public String getCode() {
        return code;
    }

}
