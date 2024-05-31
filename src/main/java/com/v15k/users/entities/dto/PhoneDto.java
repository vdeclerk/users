package com.v15k.users.entities.dto;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class PhoneDto {
    @Length(min = 3, max = 20)
    private final String number;
    @Length(min = 2, max = 10)
    private final String citycode;
    @Length(min = 2, max = 10)
    private final String countrycode;
}
