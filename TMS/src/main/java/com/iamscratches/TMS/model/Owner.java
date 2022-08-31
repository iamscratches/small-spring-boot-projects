package com.iamscratches.TMS.model;

import com.iamscratches.TMS.utils.model.Owner.Gender;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Owner {
    @Id
    @Column(name = "OWNER_ID")
    private Integer ownerId;
    @Column
    @NotNull
    private String fname;
    @Column
    private String lname;
    @Column
    @NotNull
    private Date dob;
    @Column
    private Long landlineNo;
    @Column
    @NotNull
    private Long mobileNo;
    @Column
    @NotNull
    private Gender gender;
    @Column
    private String tempAddr;
    @Column
    @NotNull
    private String permAddr;
    @Column
    @Range(min=100000,max=999999)
    @NotNull
    private Integer pincode;
    @Column
    @Size(min=2,max=2, message = "State code must be 2 Character Long")
    @NotNull
    private String stateCode;
    @Column
    private String occupation;
    @Column
    @Size(min=12, max=12)
    @NotNull
    private String adhaar;
    @Column
    @Size(min=10,max=10)
    private String pancard;
    @Column
    @NotNull
    private String addProofName;

    @OneToMany
    @JoinColumn(name="OWNER_ID", nullable=false, insertable = false, updatable = false)
    private List<Vehicle> vehicle;

    public Owner() {
    }

    public Owner(Integer ownerId, String fname, String lname, Date dob, Long landlineNo, Long mobileNo, Gender gender, String tempAddr, String permAddr, Integer pincode, String stateCode, String occupation, String adhaar, String pancard, String addProofName, List<Vehicle> vehicle) {
        this.ownerId = ownerId;
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.landlineNo = landlineNo;
        this.mobileNo = mobileNo;
        this.gender = gender;
        this.tempAddr = tempAddr;
        this.permAddr = permAddr;
        this.pincode = pincode;
        this.stateCode = stateCode;
        this.occupation = occupation;
        this.adhaar = adhaar;
        this.pancard = pancard;
        this.addProofName = addProofName;
        this.vehicle = vehicle;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Long getLandlineNo() {
        return landlineNo;
    }

    public void setLandlineNo(Long landlineNo) {
        this.landlineNo = landlineNo;
    }

    public Long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(Long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getTempAddr() {
        return tempAddr;
    }

    public void setTempAddr(String tempAddr) {
        this.tempAddr = tempAddr;
    }

    public String getPermAddr() {
        return permAddr;
    }

    public void setPermAddr(String permAddr) {
        this.permAddr = permAddr;
    }

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getAdhaar() {
        return adhaar;
    }

    public void setAdhaar(String adhaar) {
        this.adhaar = adhaar;
    }

    public String getPancard() {
        return pancard;
    }

    public void setPancard(String pancard) {
        this.pancard = pancard;
    }

    public String getAddProofName() {
        return addProofName;
    }

    public void setAddProofName(String addProofName) {
        this.addProofName = addProofName;
    }

    public List<Vehicle> getVehicle() {
        return vehicle;
    }

    public void setVehicle(List<Vehicle> vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "ownerId=" + ownerId +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", dob=" + dob +
                ", landlineNo=" + landlineNo +
                ", mobileNo=" + mobileNo +
                ", gender=" + gender +
                ", tempAddr='" + tempAddr + '\'' +
                ", permAddr='" + permAddr + '\'' +
                ", pincode=" + pincode +
                ", stateCode='" + stateCode + '\'' +
                ", occupation='" + occupation + '\'' +
                ", adhaar='" + adhaar + '\'' +
                ", pancard='" + pancard + '\'' +
                ", addProofName='" + addProofName + '\'' +
                ", vehicle=" + vehicle +
                '}';
    }
}
