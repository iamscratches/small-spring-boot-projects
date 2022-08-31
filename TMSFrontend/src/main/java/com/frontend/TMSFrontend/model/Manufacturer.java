package com.frontend.TMSFrontend.model;

import java.util.Date;


public class Manufacturer {

    private Integer manufacturerId;

    private String name;

    private String estd;

    private String licenseDate;

    private String expiryDate;

    private String expired;

    public Manufacturer() {
    }

    public Manufacturer(Integer manufacturerId, String name, String estd, String licenseDate, String expiryDate, String expired) {
        this.manufacturerId = manufacturerId;
        this.name = name;
        this.estd = estd;
        this.licenseDate = licenseDate;
        this.expiryDate = expiryDate;
        this.expired = expired;
    }

    public Integer getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEstd() {
        return estd;
    }

    public void setEstd(String estd) {
        this.estd = estd;
    }

    public String getLicenseDate() {
        return licenseDate;
    }

    public void setLicenseDate(String licenseDate) {
        this.licenseDate = licenseDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String isExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "manufacturerId=" + manufacturerId +
                ", name='" + name + '\'' +
                ", estd=" + estd +
                ", licenseDate=" + licenseDate +
                ", expiryDate=" + expiryDate +
                ", expired=" + expired +
                '}';
    }
}