package com.ericpinto.publicationsapp.controller;

import com.ericpinto.publicationsapp.domain.model.User;
import com.ericpinto.publicationsapp.domain.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.hamcrest.CoreMatchers.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    List<User> users = new ArrayList<>();

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    UserService userService;

    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        User firstUser = User.builder()
                .id("6010a4bd4bd17b16038a602e")
                .name("Éric Pinto")
                .email("ericgrandopinto@gmail.com").build();

        users.add(firstUser);
    }


//    @Test
//    public void shouldReturnAllUsers_whenAListOfUsersIsPassed() throws Exception {
//        List<User> userList = new ArrayList<>();
//        User firstUser = User.builder()
//                .id("6010a4bd4bd17b16038a602e")
//                .name("Éric Pinto")
//                .email("ericgrandopinto@gmail.com").build();
//
//        User secondUser = User.builder()
//                .id("6010c50c6892ae5d47c896c4")
//                .name("Régis Pinto")
//                .email("regispinto65@gmail.com").build();
//
//        userList.add(firstUser);
//        userList.add(secondUser);
//
//        when(userService.findAll()).thenReturn(userList);
//
//        mvc.perform(get("/users")
//            .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", Matchers.hasSize(2)))
//                .andExpect(jsonPath("$[0].id", is("6010a4bd4bd17b16038a602e")))
//                .andExpect(jsonPath("$[0].name", is("Éric Pinto")))
//                .andExpect(jsonPath("$[0].email", is("ericgrandopinto@gmail.com")))
//                .andExpect(jsonPath("$[1].id", is("6010c50c6892ae5d47c896c4")))
//                .andExpect(jsonPath("$[1].name", is("Régis Pinto")))
//                .andExpect(jsonPath("$[1].email", is("regispinto65@gmail.com")));
//    }
//
//    @Test
//    public void post_createsNewUser_andReturnsObjWith201() throws Exception {
//        User user = User.builder()
//                .id("6010a4bd4bd17b16038a602e")
//                .name("Eric Pinto")
//                .email("ericgrandopinto@gmail.com").build();
//
//        when(userService.create(ArgumentMatchers.any(User.class))).thenReturn(user);
//        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users")
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .accept(MediaType.APPLICATION_JSON)
//                .characterEncoding("UTF-8")
//                .content(this.mapper.writeValueAsBytes(user));
//
//        mvc.perform(builder).andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id", is("6010a4bd4bd17b16038a602e")))
//                .andExpect(jsonPath("$.name", is("Eric Pinto")))
//                .andExpect(content().string(this.mapper.writeValueAsString(user)));
//    }

    @Test
    public void should_ReturnNotFound_when_usernotfound() throws Exception {
        when(userService.findById("asdas")).thenReturn(users.get(0));
        mvc.perform(get("/users/asdasd")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
