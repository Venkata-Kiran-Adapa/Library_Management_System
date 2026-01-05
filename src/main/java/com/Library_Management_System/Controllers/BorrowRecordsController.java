package com.Library_Management_System.Controllers;

import com.Library_Management_System.DTOs.BorrowRecordsDTO;
import com.Library_Management_System.DTOs.BorrowRecordsRequest;
import com.Library_Management_System.Entity.BorrowRecords;
import com.Library_Management_System.Entity.Users;
import com.Library_Management_System.Exception_Handling.NotFoundException;
import com.Library_Management_System.Service.BorrowRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/borrowRecords")
public class BorrowRecordsController {

    @Autowired
    BorrowRecordsService borrowRecordsService;

    @GetMapping("/{id}")
    public BorrowRecordsDTO getById(@PathVariable Long id) throws NotFoundException{
        return borrowRecordsService.getById(id).get();
    }

    @GetMapping("")
    public List<BorrowRecordsDTO> getAllRecords(){
        return borrowRecordsService.getAllRecords();
    }

    
    @PostMapping("")
    public BorrowRecordsDTO addNewRecord(@RequestBody BorrowRecordsRequest request) throws NotFoundException{
        return borrowRecordsService.addNewRecord(request.userId, request.bookId,LocalDate.now());
    }

    @PatchMapping("/{id}")
    public BorrowRecordsDTO modifyRecord(@PathVariable Long id , @RequestBody Map<String,Object> partialRecord)throws NotFoundException {
        return borrowRecordsService.modifyRecord(id,partialRecord);
    }

    @DeleteMapping("/{id}")
    public BorrowRecordsDTO deleteRecordById(@PathVariable Long id) throws NotFoundException {
        return borrowRecordsService.deleteRecordById(id).get();
    }


}
