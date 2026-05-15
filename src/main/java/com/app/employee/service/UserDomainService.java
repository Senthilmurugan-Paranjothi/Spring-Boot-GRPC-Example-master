package com.app.employee.service;

import com.app.employee.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserDomainService {

    public User getUserById(String userId) {
        // Mock data — replace with DB call
        User user = new User();
        user.setUserId(userId);
        user.setFirstName("Senthilmurugan");
        user.setLastName("Paranjothi");
        user.setEmail("senthilmurugan.paranjothi@gmail.com");
        user.setPhone("555-1234");
        user.setAddress("123 Main St");
        user.setCity("Louisville");
        user.setState("KY");
        user.setCountry("USA");
        user.setZipCode("40299");
        user.setRole("ADMIN");
        user.setStatus("ACTIVE");
        return user;
    }
}
