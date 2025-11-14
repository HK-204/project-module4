package com.codegym.service;

import com.codegym.entity.User;

public interface UserService {
    void save(User user);
    User findByUsername (String username);
}
