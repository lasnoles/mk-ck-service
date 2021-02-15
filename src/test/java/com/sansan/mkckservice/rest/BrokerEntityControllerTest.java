package com.sansan.mkckservice.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class BrokerEntityControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void create_broker(){
//        mvc.perform(post("/api/v1/create")
//        .contentType(MediaType.APPLICATION_JSON)
//        .content("{}")
//        .andExpect(status().is2xxSuccessful()));
    }

    @Test
    public void update_broker(){

    }

    @Test
    public void get_brokers(){

    }

    @Test
    public void delete_broker(){

    }
}
