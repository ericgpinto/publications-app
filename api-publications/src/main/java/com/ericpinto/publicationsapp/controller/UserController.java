package com.ericpinto.publicationsapp.controller;

import com.ericpinto.publicationsapp.domain.model.User;
import com.ericpinto.publicationsapp.domain.service.UserService;
import com.ericpinto.publicationsapp.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/users")
@Api(value = "API REST Users")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping
    @ApiOperation(value = "Retorna uma lista de usuários")
    public ResponseEntity<List<User>> findAll(){
        List<User> list = userService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value ="/{id}")
    @ApiOperation(value = "Retorna um único usuario")
    public ResponseEntity<User> findById(@PathVariable String id){
        User obj = userService.findById(id);
        if(obj == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Cria um usuario")
    public User create(@RequestBody User user){
        User objUser = userService.create(user);
        return objUser;
    }

    @DeleteMapping(value ="/{id}")
    @ApiOperation(value = "Exclui um usuario")
    public ResponseEntity<Void> delete(@PathVariable String id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    @ApiOperation(value = "Exclui varios usuários")
    public ResponseEntity<Void> deleteAll(@RequestBody List<String> ids){
        ids.stream().forEach(x -> userService.delete(x));
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value ="/{id}")
    @ApiOperation(value = "Edita um usuario")
    public ResponseEntity<Void> update(@RequestBody User user, @PathVariable String id){
        User obj = userService.fromUser(user);
        obj.setId(id);
        obj = userService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{id}")
    @ApiOperation(value = "Edita um campo especifico de um usuario")
    public ResponseEntity<Void> saveUser(@PathVariable String id, @RequestBody Map<Object, Object> fields){
        User user = userService.findById(id);
        fields.forEach((k, v) -> {
            Field field = ReflectionUtils.findField(User.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, user, v);
        });

        userService.update(user);
        return ResponseEntity.noContent().build();
    }

}
