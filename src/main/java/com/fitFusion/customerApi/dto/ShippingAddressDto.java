package com.fitFusion.customerApi.dto;

import lombok.Data;

@Data
public class ShippingAddressDto {
    private Long addrId;
    private String hno;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String addrType;
    private String deleteSw;
}
