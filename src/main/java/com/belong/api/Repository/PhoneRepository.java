package com.belong.api.Repository;

import com.belong.api.Entity.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<PhoneNumber, String> {
    PhoneNumber findByPhoneNumber(String phoneNumber);
}
