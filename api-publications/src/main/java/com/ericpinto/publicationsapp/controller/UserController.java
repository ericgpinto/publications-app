package com.ericpinto.publicationsapp.controller;

import com.ericpinto.publicationsapp.domain.model.User;
import com.ericpinto.publicationsapp.domain.service.UserService;
import com.ericpinto.publicationsapp.domain.service.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        List<User> list = userService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value ="/{id}")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> findById(@PathVariable String id){
        User obj = userService.findById(id);
        if(obj == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user){
        User objUser = userService.create(user);
        return objUser;
    }
}
