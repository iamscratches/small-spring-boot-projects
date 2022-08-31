package com.iamscratches.TMS.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table
public class Registration implements Serializable {
    @Id
    @Column
    private Integer appNo;
    @Column(name = "VEHICLE_ID")
    @NotNull
    private Integer vehicleId;
    @Column(name = "VEHICLE_NO")
    @NotNull
    private Integer vehicleNo;
    @Column(name = "OWNER_ID")
    private Integer ownerId;
    @Column
    private Date dateOfRetention;
    @Column
    private boolean valid;
    @Column
    private Date dateOfExpiry;

    @ManyToOne
    @JoinColumn(name="OWNER_ID", nullable=false, insertable = false, updatable = false)
    private Owner owner;

    @OneToOne
    @JoinColumn(name="VEHICLE_ID", insertable = false, updatable = false)
    private Vehicle vehicle;

    public Registration() {
    }

    public Registration(Integer appNo, Integer vehicleId, Integer vehicleNo, Integer ownerId, Date dateOfRetention, boolean valid, Date dateOfExpiry, Owner owner, Vehicle vehicle) {
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

    public Date getDateOfRetention() {
        return dateOfRetention;
    }

    public void setDateOfRetention(Date dateOfRetention) {
        this.dateOfRetention = dateOfRetention;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Date getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(Date dateOfExpiry) {
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
