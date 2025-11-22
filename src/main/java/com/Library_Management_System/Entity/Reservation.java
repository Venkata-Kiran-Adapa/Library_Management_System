package com.Library_Management_System.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

import com.Library_Management_System.DTOs.UserDTO;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Users user;

    public Reservation() {
    }

    public Reservation(Long id, Users user, Books book, LocalDate reservationDate) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.reservationDate = reservationDate;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    @ManyToOne
    private Books book;

    private LocalDate reservationDate;

}
