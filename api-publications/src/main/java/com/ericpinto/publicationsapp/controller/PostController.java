package com.ericpinto.publicationsapp.controller;

import com.ericpinto.publicationsapp.domain.model.Post;
import com.ericpinto.publicationsapp.domain.model.User;
import com.ericpinto.publicationsapp.domain.service.PostService;
import com.ericpinto.publicationsapp.domain.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "api/posts")
@Api(value = "API REST Publications")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @GetMapping
    @ApiOperation(value = "Retorna uma lista de publicações")
    public ResponseEntity<List<Post>> findAll(){
        List<Post> list = postService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value ="/{id}")
    @ApiOperation(value = "Retorna uma única publicação")
    public ResponseEntity<Post> findById(@PathVariable String id){
        Post obj = postService.findById(id);
        if(obj == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Cria uma publicação")
    public ResponseEntity<Post> create(@RequestBody Post post){
        Post objPost = postService.create(post);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objPost.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value ="/{id}")
    @ApiOperation(value = "Exclui uma publicação")
    public ResponseEntity<Void> delete(@PathVariable String id){
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value ="/{id}")
    @ApiOperation(value = "Edita uma publicação")
    public ResponseEntity<Void> update(@RequestBody Post post, @PathVariable String id){
        Post obj = postService.fromPost(post);
        obj.setId(id);
        obj = postService.update(obj);
        return ResponseEntity.noContent().build();
    }
}
