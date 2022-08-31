package com.frontend.TMSFrontend.model;

import java.time.LocalDateTime;


public class OffenceDetails {

    private Integer appNo;

    private Integer offenceId;

    private Integer vehicleNo;

    private LocalDateTime offenceTime;

    private String place;

    private String reportedBy;

    private Offence offence;

    private Registration registration;

    private User user;

    public OffenceDetails() {
    }

    public OffenceDetails(Integer appNo, Integer offenceId, Integer vehicleNo, LocalDateTime offenceTime, String place, String reportedBy, Offence offence, Registration registration, User user) {
        this.appNo = appNo;
        this.offenceId = offenceId;
        this.vehicleNo = vehicleNo;
        this.offenceTime = offenceTime;
        this.place = place;
        this.reportedBy = reportedBy;
        this.offence = offence;
        this.registration = registration;
        this.user = user;
    }

    public Integer getAppNo() {
        return appNo;
    }

    public void setAppNo(Integer appNo) {
        this.appNo = appNo;
    }

    public Integer getOffenceId() {
        return offenceId;
    }

    public void setOffenceId(Integer offenceId) {
        this.offenceId = offenceId;
    }

    public Integer getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(Integer vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public LocalDateTime getOffenceTime() {
        return offenceTime;
    }

    public void setOffenceTime(LocalDateTime offenceTime) {
        this.offenceTime = offenceTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    public Offence getOffence() {
        return offence;
    }

    public void setOffence(Offence offence) {
        this.offence = offence;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "OffenceDetails{" +
                "appNo=" + appNo +
                ", offenceId=" + offenceId +
                ", vehicleNo=" + vehicleNo +
                ", offenceTime=" + offenceTime +
                ", place='" + place + '\'' +
                ", reportedBy='" + reportedBy + '\'' +
                ", offence=" + offence +
                ", registration=" + registration +
                ", user=" + user +
                '}';
    }
}