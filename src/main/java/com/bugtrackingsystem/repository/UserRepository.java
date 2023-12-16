package com.bugtrackingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bugtrackingsystem.entity.User;
import com.bugtrackingsystem.util.UserRoleEnum;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailIgnoreCaseAndPassword(String email, String password);
    List<User> findByRole(UserRoleEnum role);
    List<User> findByIdNot(Long id);
    boolean existsByEmailIgnoreCaseAndPassword(String email, String password);
    Boolean existsByEmail(String email);

}
