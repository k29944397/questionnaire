package com.example.questtionnaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.questtionnaire.entity.User;

@Repository
public interface UserDao extends JpaRepository<User ,String>{

}
