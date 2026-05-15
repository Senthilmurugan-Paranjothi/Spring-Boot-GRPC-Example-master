package com.app.employee.model;

import lombok.Data;

@Data
public class User {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String role;
    private String status;
}
