package com.Library_Management_System.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.Library_Management_System.DTOs.LoginRequest;
import com.Library_Management_System.DTOs.UserDTO;
import com.Library_Management_System.DTOs.UserSignupDTO;
import com.Library_Management_System.Entity.Users;
import com.Library_Management_System.Exception_Handling.NotFoundException;
import com.Library_Management_System.Service.UserService;
import com.Library_Management_System.Util.JWTUtil;

@RestController
@RequestMapping("/api/authenticate")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    UserService userService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

  

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/login")
    public String authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getUserEmail(),
                    loginRequest.getPassword()
                )
            );
            return jwtUtil.generateToken(loginRequest.getUserEmail());

        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid credentials");
        }
    }
        
    @PostMapping("/signup")
    public UserDTO signInUser(@RequestBody UserSignupDTO dto) throws NotFoundException {
    	Optional<UserDTO> existingUser = userService.getUserByEmail(dto.getUserEmail());
    	if (existingUser.isEmpty()) {
    	    Users user = new Users();
    	    user.setUser_name(dto.getUserName());
    	    user.setUserEmail(dto.getUserEmail());
    	    user.setPassword(passwordEncoder.encode(dto.getRawPassword()));
    	    user.setRole(dto.getRole());
    	    return userService.addUser(user);
    	} else {
      	    throw new NotFoundException("User with "+ dto.getUserEmail()+" already Exists");
    	}
    	}
       
}
