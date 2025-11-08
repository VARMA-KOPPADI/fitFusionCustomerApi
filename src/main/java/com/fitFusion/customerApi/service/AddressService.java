package com.fitFusion.customerApi.service;

import com.fitFusion.customerApi.dto.ShippingAddressDto;

import java.util.List;

public interface AddressService {
    public ShippingAddressDto saveAddress(ShippingAddressDto addressDto, Integer customerId);
    public ShippingAddressDto getAddress(Integer id);
    public List<ShippingAddressDto> getCustomerAddresses(Integer customerId);
    public boolean deleteAddress(Integer addressId);
}
