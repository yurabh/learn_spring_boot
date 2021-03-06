package com.repository;

import com.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql(scripts = {"classpath:data.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @Before
    public void beforeTest() {
        user = new User();
        user.setId(1);
        user.setUserName("yura");
        user.setPassword("111");
        user.setPhoneNumber("222");
        user.setActive(true);
    }

    @Sql(scripts = {"classpath:schema.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    public void shouldSaveUser() {
        Assert.assertEquals(user, userRepository.findById(1).get());
    }

    @Test
    public void shouldGetUser() {
        Assert.assertEquals(user, userRepository.findById(1).get());
    }

    @Test
    public void shouldUpdateUser() {
        user.setPhoneNumber("111");
        user.setPassword("222");
        user.setActive(false);
        user.setUserName("Michel");
        Assert.assertEquals(user, userRepository.save(user));
    }

    @Test
    public void shouldDeleteUser() {
        userRepository.deleteById(1);
        Assert.assertEquals(4, userRepository.count());
    }
}
