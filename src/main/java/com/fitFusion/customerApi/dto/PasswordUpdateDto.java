package com.fitFusion.customerApi.dto;

import lombok.Data;

@Data
public class PasswordUpdateDto {
    private String email;
    private String newPassword;
    private String conformPassword;
}
