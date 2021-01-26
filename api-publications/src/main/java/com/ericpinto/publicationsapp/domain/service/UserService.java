package com.ericpinto.publicationsapp.domain.service;

import com.ericpinto.publicationsapp.domain.model.User;
import com.ericpinto.publicationsapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User create(User user){
        return userRepository.insert(user);
    }
}