package com.iamscratches.TMS.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table
public class Vehicle {
    @Id
    @Column(name = "VEHICLE_ID")
    private Integer vehicleId;
    @Column
    @NotNull
    private String modelNo;
    @Column
    @NotNull
    private String modelName;
    @Column(name="VEHICLE_TYPE")
    @NotNull
    private Integer vehicleType;
    @Column
    private String color;
    @Column(name = "OWNER_ID")
    private Integer ownerId;
    @Column(name = "MANUFACTURER_ID")
    @NotNull
    private Integer manufacturerId;
    @Column
    private Date dom;
    @Column
    private Integer noc;
    @Column
    private Integer cc;
    @Column
    private Integer fuelUsed;
    @Column
    private Integer travelledKm;
    @Column
    private Date lpc;
    @Column
    private boolean expired;

    @ManyToOne
    @JoinColumn(name="MANUFACTURER_ID", insertable = false, updatable = false)
    private Manufacturer manufacturer;

    @ManyToOne
    @JoinColumn(name="VEHICLE_TYPE", insertable = false, updatable = false)
    private TypeOfVehicles typeOfVehicles;

    public Vehicle() {
    }

    public Vehicle(String modelNo, String modelName, Integer vehicleType, String color, Integer ownerId, Integer manufacturerId, Date dom, Integer noc, Integer cc, Integer fuelUsed, Integer travelledKm, Date lpc, boolean expired) {
        this.modelNo = modelNo;
        this.modelName = modelName;
        this.vehicleType = vehicleType;
        this.color = color;
        this.ownerId = ownerId;
        this.manufacturerId = manufacturerId;
        this.dom = dom;
        this.noc = noc;
        this.cc = cc;
        this.fuelUsed = fuelUsed;
        this.travelledKm = travelledKm;
        this.lpc = lpc;
        this.expired = expired;
    }

    public Vehicle(Integer vehicleId, String modelNo, String modelName, Integer vehicleType, String color, Integer ownerId, Integer manufacturerId, Date dom, Integer noc, Integer cc, Integer fuelUsed, Integer travelledKm, Date lpc, boolean expired, Manufacturer manufacturer, TypeOfVehicles typeOfVehicles) {
        this.vehicleId = vehicleId;
        this.modelNo = modelNo;
        this.modelName = modelName;
        this.vehicleType = vehicleType;
        this.color = color;
        this.ownerId = ownerId;
        this.manufacturerId = manufacturerId;
        this.dom = dom;
        this.noc = noc;
        this.cc = cc;
        this.fuelUsed = fuelUsed;
        this.travelledKm = travelledKm;
        this.lpc = lpc;
        this.expired = expired;
        this.manufacturer = manufacturer;
        this.typeOfVehicles = typeOfVehicles;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Integer getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(Integer vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public Date getDom() {
        return dom;
    }

    public void setDom(Date dom) {
        this.dom = dom;
    }

    public Integer getNoc() {
        return noc;
    }

    public void setNoc(Integer noc) {
        this.noc = noc;
    }

    public Integer getCc() {
        return cc;
    }

    public void setCc(Integer cc) {
        this.cc = cc;
    }

    public Integer getFuelUsed() {
        return fuelUsed;
    }

    public void setFuelUsed(Integer fuelUsed) {
        this.fuelUsed = fuelUsed;
    }

    public Integer getTravelledKm() {
        return travelledKm;
    }

    public void setTravelledKm(Integer travelledKm) {
        this.travelledKm = travelledKm;
    }

    public Date getLpc() {
        return lpc;
    }

    public void setLpc(Date lpc) {
        this.lpc = lpc;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public TypeOfVehicles getTypeOfVehicles() {
        return typeOfVehicles;
    }

    public void setTypeOfVehicles(TypeOfVehicles typeOfVehicles) {
        this.typeOfVehicles = typeOfVehicles;
    }


    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", modelNo='" + modelNo + '\'' +
                ", modelName='" + modelName + '\'' +
                ", vehicleType=" + vehicleType +
                ", color='" + color + '\'' +
                ", ownerId=" + ownerId +
                ", manufacturerId=" + manufacturerId +
                ", dom=" + dom +
                ", noc=" + noc +
                ", cc=" + cc +
                ", fuelUsed=" + fuelUsed +
                ", travelledKm=" + travelledKm +
                ", lpc=" + lpc +
                ", expired=" + expired +
                ", manufacturer=" + manufacturer +
                ", typeOfVehicles=" + typeOfVehicles +
                '}';
    }
}
