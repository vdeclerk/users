package com.v15k.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.v15k.users.entities.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Integer> {
    
}
