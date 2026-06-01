package com.ecom.management.dto;

import com.ecom.management.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {

    private Long userid;
    private String name;
    private String phoneno;
    private String emailid;
    private Gender gender;
    private String address;



}
