package com.sampleLogin.userlogin.service;

import com.sampleLogin.userlogin.dtos.CreateUserRequest;
import com.sampleLogin.userlogin.entity.User;

public interface UserService {
    User createUser(CreateUserRequest req) throws IllegalArgumentException;
    User getByUsername(String username);
    boolean existsByUsername(String username);
    boolean validateCredentials(String username, String password);
}
