package com.service;

import com.domain.User;

public interface UserService {

    User save(User user);

    User find(int id);

    void update(User user);

    void delete(int id);
}
