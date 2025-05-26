package com.sample.controllerTest;

import com.sample.entity.User;
import com.sample.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TestUserService {

       UserService userService;

    @Mock
    RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        userService = new UserService(restTemplate);
    }


    @Test
    public void findAllUsers(){
        //Arrange
        User user1 =User.builder().id(1).email("one@gmail.com").name("one").username("oneUsername").build();
        User user2 =User.builder().id(2).email("two@gmail.com").name("two").username("twoUsername").build();
        List<User> userList=new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        ResponseEntity<List<User>> responseEntity = new ResponseEntity<>(userList, HttpStatus.OK);

        when(restTemplate.exchange(
                eq("https://jsonplaceholder.typicode.com/users"),
                eq(HttpMethod.GET),
                isNull(),
                any(ParameterizedTypeReference.class)))
                .thenReturn(responseEntity);
        //act
        List<User> actualUsers=userService.findAll();

        //Assert
        assertNotNull(actualUsers);
        assertEquals(2,actualUsers.size());
        assertEquals(1,actualUsers.get(0).getId());
        assertEquals("oneUsername",actualUsers.get(0).getUsername());
        verify(restTemplate,times(1))
                .exchange(eq("https://jsonplaceholder.typicode.com/users")
                ,eq(HttpMethod.GET)
                ,isNull()
                ,any(ParameterizedTypeReference.class));

    }
}
