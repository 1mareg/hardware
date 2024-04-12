package ru.aspectnet.hardware.view.block;

/*
    Функциональный интерфейс, для реализации различной реакции на нажатие на строку таблицы в заданиях 1 и 3
 */

import android.content.Context;

import ru.aspectnet.hardware.model.data.Hardware;

public interface OnTableRowClick {
    void onClick(Hardware h, Context ctx);
}
