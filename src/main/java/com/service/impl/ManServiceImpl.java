package com.service.impl;

import com.domain.Man;
import com.repository.ManRepository;
import com.service.ManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ManServiceImpl implements ManService {

    private final ManRepository repository;

    @Autowired
    public ManServiceImpl(ManRepository repository) {
        this.repository = repository;
    }

    @Override
    public Man save(final Man man) {
        man.getEmails().forEach(a -> a.setMan(man));
        return repository.save(man);
    }

    @Override
    public Man find(final int id) {
        return repository.findById(id).get();
    }

    @Override
    public void update(final Man man) {
        repository.save(man);
    }

    @Override
    public void delete(final int id) {
        repository.deleteById(id);
    }

    @Override
    public List<Man> getAll() {
        return repository.findAll();
    }

    @Override
    public Man findByNameAndAge(final String name, final int age) {
        return repository.findByNameAndAge(name, age);
    }

    @Override
    public List<Man> findAllByName(final String name) {
        return repository.findAllByNameLike(name);
    }

    @Override
    public List<Man> findByNameAndAgeAndSortByName(String name) {
        return repository.findByNameAndAgeAndSortByName(Sort.by(name));
    }
}
