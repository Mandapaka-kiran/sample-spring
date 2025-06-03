package com.sample.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.controller.UserController;
import com.sample.entity.User;
import com.sample.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.LocalTime;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//for single context use WebMvcTest and MockMvc
@WebMvcTest(UserController.class)
@Slf4j
public class TestUserController {

    UserController userController;
    @Autowired
    MockMvc mockMvc;

    //Need to inject MockitoBean not Mock
    @MockitoBean
    UserService userService;
    ObjectMapper objectMapper;

    //use BeforeEach for Initilize the values
    @BeforeEach
    void setUp(){
        User user1=new User(1,"Arun","arun1","arun@google.com");
        User user2=new User(1,"Baali","arun1","arun@google.com");
        User user3=new User(1,"Cathrin","arun1","arun@google.com");
        List<User> userList = List.of(user1,user2,user3);
        when(userService.findAll()).thenReturn(userList);
    }


    @Test
    @DisplayName("/api/v1/user/all-Success")
    public void testGetAll() throws Exception {
        long start=System.currentTimeMillis();
        log.info("execution start time is: "+System.currentTimeMillis());
        mockMvc.perform(get("/api/v1/user/all").with(user("user").roles("USER")))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$",hasSize(3)))
                .andExpect(jsonPath("$[0].name").value("Arun"))
                .andExpect(jsonPath("$[0].id").value(1));
        long end=System.currentTimeMillis();
        log.info("execution end time: "+System.currentTimeMillis());
        log.info("Total execution time is: "+ (end-start));

    }
}
