package com.iamscratches.spring.springbootdemo.data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long reservationId;
    @Column
    private long roomId;
    @Column
    private long guestId;
    @Column
    private Date resDate;

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public long getGuestId() {
        return guestId;
    }

    public void setGuestId(long guestId) {
        this.guestId = guestId;
    }

    public Date getDate() {
        return resDate;
    }

    public void setDate(Date resDate) {
        this.resDate = resDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", roomId=" + roomId +
                ", guestId=" + guestId +
                ", date=" + resDate +
                '}';
    }
}

