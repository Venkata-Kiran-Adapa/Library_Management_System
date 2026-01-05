package com.Library_Management_System.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Library_Management_System.DTOs.BorrowRecordsDTO;
import com.Library_Management_System.DTOs.ReservationDTO;
import com.Library_Management_System.Entity.Books;
import com.Library_Management_System.Entity.Reservation;
import com.Library_Management_System.Entity.Users;
import com.Library_Management_System.Exception_Handling.NotFoundException;
import com.Library_Management_System.Repository.BooksRepo;
import com.Library_Management_System.Repository.ReservationRepo;
import com.Library_Management_System.Repository.UsersRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class ReservationService {
	
	 @Autowired
	 private UsersRepo usersRepository;
	 
	 @Autowired
	 private UserService userService;

     @Autowired
     private BooksRepo booksRepository;
     
     @Autowired
     private BorrowRecordsService borrowRecordsService;
     
     @Autowired
     private ReservationRepo reservationRepo;
     
     @Autowired
     ObjectMapper objectMapper;
     
	public ReservationDTO addNewReservation(Long userId, Long bookId, LocalDate reservationDate) throws NotFoundException {
		 Users user = usersRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
	     Books book = booksRepository.findById(bookId).orElseThrow(() -> new NotFoundException("Book not found"));
	     if(!book.getBook_availability()) {
	    	 throw new NotFoundException("Book is not available..!");
	     }
		 Reservation reservation=new Reservation();
		 reservation.setBook(book);
		 reservation.setUser(user);
		 LocalDate effectiveReservationDate = (reservationDate != null) ? reservationDate : LocalDate.now(); 
		 if (effectiveReservationDate.isBefore(LocalDate.now())) {
		        throw new NotFoundException("Reservation date must be today or in the future.");
		    }
		 reservation.setReservationDate(effectiveReservationDate);
		 Reservation savedReservation=reservationRepo.save(reservation);
		 Map<String, Object> saveInUser=new HashMap<String, Object>();
	     saveInUser.put("reservationRecords",reservation);
	     userService.modifyUser(user.getUser_id(), saveInUser);  
         borrowRecordsService.addNewRecord(userId, bookId,effectiveReservationDate);
		 return new ReservationDTO(savedReservation);
	}  
    
	public Optional<ReservationDTO> getById(long id) throws NotFoundException {
		if(reservationRepo.existsById(id)) {
			return  reservationRepo.findById(id).map(ReservationDTO::new);
		}
		throw new NotFoundException("Reservation With Id: " + id + " is not Found");
	}

	public List<ReservationDTO> getAll() {
		return reservationRepo.findAll()
				.stream()
				.map(ReservationDTO::new)
				.toList();
	}

	public ReservationDTO modifyReservation(Long id,Map<String, Object> partialRecord) throws NotFoundException {
		Optional<Reservation> records = reservationRepo.findById(id);
        if (records.isEmpty()) {
            throw new NotFoundException("Reservation With Id: " + id + " is not Found");
        }
        Reservation existingRecord = records.get();
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
        Reservation patchedRecord = apply(existingRecord, partialRecord);
        Reservation savedReservation=reservationRepo.save(patchedRecord);
        return new ReservationDTO(savedReservation);
		
	}
	  private Reservation apply(Reservation tempEmployee, Map<String, Object> patchPayLoad) {
	        ObjectNode tempObjectNode=objectMapper.convertValue(tempEmployee,ObjectNode.class);
	        ObjectNode patchPayLoadNode=objectMapper.convertValue(patchPayLoad, ObjectNode.class);
	        tempObjectNode.setAll(patchPayLoadNode);
	        return objectMapper.convertValue(tempObjectNode, Reservation.class);
	    }
	  
	  public ReservationDTO deleteById(Long id) throws NotFoundException {
	        if(reservationRepo.existsById(id)) {
	            Optional<ReservationDTO> record = reservationRepo.findById(id).map(ReservationDTO::new);
	            Books book = booksRepository.findById(record.get().getBook().getBook_id()).orElseThrow(() -> new NotFoundException("Book not found"));
	            book.setBook_availability(true);
	            borrowRecordsService.changeReturnDate(record.get().getUser().getUser_id(), book.getBook_id());
	            reservationRepo.deleteById(id);
	            return record.get();
	        }
	        throw new NotFoundException("Record With Id: "+id+" is not Found");
	    }

}
