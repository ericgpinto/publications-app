//package com.ericpinto.publicationsapp.service;
//
//import com.ericpinto.publicationsapp.domain.model.User;
//import com.ericpinto.publicationsapp.domain.service.UserService;
//import com.ericpinto.publicationsapp.repository.UserRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentMatchers;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//
//public class UserServiceTests {
//
//    @Mock
//    UserRepository userRepository;
//
//    @InjectMocks
//    UserService userService;
//
//    @Test
//    public void when_save_user_it_should_return_user(){
//        User user = User.builder()
//                .name("Eric Pinto").build();
//
//        Mockito.when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(new User());
//
//        User created = userService.create(user);
//        Assertions.assertThat(created.getName()).isSameAs(user.getName());
//    }
//}
