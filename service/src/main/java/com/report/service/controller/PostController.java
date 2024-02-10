package com.report.service.controller;

import com.report.service.documnent.Post;
import com.report.service.repository.PostRepository;
import com.report.service.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Slf4j
//@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts/{profile}")
    public Post getPostByProfile(@PathVariable String profile) {
        log.info("Getting post by profile #####");
        return postService.getByProfile(profile);
    }

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        log.info("Getting all the posts #####");
        return postService.getAllPosts();
    }

    @PostMapping("/posts")
    public Post createPost(@Valid @RequestBody Post post) {
        log.info("Saving post #####");
        return this.postService.createPost(post);
    }
}
