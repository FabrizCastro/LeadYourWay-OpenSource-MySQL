package com.lyw.LeadYourWay.repository;

import com.lyw.LeadYourWay.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUserEmail(String user_email);
    boolean existsByUserEmail(String user_email);
}
