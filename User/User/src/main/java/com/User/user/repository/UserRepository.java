package com.User.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.User.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
