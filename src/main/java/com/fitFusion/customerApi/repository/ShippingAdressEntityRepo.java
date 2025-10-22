package com.fitFusion.customerApi.repository;

import com.fitFusion.customerApi.entity.ShippingAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingAdressEntityRepo extends JpaRepository<ShippingAddressEntity, Long> {
}
