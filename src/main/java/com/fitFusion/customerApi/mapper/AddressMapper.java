package com.fitFusion.customerApi.mapper;

import com.fitFusion.customerApi.dto.ShippingAddressDto;
import com.fitFusion.customerApi.entity.ShippingAddressEntity;
import org.modelmapper.ModelMapper;

public class AddressMapper {

    public static final ModelMapper mappper = new ModelMapper();

    public static ShippingAddressEntity convertToEntity(ShippingAddressDto adressDto) {

        return mappper.map(adressDto, ShippingAddressEntity.class);

    }

    public static ShippingAddressDto convertToDto(ShippingAddressEntity entity) {
        return mappper.map(entity, ShippingAddressDto.class);
    }
}
