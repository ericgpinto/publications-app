package com.ericpinto.publicationsapp.domain.service;

import com.ericpinto.publicationsapp.domain.model.User;
import com.ericpinto.publicationsapp.domain.service.exceptions.ObjectNotFoundException;
import com.ericpinto.publicationsapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("User não encontrado"));
    }

    public User create(User user){
        return userRepository.insert(user);
    }

    public void delete(String id){
        findById(id);
        userRepository.deleteById(id);
    }

    public User update(User obj){
        User updatedUser = findById(obj.getId());
        updateData(updatedUser, obj);
        return userRepository.save(updatedUser);
    }

    private void updateData(User updatedUser, User obj) {

        updatedUser.setName(obj.getName());
        updatedUser.setEmail(obj.getEmail());
    }

    public User fromUser(User user){
        return User.builder()
                .id(user.getId()).name(user.getName()).email(user.getEmail())
                .build();
    }




}
