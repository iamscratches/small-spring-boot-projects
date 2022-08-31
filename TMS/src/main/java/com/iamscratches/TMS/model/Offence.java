package com.iamscratches.TMS.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table
public class Offence {
    @Id
    @Column(name = "OFFENCE_ID")
    private Integer offenceId;
    @Column
    private String nof;
    @Column(name="VEHICLE_TYPE_ID")
    private Integer vehicleType;
    @Column
    @NotNull
    private Integer penalty;

    @ManyToOne
    @JoinColumn(name="VEHICLE_TYPE_ID", insertable = false, updatable = false)
    private TypeOfVehicles typeOfVehicles;

    public Offence() {
    }

    public Offence(Integer offenceId, String nof, Integer vehicleType, Integer penalty, TypeOfVehicles typeOfVehicles) {
        this.offenceId = offenceId;
        this.nof = nof;
        this.vehicleType = vehicleType;
        this.penalty = penalty;
        this.typeOfVehicles = typeOfVehicles;
    }

    public Integer getOffenceId() {
        return offenceId;
    }

    public void setOffenceId(Integer offenceId) {
        this.offenceId = offenceId;
    }

    public String getNof() {
        return nof;
    }

    public void setNof(String nof) {
        this.nof = nof;
    }

    public Integer getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(Integer vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Integer getPenalty() {
        return penalty;
    }

    public void setPenalty(Integer penalty) {
        this.penalty = penalty;
    }

    public TypeOfVehicles getTypeOfVehicles() {
        return typeOfVehicles;
    }

    public void setTypeOfVehicles(TypeOfVehicles typeOfVehicles) {
        this.typeOfVehicles = typeOfVehicles;
    }

    @Override
    public String toString() {
        return "Offence{" +
                "offenceId=" + offenceId +
                ", nof='" + nof + '\'' +
                ", vehicleType=" + vehicleType +
                ", penalty=" + penalty +
                ", typeOfVehicles=" + typeOfVehicles +
                '}';
    }
}
