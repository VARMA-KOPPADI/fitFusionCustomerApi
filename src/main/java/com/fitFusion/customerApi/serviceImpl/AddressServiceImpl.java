package com.fitFusion.customerApi.serviceImpl;

import com.fitFusion.customerApi.dto.ShippingAddressDto;
import com.fitFusion.customerApi.entity.CustomerEntity;
import com.fitFusion.customerApi.entity.ShippingAddressEntity;
import com.fitFusion.customerApi.mapper.AddressMapper;
import com.fitFusion.customerApi.repository.CustomerRepo;
import com.fitFusion.customerApi.repository.ShippingAddressRepo;
import com.fitFusion.customerApi.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class AddressServiceImpl implements AddressService {
    @Autowired
    private ShippingAddressRepo addressRepo;
    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public ShippingAddressDto saveAddress(ShippingAddressDto addressDto, Integer customerId) {
        CustomerEntity customer = customerRepo.findById(customerId).orElseThrow();
        ShippingAddressEntity addressEntity = AddressMapper.convertToEntity(addressDto);
        addressEntity.setCustomer(customer);
        addressEntity.setDeleteSw("N");
        ShippingAddressEntity save = addressRepo.save(addressEntity);
        return AddressMapper.convertToDto(save);
    }

    @Override
    public ShippingAddressDto getAddress(Integer id) {
        Optional<ShippingAddressEntity> byId = addressRepo.findById(id);
        if(byId.isPresent()){
            return AddressMapper.convertToDto(byId.get());
        }
        return null;
    }

/*    @Override
    public List<ShippingAddressDto> getCustomerAllAddress(Integer customerId) {
        Optional<CustomerEntity> customer = customerRepo.findById(customerId);
        if(customer.isPresent()){
            List<ShippingAddressEntity> byCustomer = addressRepo.findByCustomer(customerId);
            return byCustomer.stream().filter(obj->obj.getDeleteSw().equals("N")).map(AddressMapper::convertToDto).toList();
        }
        return null;
    }*/
@Override
public List<ShippingAddressDto> getCustomerAddresses(Integer customerId) {
    if (!customerRepo.existsById(customerId)) {
        throw new RuntimeException("Customer not found with ID: " + customerId);
    }
    return addressRepo.findByCustomerCustomerIdAndDeleteSw(customerId, "N")
            .stream()
            .map(AddressMapper::convertToDto)
            .toList();
}

    @Override
    public boolean deleteAddress(Integer addressId) {
        Optional<ShippingAddressEntity> byId = addressRepo.findById(addressId);
        if(byId.isPresent()){
            ShippingAddressEntity addressEntity = byId.get();
            addressEntity.setDeleteSw("Y");
            addressRepo.save(addressEntity);
            return true;
        }
        return false;
    }
}
