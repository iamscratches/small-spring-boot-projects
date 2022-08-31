package com.iamscratches.TMS.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table
public class OffenceDetails {
    @Id
    @Column
    private Integer appNo;
    @Column(name = "OFFENCE_ID")
    @NotNull
    private Integer offenceId;
    @Column(name = "VEHICLE_NO")
    @NotNull
    private Integer vehicleNo;
    @Column
    private LocalDateTime offenceTime;
    @Column
    private String place;
    @Column(name="REPORTED_BY")
    private String reportedBy;

    @ManyToOne
    @JoinColumn(name="OFFENCE_ID", insertable = false, updatable = false)
    private Offence offence;

    @ManyToOne
    @JoinColumn(name="VEHICLE_NO", referencedColumnName = "VEHICLE_NO", insertable = false, updatable = false)
    private Registration registration;

    @ManyToOne
    @JoinColumn(name="REPORTED_BY", referencedColumnName = "USERNAME", nullable = false, insertable = false, updatable = false)
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