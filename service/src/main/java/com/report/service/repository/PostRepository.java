package com.report.service.repository;

import com.report.service.documnent.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface PostRepository extends MongoRepository<Post, String> {
    @Query("{profile : ?0}")
    Optional<Post> getByProfile(String profile);

}
