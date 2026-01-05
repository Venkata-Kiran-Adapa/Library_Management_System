package com.Library_Management_System.Repository;

import com.Library_Management_System.Entity.BorrowRecords;

import jakarta.transaction.Transactional;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BorrowRecordsRepo extends JpaRepository<BorrowRecords,Long> {
	
    @Modifying
    @Transactional
    @Query("UPDATE BorrowRecords s SET s.returnDate = ?3 WHERE s.user.id = ?1 AND s.book.id = ?2")
    public void updateReturnDate(long userId, long bookId, LocalDate returnDate);

}
