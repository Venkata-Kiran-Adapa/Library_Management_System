package com.Library_Management_System.DTOs;

import com.Library_Management_System.Entity.Users;
import com.Library_Management_System.Enums.Role;

public class UserDTO {
    private Long user_id;
    private String user_name;
    private String userEmail;
    private Role role;

    public UserDTO(Users user) {
        this.user_id = user.getUser_id();
        this.user_name = user.getUser_name();
        this.userEmail = user.getUserEmail();
        this.role = user.getRole();
    }

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}