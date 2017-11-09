package com.springsecurity.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springsecurity.basic.domain.Roles;

public interface UserAuthorityRepository extends JpaRepository<Roles, String> {

     Roles findByOrderByName(String name);
}