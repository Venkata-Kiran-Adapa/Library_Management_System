package com.Library_Management_System.Service;

import com.Library_Management_System.DTOs.UserDTO;
import com.Library_Management_System.Entity.Users;
import com.Library_Management_System.Enums.Role;
import com.Library_Management_System.Exception_Handling.NotFoundException;
import com.Library_Management_System.Repository.UsersRepo;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UsersRepo usersRepo;
    
    @Autowired
    ObjectMapper objectMapper;

    public Optional<UserDTO> getUserById(long id) throws NotFoundException {
        if(usersRepo.existsById(id)){
            return  usersRepo.findById(id).map(UserDTO::new);
        }
        throw new NotFoundException("User With Id: "+id+" is not Found");
    }

    public Optional<UserDTO> getUserByEmail(String user_email) throws NotFoundException {
        Optional<UserDTO> user=usersRepo.findByUserEmail(user_email).map(UserDTO::new);
        return  user;
    }

    public List<UserDTO> getAllUsers(){
    	return usersRepo.findAll()
        .stream()             
        .map(UserDTO::new)    
        .toList();             
    }

    public UserDTO addUser(Users user){
    	Users savedUser = usersRepo.save(user);
    	return new UserDTO(savedUser);
    }

    public UserDTO modifyUser(long id, Map<String,Object>  partialUser) throws NotFoundException {
        Optional<UserDTO> user=usersRepo.findById(id).map(UserDTO::new);
        if(user.isEmpty()){
            throw new NotFoundException("User With Id: "+id+" is not Found");
        }
        if(partialUser.containsKey("user_id")){
            throw new NotFoundException("Primary Key Changing Error - Custom One");
        }
        Users patchedUser=apply(user,partialUser);
        Users savedUser = usersRepo.save(patchedUser);
        UserDTO dbUser=new UserDTO(savedUser);
        return dbUser;
    }

    private Users apply(Optional<UserDTO> tempEmployee, Map<String, Object> patchPayLoad) {
        ObjectNode tempObjectNode=objectMapper.convertValue(tempEmployee,ObjectNode.class);
        ObjectNode patchPayLoadNode=objectMapper.convertValue(patchPayLoad, ObjectNode.class);
        tempObjectNode.setAll(patchPayLoadNode);
        return objectMapper.convertValue(tempObjectNode, Users.class);
    }

    public Optional<UserDTO> deleteUserById(@PathVariable long id) throws NotFoundException {
        if(usersRepo.existsById(id)) {
            Optional<UserDTO> user = usersRepo.findById(id).map(UserDTO::new);
            if((user.get().getRole()).equals(Role.ADMIN)) {
            	throw new NotFoundException("You dont have the permission to delete the Admin");
            }
            usersRepo.deleteById(id);
            return user;
    }
        throw new NotFoundException("User With Id: "+id+" is not Found");
}
}
