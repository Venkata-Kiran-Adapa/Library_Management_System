package com.Library_Management_System.DTOs;

import java.time.LocalDate;

import com.Library_Management_System.Entity.Books;
import com.Library_Management_System.Entity.Reservation;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class ReservationDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserRecordsDTO user;

    public ReservationDTO() {
    }

    public ReservationDTO(Reservation reservation) {
        this.id = reservation.getId();
        this.user = new UserRecordsDTO(reservation.getUser());
        this.book = new BookRecordsDTO(reservation.getBook().getBook_id(),reservation.getBook().getBook_title(),reservation.getBook().getBook_author());
        this.reservationDate = reservation.getReservationDate();
    }

    public BookRecordsDTO getBook() {
        return book;
    }

    public void setBook(BookRecordsDTO book) {
        this.book = book;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserRecordsDTO getUser() {
        return user;
    }

    public void setUser(UserRecordsDTO user) {
        this.user = user;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    @ManyToOne
    private BookRecordsDTO book;

    private LocalDate reservationDate;

}

