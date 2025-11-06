package com.sampleLogin.userlogin.service;

import com.sampleLogin.userlogin.dtos.CreateUserRequest;
import com.sampleLogin.userlogin.entity.User;
import com.sampleLogin.userlogin.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository repo;


    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public User createUser(CreateUserRequest req) {
        if (repo.existsByUsername(req.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        User u = new User();
        u.setUsername(req.getUsername());
        u.setPassword(req.getPassword());
        u.setFirstName(req.getFirstName());
        u.setLastName(req.getLastName());
        u.setAge(req.getAge());
        u.setEmail(req.getEmail());
        u.setAddress(req.getAddress());
        u.setPhone(req.getPhone());
        return repo.saveAndFlush(u);
    }

    @Override
    public User getByUsername(String username) {
        return repo.findByUsername(username).orElse(null);
    }

    @Override
    public boolean existsByUsername(String username) {
        return repo.existsByUsername(username);
    }

    @Override
    public boolean validateCredentials(String username, String password) {
        return repo.findByUsername(username)
                .map(u -> u.getPassword().equals(password))
                .orElse(false);
    }
}
