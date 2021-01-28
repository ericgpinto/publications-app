package com.ericpinto.publicationsapp.repository;

import com.ericpinto.publicationsapp.domain.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
}
