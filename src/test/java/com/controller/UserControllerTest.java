package com.controller;

import com.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .build();
    }

    @Test
    public void shouldSaveUser() throws Exception {
        final User user = new User(1, "Andrea", "10", "20", true);

        Mockito.doReturn(user).
                when(userService).save(any(User.class));

        String json = new ObjectMapper().writeValueAsString(user);

        mockMvc
                .perform(post("/user/save")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldFindUser() throws Exception {
        final User user = new User(0, "Andrea", "10", "20", true);

        Mockito.doReturn(user).
                when(userService).find(ArgumentMatchers.anyInt());

        mockMvc
                .perform(get("/user/get/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(0))
                .andExpect(jsonPath("userName").value("Andrea"))
                .andExpect(jsonPath("password").value("10"))
                .andExpect(jsonPath("phoneNumber").value("20"))
                .andExpect(jsonPath("active").value(true))
                .andReturn();
    }

    @Test
    public void shouldUpdateUser() throws Exception {
        final User user = new User(1, "Yura", "11", "22", false);

        Mockito.doNothing().
                when(userService).update(any(User.class));

        String json = new ObjectMapper().writeValueAsString(user);

        mockMvc
                .perform(put("/user/update")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldDeleteUser() throws Exception {
        Mockito.doNothing().
                when(userService).delete(anyInt());

        mockMvc
                .perform(delete("/user/delete/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}
