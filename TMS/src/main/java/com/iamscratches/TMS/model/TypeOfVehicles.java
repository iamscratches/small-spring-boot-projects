package com.iamscratches.TMS.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class TypeOfVehicles {

    @Id
    @Column
    private Integer vehicleTypeId;
    @Column
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
