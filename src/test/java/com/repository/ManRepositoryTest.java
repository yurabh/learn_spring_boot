package com.repository;

import com.domain.Email;
import com.domain.Man;
import com.domain.Phone;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ManRepositoryTest {

    @Autowired
    private ManRepository manRepository;

    private Man man;

    @Before
    public void beforeTest() {
        man = new Man();
        man.setName("oleg");
        man.setAge(20);
        man.setPhone(new Phone(0, "111"));
        man.setEmails(Collections.singletonList(new Email(0, "yura@gmail.com", man)));
    }

    @Test
    public void shouldFindMan() {
        final Man savedMan = manRepository.save(man);
        final int id = savedMan.getId();
        Assert.assertEquals(id, manRepository.findById(id).get().getId());
    }

    @Test
    public void shouldUpdateMan() {
        man.setAge(100);
        man.setPhone(new Phone(0, "222"));
        Assert.assertEquals(man, manRepository.save(man));
    }

    @Test
    public void shouldDeleteMan() {
        final Man savedMan = manRepository.save(man);
        manRepository.deleteById(savedMan.getId());
        Assert.assertEquals(0, manRepository.count());
    }

    @Test
    public void shouldGetAll() {
        manRepository.save(man);
        final List<Man> listOfMen = createListOfMen();
        manRepository.save(listOfMen.get(1));
        manRepository.save(listOfMen.get(2));
        manRepository.save(listOfMen.get(3));
        manRepository.save(listOfMen.get(4));
        Assert.assertEquals(listOfMen, manRepository.findAll());
    }

    @Test
    public void shouldFindManByNameAndAge() {
        manRepository.save(man);
        final List<Man> listOfMen = createListOfMen();
        manRepository.save(listOfMen.get(1));
        manRepository.save(listOfMen.get(2));
        manRepository.save(listOfMen.get(3));
        manRepository.save(listOfMen.get(4));
        Assert.assertEquals(man, manRepository.findByNameAndAge("oleg", 20));
    }

    private List<Man> createListOfMen() {
        final List<String> dogs = new ArrayList<>();
        dogs.add("Hi");
        final List<Email> emails = new ArrayList<>();
        emails.add(new Email(0, "111", man));
        Man manOne = new Man(0, "andriy", 20, dogs, new Phone(0, "111"), emails);
        Man manTwo = new Man(0, "yura", 21, dogs, new Phone(0, "111"), emails);
        Man manThree = new Man(0, "kola", 22, dogs, new Phone(0, "111"), emails);
        Man manFour = new Man(0, "nastja", 23, dogs, new Phone(0, "111"), emails);
        return Arrays.asList(man, manOne, manTwo, manThree, manFour);
    }
}
