package ru.aspectnet.hardware.model.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

/*
    Класс для хранения информации обо всем оборудовании, загруженном в приложение
 */
public class HardwarePackage {
    ArrayList<Hardware> hardwareList = new ArrayList<>();   // коллекция с элементами оборудования
    String codeFilter = "";         // фильтр для сортировки по полю "Код"
    String nameFilter = "";         // фильтр для сортировки по полю "Наименование"
    String statusFilter = "";       // фильтр для сортировки по полю "Статус"
    String criticalityFilter = "";  // фильтр для сортировки по полю "Критичность"

    /*
        Метод, возвращающий коллекцию оборудования
            - отсортированную по полю "Код";
            - отфильтрованную по всем полям, для которых установлены значения в фильтрах.
     */
    public ArrayList<Hardware> getHardwareList() {
        Collections.sort(hardwareList, Comparator.comparing(Hardware::getCode));

        return (ArrayList<Hardware>) hardwareList.stream()
                .filter(hardware -> hardware.getCode().contains(codeFilter))
                .filter(hardware -> hardware.getName().contains(nameFilter))
                .filter(hardware -> hardware.getStatus().contains(statusFilter))
                .filter(hardware -> hardware.getCriticality().contains(criticalityFilter))
                .collect(Collectors.toList());
    }

    public void setCodeFilter(String codeFilter) {
        this.codeFilter = codeFilter;
    }

    public void setNameFilter(String nameFilter) {
        this.nameFilter = nameFilter;
    }

    public void setStatusFilter(String statusFilter) {
        this.statusFilter = statusFilter;
    }

    public void setCriticalityFilter(String criticalityFilter) {
        this.criticalityFilter = criticalityFilter;
    }

    public void setHardware(Hardware hardware) {
        this.hardwareList.add(hardware);
    }
}
