package com.service.impl;

import com.domain.User;
import com.repository.UserRepository;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(final User user) {
        return repository.save(user);
    }

    @Override
    public User find(final int id) {
        return repository.findById(id).orElseGet(null);
    }

    @Override
    public void update(final User user) {
        repository.save(user);
    }

    @Override
    public void delete(final int id) {
        repository.deleteById(id);
    }
}
