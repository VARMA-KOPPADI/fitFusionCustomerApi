package com.fitFusion.customerApi.mapper;

import com.fitFusion.customerApi.dto.ShippingAdressDto;
import com.fitFusion.customerApi.entity.ShippingAddressEntity;
import org.modelmapper.ModelMapper;

public class AddressMapper {

    public static final ModelMapper mappper = new ModelMapper();

    public static ShippingAddressEntity convertToEntity(ShippingAdressDto adressDto) {

        return mappper.map(adressDto, ShippingAddressEntity.class);

    }

    public static ShippingAdressDto convertToDto(ShippingAddressEntity entity) {
        return mappper.map(entity, ShippingAdressDto.class);
    }
}
