package com.fitFusion.customerApi.mapper;

import com.fitFusion.customerApi.dto.CustomerDto;
import com.fitFusion.customerApi.entity.CustomerEntity;
import org.modelmapper.ModelMapper;

public class CustomerMapper {
    public static final ModelMapper mapper = new ModelMapper();

    public static CustomerDto convertToDto(CustomerEntity entity) {
        return mapper.map(entity, CustomerDto.class);
    }

    public static CustomerEntity convertToEntity(CustomerDto customerDto){
        return mapper.map(customerDto, CustomerEntity.class);
    }
}
