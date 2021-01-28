package com.ericpinto.publicationsapp.domain.service;

import com.ericpinto.publicationsapp.domain.model.Post;
import com.ericpinto.publicationsapp.domain.model.User;
import com.ericpinto.publicationsapp.domain.service.exceptions.ObjectNotFoundException;
import com.ericpinto.publicationsapp.repository.PostRepository;
import com.ericpinto.publicationsapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(String id) {
        return postRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Publicação não encontrada"));
    }

    public Post create(Post post){
        return postRepository.save(post);
    }

    public void delete(String id){
        findById(id);
        postRepository.deleteById(id);
    }

    public Post update(Post obj){
        Post updatedPost = findById(obj.getId());
        updateData(updatedPost, obj);
        return postRepository.save(updatedPost);
    }

    private void updateData(Post updatedPost, Post obj) {

        updatedPost.setDate(obj.getDate());
        updatedPost.setTitle(obj.getTitle());
        updatedPost.setBody(obj.getBody());
        updatedPost.setAuthor(obj.getAuthor());
    }

    public Post fromPost(Post post){
        return Post.builder()
                .id(post.getId())
                .date(post.getDate())
                .title(post.getTitle())
                .body(post.getBody())
                .author(post.getAuthor()).build();
    }
}
