package com.sample.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.controller.Controller;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Path;

//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(Controller.class)
public class TestController {
    @Autowired
    Controller controller;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    @DisplayName("/api/test-SUCCESS")
    public void test() throws Exception{
        mockMvc.perform(get("/api/test")
                //for actual security
                //.with(httpBasic("user","d6a94314-83d3-4576-94e4-3103c3937cfb")))

                //to mock the authentication
                .with(user("user").roles("USER")))
                .andExpect(status().isFound())
                .andExpect(content().string("Welcome"));
    }
    //feature1
    /*
        Description for feature 1....
     */
}
