package com.service;

import com.domain.Man;

import java.util.List;

public interface ManService {

    Man save(Man man);

    Man find(int id);

    void update(Man man);

    void delete(int id);

    List<Man> getAll();

    Man findByNameAndAge(String name, int age);

    List<Man> findAllByName(String name);

    List<Man> findByNameAndAgeAndSortByName(String name);
}
