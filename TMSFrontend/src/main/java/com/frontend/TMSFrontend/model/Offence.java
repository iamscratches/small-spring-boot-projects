package com.frontend.TMSFrontend.model;


public class Offence {
    private Integer offenceId;
    
    private String nof;

    private Integer vehicleType;
    
    private Integer penalty;

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
