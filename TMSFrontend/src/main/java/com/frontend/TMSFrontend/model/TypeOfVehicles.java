package com.frontend.TMSFrontend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public class TypeOfVehicles {

    private Integer vehicleTypeId;

    private String vehicleCategory;

    public TypeOfVehicles() {
    }

    public TypeOfVehicles(Integer vehicleTypeId, String vehicleCategory) {
        this.vehicleTypeId = vehicleTypeId;
        this.vehicleCategory = vehicleCategory;
    }

    public Integer getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Integer vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public String getVehicleCategory() {
        return vehicleCategory;
    }

    public void setVehicleCategory(String vehicleCategory) {
        this.vehicleCategory = vehicleCategory;
    }

    @Override
    public String toString() {
        return "TypeOfVehicles{" +
                "vehicleTypeId=" + vehicleTypeId +
                ", vehicleCategory='" + vehicleCategory + '\'' +
                '}';
    }
}
