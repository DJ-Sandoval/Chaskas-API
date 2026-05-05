package com.api.BackChaskas.model.enums;

public enum RoleType {
    ROLE_ADMIN("Administrador - Gestión total del sistema"),
    ROLE_EMPLOYEE("Empleado/Cajero - Ventas y caja"),
    ROLE_CUSTOMER("Cliente - E-commerce");

    private final String description;

    RoleType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
