package com.frontend.TMSFrontend.model;

import javax.persistence.*;
import java.io.Serializable;


public class Registration implements Serializable {

    private Integer appNo;

    private Integer vehicleId;

    private Integer vehicleNo;

    private Integer ownerId;

    private String dateOfRetention;

    private String valid;

    private String dateOfExpiry;


    private Owner owner;


    private Vehicle vehicle;

    public Registration() {
    }

    public Registration(Integer appNo, Integer vehicleId, Integer vehicleNo, Integer ownerId, String dateOfRetention, String valid, String dateOfExpiry, Owner owner, Vehicle vehicle) {
        this.appNo = appNo;
        this.vehicleId = vehicleId;
        this.vehicleNo = vehicleNo;
        this.ownerId = ownerId;
        this.dateOfRetention = dateOfRetention;
        this.valid = valid;
        this.dateOfExpiry = dateOfExpiry;
        this.owner = owner;
        this.vehicle = vehicle;
    }

    public Integer getAppNo() {
        return appNo;
    }

    public void setAppNo(Integer appNo) {
        this.appNo = appNo;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(Integer vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getDateOfRetention() {
        return dateOfRetention;
    }

    public void setDateOfRetention(String dateOfRetention) {
        this.dateOfRetention = dateOfRetention;
    }

    public String isValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(String dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "appNo=" + appNo +
                ", vehicleId=" + vehicleId +
                ", VehicleNo=" + vehicleNo +
                ", ownerId=" + ownerId +
                ", dateOfRetention=" + dateOfRetention +
                ", valid=" + valid +
                ", dateOfExpiry=" + dateOfExpiry +
                ", owner=" + owner +
                ", vehicle=" + vehicle +
                '}';
    }
}
