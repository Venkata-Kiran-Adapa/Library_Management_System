package com.Library_Management_System.Repository;

import com.Library_Management_System.Entity.BorrowRecords;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRecordsRepo extends JpaRepository<BorrowRecords,Long> {
}
