package com.Library_Management_System.Service;

import com.Library_Management_System.DTOs.BorrowRecordsDTO;
import com.Library_Management_System.DTOs.UserDTO;
import com.Library_Management_System.Entity.Books;
import com.Library_Management_System.Entity.BorrowRecords;
import com.Library_Management_System.Entity.Users;
import com.Library_Management_System.Exception_Handling.NotFoundException;
import com.Library_Management_System.Repository.BooksRepo;
import com.Library_Management_System.Repository.BorrowRecordsRepo;
import com.Library_Management_System.Repository.UsersRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class BorrowRecordsService {
    @Autowired
    BorrowRecordsRepo borrowRecordsRepo;
    @Autowired
    ObjectMapper objectMapper;
    
    @Autowired
    private UsersRepo usersRepository;

    @Autowired
    private BooksRepo booksRepository;

    public Optional<BorrowRecordsDTO> getById(long id) throws NotFoundException {
    	if(borrowRecordsRepo.existsById(id)) {
    		return borrowRecordsRepo.findById(id).map(BorrowRecordsDTO::new);
    	}
        throw new NotFoundException("Record With Id: "+id+" is not Found");
    }

    public List<BorrowRecordsDTO> getAllRecords() {
        return borrowRecordsRepo.findAll()
        		.stream()
        		.map(BorrowRecordsDTO::new)
        		.toList();
        
    }


    public BorrowRecordsDTO addNewRecord(Long userId, Long bookId, LocalDate issueDate, LocalDate dueDate) throws NotFoundException {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        Books book = booksRepository.findById(bookId).orElseThrow(() -> new NotFoundException("Book not found"));
        BorrowRecords record = new BorrowRecords();
        record.setUser(user);
        record.setBook(book);
        record.setIssueDate(issueDate);
        record.setDueDate(dueDate);
        record.setReturnDate(null);
        record.setFineAmount(0);
        BorrowRecords savedRecords=borrowRecordsRepo.save(record);
        return new BorrowRecordsDTO(savedRecords);
    }

    
    public BorrowRecordsDTO modifyRecord(Long id, Map<String, Object> partialRecord) throws NotFoundException {
        Optional<BorrowRecords> records = borrowRecordsRepo.findById(id);
        if (records.isEmpty()) {
            throw new NotFoundException("Record With Id: " + id + " is not Found");
        }
        BorrowRecords existingRecord = records.get();
        if (partialRecord.containsKey("userId")) {
            Object userIdObj = partialRecord.get("userId");
            Long userId = ((Number) userIdObj).longValue(); 
            Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
            existingRecord.setUser(user);
            partialRecord.remove("userId");
        }
        if (partialRecord.containsKey("bookId")) {
            Object bookIdObj = partialRecord.get("bookId");
            Long bookId = ((Number) bookIdObj).longValue();
            Books book = booksRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Book not found"));
            existingRecord.setBook(book);
            partialRecord.remove("bookId");
        }
        
        BorrowRecords patchedRecord = apply(existingRecord, partialRecord);
        BorrowRecords savedRecords=borrowRecordsRepo.save(patchedRecord);
        BorrowRecordsDTO dbRecordsDTO=new BorrowRecordsDTO(savedRecords);
        return dbRecordsDTO;
    }
    
    private BorrowRecords apply(BorrowRecords tempEmployee, Map<String, Object> patchPayLoad) {
        ObjectNode tempObjectNode=objectMapper.convertValue(tempEmployee,ObjectNode.class);
        ObjectNode patchPayLoadNode=objectMapper.convertValue(patchPayLoad, ObjectNode.class);
        tempObjectNode.setAll(patchPayLoadNode);
        return objectMapper.convertValue(tempObjectNode, BorrowRecords.class);
    }
    

    public Optional<BorrowRecordsDTO> deleteRecordById(long id) throws NotFoundException {
        if(borrowRecordsRepo.existsById(id)) {
            Optional<BorrowRecordsDTO> record = borrowRecordsRepo.findById(id).map(BorrowRecordsDTO::new);
            borrowRecordsRepo.deleteById(id);
            return record;
        }
        throw new NotFoundException("Record With Id: "+id+" is not Found");
    }
}
