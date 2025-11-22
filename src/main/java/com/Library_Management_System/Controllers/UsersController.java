package com.Library_Management_System.Controllers;

import com.Library_Management_System.DTOs.UserDTO;
import com.Library_Management_System.Entity.Users;
import com.Library_Management_System.Exception_Handling.NotFoundException;
import com.Library_Management_System.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public Optional<UserDTO> getUserById(@PathVariable long id) throws NotFoundException {
       return userService.getUserById(id);
    }

    @GetMapping("/email/{user_email}")
    public Optional<UserDTO> getUserByEmail(@PathVariable String user_email) throws NotFoundException {
    	Optional<UserDTO> user=userService.getUserByEmail(user_email);
    	if(user==null){
            throw new NotFoundException("User With email: "+user_email+" is not Found");
        }
        return user;
    }

    @GetMapping("")
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @Transactional
    @PatchMapping("/{id}")
    public UserDTO modifyUser(@PathVariable long id ,@RequestBody Map<String,Object> partialUser)throws NotFoundException {
        return userService.modifyUser(id,partialUser);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public Optional<UserDTO> deleteUserById(@PathVariable long id) throws NotFoundException {
        return userService.deleteUserById(id);
    }
}
