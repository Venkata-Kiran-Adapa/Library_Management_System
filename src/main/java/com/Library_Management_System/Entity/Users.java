package com.Library_Management_System.Entity;


import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.Library_Management_System.Enums.Role;

import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long user_id;
    private String user_name;
    @Column(unique = true)
    private String userEmail;
	@JsonIgnore
	private String password;
	
	@OneToMany( mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<BorrowRecords> borrowRecords=new ArrayList<>();
	
	@OneToMany( mappedBy = "user",cascade = CascadeType.ALL)
	private List<Reservation> reservation=new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Books> books=new ArrayList<>();
	

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

   

    public List<BorrowRecords> getBorrowRecords() {
		return borrowRecords;
	}

	public void setBorrowRecords(BorrowRecords borrowRecords) {
		this.borrowRecords.add(borrowRecords);
	}

	public List<Reservation> getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation.add(reservation);
	}

	public List<Books> getBooks() {
		return books;
	}

	public void setBooks(Books books) {
		this.books.add(books);
	}

	public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Users(Long user_id, String user_name, String user_email, Role role) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.userEmail = user_email;
        this.role = role;
    }

    public Users() {
    }

    @Override
    public String toString() {
        return "Users{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_email='" + userEmail + '\'' +
                ", role=" + role +
                ", createdAt=" + createdAt +
                '}';
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime createdAt=LocalDateTime.now();


	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPassword() {
		return password;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}



}
