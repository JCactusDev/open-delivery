package com.github.jcactus.serviceorder.model;

import lombok.Getter;

@Getter
public enum OrderState {
    NEW("Новый"),
    AWAITING_VERIFICATION("Ожидает проверки"),
    ACCEPT("Подтвержден"),
    CANCELLED("Отменен"),
    AWAITING_SHIPMENT("Ожидает отгрузки"),
    SHIPPED("Отгружен"),
    IN_TRANSIT("В пути"),
    DELIVERED("Доставлен"),
    COMPLETED("Выполнен");

    private final String view;

    OrderState(String view) {
        this.view = view;
    }
}
