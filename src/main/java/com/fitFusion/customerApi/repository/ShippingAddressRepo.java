package com.fitFusion.customerApi.repository;

import com.fitFusion.customerApi.entity.ShippingAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShippingAddressRepo extends JpaRepository<ShippingAddressEntity, Integer> {
   // public List<ShippingAddressEntity> findByCustomer(Integer id);
    public List<ShippingAddressEntity> findByCustomerCustomerIdAndDeleteSw(Integer id, String s);
}
