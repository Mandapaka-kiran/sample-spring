package com.sample.service;

import com.sample.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {
    @Autowired
    RestTemplate restTemplate;

    public UserService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }
    public List<User> findAll(){
        ResponseEntity<List<User>> response=restTemplate.exchange(
                "https://jsonplaceholder.typicode.com/users",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {}
        );return response.getBody();
    }

}
