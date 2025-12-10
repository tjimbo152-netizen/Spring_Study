package com.example.demo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer>{
	// 1-6課題
    // E-Mail(Entityのフィールド名)でレコードが存在するかチェックする
    boolean existsByEmail(String email);
}