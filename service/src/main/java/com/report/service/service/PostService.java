package com.report.service.service;

import com.report.service.documnent.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Post getByProfile(String profile);

    List<Post> getAllPosts();
    Post createPost(Post post);

    Post updatePost(Post post);
}
