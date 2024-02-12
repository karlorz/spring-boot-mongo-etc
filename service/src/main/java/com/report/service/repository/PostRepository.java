package com.report.service.repository;

import com.report.service.documnent.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Aggregation;

import java.util.Optional;

public interface PostRepository extends MongoRepository<Post, String> {
    @Aggregation(pipeline = {
            "{$match: {profile: ?0}}",
            "{$sort: { _id: -1 }}",
            "{$limit: 1}"
    })
//    @Query("{profile : ?0}")
    Optional<Post> getByProfile(String profile);

}
