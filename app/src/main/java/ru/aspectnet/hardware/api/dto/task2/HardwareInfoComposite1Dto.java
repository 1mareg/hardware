package ru.aspectnet.hardware.api.dto.task2;

/*
    DTO для составных ключей с 4-мя полями в JSON
    (area,strategy,costCode,ibo,overhaulCostCode,HierarchyLevelType,department,riskLevel,
    parentPosition,location,_organization_)
 */

public class HardwareInfoComposite1Dto {
    private String id;
    private String schemaId;
    private String name;
    private boolean _isReference_;
}
