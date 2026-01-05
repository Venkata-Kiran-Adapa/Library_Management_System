package com.Library_Management_System.Service;

import com.Library_Management_System.DTOs.BorrowRecordsDTO;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BorrowRecordsService {
	
    @Autowired
    BorrowRecordsRepo borrowRecordsRepo;
    
    @Autowired
    BookService bookService;
    
    @Autowired
    ObjectMapper objectMapper;
    
    @Autowired
    private UsersRepo usersRepository;
    
    @Autowired
    private UserService userService;  
    
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
    
    public BorrowRecordsDTO addNewRecord(Long userId, Long bookId,LocalDate reservationDate) throws NotFoundException {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        Books book = booksRepository.findById(bookId).orElseThrow(() -> new NotFoundException("Book not found"));
        BorrowRecords record = new BorrowRecords();
        record.setUser(user);
        if(!book.getBook_availability()) {
        	throw new NotFoundException("Book is not available");
        }
        record.setBook(book);
        record.setIssueDate(reservationDate);
        record.setDueDate(reservationDate.plusDays(15));
        record.setReturnDate(null);
        record.setFineAmount(0);
        book.setBook_availability(false);
        booksRepository.save(book);
        BorrowRecords savedRecords=borrowRecordsRepo.save(record);
        Map<String, Object> saveInUser=new HashMap<String, Object>();
        saveInUser.put("borrowRecords",record);
        userService.modifyUser(user.getUser_id(), saveInUser);        
        return new BorrowRecordsDTO(savedRecords);
    }

    public BorrowRecordsDTO modifyRecord(Long id, Map<String, Object> partialRecord) throws NotFoundException {
    	BorrowRecords existingRecord  = borrowRecordsRepo.findById(id).get();
        if (existingRecord==null) {
            throw new NotFoundException("Record With Id: " + id + " is not Found");
        }
        Users users=existingRecord.getUser();
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
        patchedRecord.setUser(users);
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
    
    public void changeReturnDate(long user_id,long book_id) throws NotFoundException{
    	borrowRecordsRepo.updateReturnDate(user_id,book_id,LocalDate.now());
    }
}
