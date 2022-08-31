package com.frontend.TMSFrontend.model;

public class Vehicle {

    private Integer vehicleId;

    private String modelNo;

    private String modelName;

    private Integer vehicleType;

    private String color;

    private Integer ownerId;

    private Integer manufacturerId;

    private String dom;

    private Integer noc;

    private Integer cc;

    private Integer fuelUsed;

    private Integer travelledKm;

    private String lpc;

    private String expired;

    private Manufacturer manufacturer;

    private TypeOfVehicles typeOfVehicles;

    public Vehicle() {
    }

    public Vehicle(String modelNo, String modelName, Integer vehicleType, String color, Integer ownerId, Integer manufacturerId, String dom, Integer noc, Integer cc, Integer fuelUsed, Integer travelledKm, String lpc, String expired) {
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

    public Vehicle(Integer vehicleId, String modelNo, String modelName, Integer vehicleType, String color, Integer ownerId, Integer manufacturerId, String dom, Integer noc, Integer cc, Integer fuelUsed, Integer travelledKm, String lpc, String expired, Manufacturer manufacturer, TypeOfVehicles typeOfVehicles) {
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

    public String getDom() {
        return dom;
    }

    public void setDom(String dom) {
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

    public String getLpc() {
        return lpc;
    }

    public void setLpc(String lpc) {
        this.lpc = lpc;
    }

    public String isExpired() {
        return expired;
    }

    public void setExpired(String expired) {
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
