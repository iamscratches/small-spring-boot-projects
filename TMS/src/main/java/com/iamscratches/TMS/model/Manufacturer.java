package com.iamscratches.TMS.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
public class Manufacturer {
    @Id
    @Column
    private Integer manufacturerId;
    @Column
    private String name;
    @Column
    private Date estd;
    @Column
    private Date licenseDate;
    @Column
    private Date expiryDate;
    @Column
    private boolean expired;

    public Manufacturer() {
    }

    public Manufacturer(Integer manufacturerId, String name, Date estd, Date licenseDate, Date expiryDate, boolean expired) {
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

    public Date getEstd() {
        return estd;
    }

    public void setEstd(Date estd) {
        this.estd = estd;
    }

    public Date getLicenseDate() {
        return licenseDate;
    }

    public void setLicenseDate(Date licenseDate) {
        this.licenseDate = licenseDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
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