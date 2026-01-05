package com.Library_Management_System.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

import com.Library_Management_System.DTOs.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservation_id;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    private Users user;

    public Reservation() {
    }

    public Reservation(Long id, Users user, Books book, LocalDate reservationDate) {
        this.reservation_id = id;
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
        return reservation_id;
    }

    public void setId(Long id) {
        this.reservation_id = id;
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

    @Override
	public String toString() {
		return "Reservation [id=" + reservation_id + ", user=" + user + ", book=" + book + ", reservationDate=" + reservationDate
				+ "]";
	}

	private LocalDate reservationDate;

}
