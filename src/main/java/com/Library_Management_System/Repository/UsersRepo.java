package com.Library_Management_System.Repository;

import com.Library_Management_System.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepo  extends JpaRepository<Users,Long> {

    Optional<Users> findByUserEmail(String user_email);
}
