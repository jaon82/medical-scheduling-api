package com.medicalscheduling.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicalscheduling.api.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
