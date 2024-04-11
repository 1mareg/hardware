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
    private String damageType;
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
    private String assetFunction;
    private String decommissDate;
    private String assetConditionIndex;
    private String parentObject;
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

    public int getRevision() {
        return revision;
    }

    public String getSchema() {
        return schema;
    }

    public String getSchemaId() {
        return schemaId;
    }

    public String getUpdater() {
        return updater;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public String getCreator() {
        return creator;
    }

    public String getCreateDate() {
        return createDate;
    }

    public int getSchemaRev() {
        return schemaRev;
    }

    public String getState() {
        return state;
    }

    public String getClassification() {
        return classification;
    }

    public String getLifetimeMax() {
        return lifetimeMax;
    }

    public boolean isRollupCostsPosition() {
        return rollupCostsPosition;
    }

    public String getNormSpec() {
        return normSpec;
    }

    public String getExternalSystemAssets() {
        return externalSystemAssets;
    }

    public String getDesignDrawing() {
        return designDrawing;
    }

    public String getExternalSystemEquipment() {
        return externalSystemEquipment;
    }

    public boolean isFailureCycle() {
        return failureCycle;
    }

    public HardwareInfoComposite1Dto getArea() {
        return area;
    }

    public String getPerformanceMeasureUnit() {
        return performanceMeasureUnit;
    }

    public HardwareInfoComposite1Dto getStrategy() {
        return strategy;
    }

    public String getDamageType() {
        return damageType;
    }

    public boolean isEcology() {
        return ecology;
    }

    public HardwareInfoComposite1Dto getIbo() {
        return ibo;
    }

    public String getResponsible() {
        return responsible;
    }

    public boolean isVehicle() {
        return vehicle;
    }

    public HardwareInfoComposite1Dto getOverhaulCostCode() {
        return overhaulCostCode;
    }

    public boolean isRollupCostsSystem() {
        return rollupCostsSystem;
    }

    public boolean isDependToPosition() {
        return dependToPosition;
    }

    public boolean isRollupCostsObject() {
        return rollupCostsObject;
    }

    public boolean isDependToSystem() {
        return dependToSystem;
    }

    public String getAssetSignificance() {
        return assetSignificance;
    }

    public String getRedaction() {
        return redaction;
    }

    public boolean isLifecycle() {
        return lifecycle;
    }

    public boolean isAssetTemplate() {
        return assetTemplate;
    }

    public boolean isAutoNum() {
        return autoNum;
    }

    public String getApproved() {
        return approved;
    }

    public String getLifetimeMin() {
        return lifetimeMin;
    }

    public String getReason() {
        return reason;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public String getRedactionStatus() {
        return redactionStatus;
    }

    public String getWorkOrderTemplate() {
        return workOrderTemplate;
    }

    public boolean isDependToObject() {
        return dependToObject;
    }

    public boolean isEditAssetConditionIndex() {
        return editAssetConditionIndex;
    }

    public String getFailureCurves() {
        return failureCurves;
    }

    public String getAssetFunction() {
        return assetFunction;
    }

    public String getDecommissDate() {
        return decommissDate;
    }

    public String getAssetConditionIndex() {
        return assetConditionIndex;
    }

    public String getParentObject() {
        return parentObject;
    }

    public String getRcmProject() {
        return rcmProject;
    }

    public boolean isUseRcmAnalysis() {
        return useRcmAnalysis;
    }

    public boolean isAssetCondition() {
        return assetCondition;
    }

    public String getEquipmentFailureValue() {
        return equipmentFailureValue;
    }

    public String getItem() {
        return item;
    }

    public HardwareInfoComposite1Dto getRiskLevel() {
        return riskLevel;
    }

    public boolean isEditEquipmentFailure() {
        return editEquipmentFailure;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public String getPurchaseOrder() {
        return purchaseOrder;
    }

    public String getConstructionObjectCode() {
        return constructionObjectCode;
    }

    public String getAreaCodeProdRep() {
        return areaCodeProdRep;
    }

    public String getCurrentValue() {
        return currentValue;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public String getOwnershipType() {
        return ownershipType;
    }

    public String getGisMap() {
        return gisMap;
    }

    public String getyLocation() {
        return yLocation;
    }

    public String getDormantStartDate() {
        return dormantStartDate;
    }

    public String getObjectGisId() {
        return objectGisId;
    }

    public boolean isNotCreateWo() {
        return notCreateWo;
    }

    public String getDecommissCause() {
        return decommissCause;
    }

    public String getMaxPerformance() {
        return maxPerformance;
    }

    public String getMinPerformance() {
        return minPerformance;
    }

    public String getNormBase() {
        return normBase;
    }

    public String getxLocation() {
        return xLocation;
    }

    public String getFuel() {
        return fuel;
    }

    public String getGisLayer() {
        return gisLayer;
    }

    public String getDormantEndDate() {
        return dormantEndDate;
    }

    public boolean isProduction() {
        return production;
    }

    public String getSuffixNum() {
        return suffixNum;
    }

    public String getInstallationDate() {
        return installationDate;
    }

    public String getLength() {
        return length;
    }

    public HardwareInfoComposite1Dto getDormantCause() {
        return dormantCause;
    }

    public String getGeoCoordinate() {
        return geoCoordinate;
    }

    public String getPrevReductionObject() {
        return prevReductionObject;
    }

    public HardwareInfoComposite2Dto getCriticality() {
        return criticality;
    }

    public String getLengthMeasureUnit() {
        return lengthMeasureUnit;
    }

    public String getPrimarySystem() {
        return primarySystem;
    }

    public String getPrefixNum() {
        return prefixNum;
    }

    public String getLengthNum() {
        return lengthNum;
    }

    public HardwareInfoComposite1Dto getParentPosition() {
        return parentPosition;
    }

    public boolean isSafety() {
        return safety;
    }

    public String getCode() {
        return code;
    }

    public boolean isNotUse() {
        return notUse;
    }

    public String getSupplier() {
        return supplier;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public HardwareInfoComposite1Dto getLocation() {
        return location;
    }

    public boolean isLinear() {
        return linear;
    }

    public HardwareInfoComposite1Dto get_organization_() {
        return _organization_;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public String getUpdaterUsername() {
        return updaterUsername;
    }

    public HardwareInfoComposite1Dto get_characteristics_() {
        return _characteristics_;
    }
}
