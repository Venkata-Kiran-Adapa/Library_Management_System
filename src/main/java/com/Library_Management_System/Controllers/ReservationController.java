package com.Library_Management_System.Controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Library_Management_System.DTOs.ReservationDTO;
import com.Library_Management_System.DTOs.ReservationRequest;
import com.Library_Management_System.Entity.Reservation;
import com.Library_Management_System.Exception_Handling.NotFoundException;
import com.Library_Management_System.Service.ReservationService;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
	
	@Autowired
	ReservationService reservationService;
	
	
	@PostMapping("")
	public ReservationDTO addNewReservation(@RequestBody ReservationRequest reservationRequest) throws NotFoundException {
		return reservationService.addNewReservation(reservationRequest.userId,reservationRequest.bookId,reservationRequest.reservationDate);
	}
	
	@GetMapping("/{id}")
	public Optional<ReservationDTO> getById(@PathVariable long id) throws NotFoundException {
		return reservationService.getById(id);
	}
	
	@GetMapping("")
	public List<ReservationDTO> getAll() {
		return reservationService.getAll();
	}
	
	@PatchMapping("/{id}")
	public ReservationDTO modifyReservation(@PathVariable Long id, @RequestBody Map<String, Object> partialRecord) throws NotFoundException{
		return reservationService.modifyReservation(id,partialRecord);
	}
	
	@DeleteMapping("/{id}")
	public ReservationDTO deleteById(@PathVariable Long id) throws NotFoundException {
		return reservationService.deleteById(id);
	}
}
