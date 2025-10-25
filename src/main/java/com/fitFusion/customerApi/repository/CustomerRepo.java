package com.fitFusion.customerApi.repository;

import com.fitFusion.customerApi.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<CustomerEntity, Integer> {
    public CustomerEntity findByEmailAndPassword(String email, String password);
    public CustomerEntity findByEmail(String email);
}
