package com.Library_Management_System.Repository;

import com.Library_Management_System.Entity.Books;
import com.Library_Management_System.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepo extends JpaRepository<Reservation,Long> {
}
