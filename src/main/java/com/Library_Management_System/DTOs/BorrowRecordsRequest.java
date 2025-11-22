package com.Library_Management_System.DTOs;

import java.time.LocalDate;

public class BorrowRecordsRequest {

	public Long userId;
    public Long bookId;
    public LocalDate issueDate;
    public LocalDate dueDate;

}
