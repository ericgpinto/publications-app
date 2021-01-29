package com.ericpinto.publicationsapp.controller;

import com.ericpinto.publicationsapp.domain.model.User;
import com.ericpinto.publicationsapp.domain.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.hamcrest.CoreMatchers.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;

import org.springframework.restdocs.JUnitRestDocumentation;
import static org.springframework.restdocs.request.RequestDocumentation.*;

import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(UserController.class)
@AutoConfigureRestDocs(outputDir = "build/snippets")
public class UserControllerTest {

    List<User> users = new ArrayList<>();

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    UserService userService;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void findAllShouldReturnListOfUsers() throws Exception {

        User firstUser = User.builder()
                .id("6010a4bd4bd17b16038a602e")
                .name("Éric Pinto")
                .email("ericgrandopinto@gmail.com").build();

        users.add(firstUser);

        when(userService.findAll()).thenReturn(users);
        mvc.perform(get("/api/users").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", is("6010a4bd4bd17b16038a602e")))
                .andExpect(jsonPath("$.[0].name", is("Éric Pinto")))
                .andExpect(jsonPath("$.[0].email", is("ericgrandopinto@gmail.com")))

                .andDo(document("{method-name}", requestParameters(
                        parameterWithName("page").description("The page to retrieve").optional(),
                        parameterWithName("size").description("The number of elements within a single page").optional()
                ), responseFields(
                        fieldWithPath("[].id").description("The unique identifier of the user"),
                        fieldWithPath("[].name").description("The name of the user"),
                        fieldWithPath("[].email").description("The email of the user"))));
    }
}

