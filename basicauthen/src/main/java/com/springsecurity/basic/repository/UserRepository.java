package com.springsecurity.basic.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springsecurity.basic.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

  
    User findOneByEmail(String email);

    User findOneByLogin(String login);

    Page<User> findAllByLoginNot(Pageable pageable, String login);
}