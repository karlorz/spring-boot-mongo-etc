package com.report.service.controller;

import com.report.service.documnent.Post;
import com.report.service.service.PostService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{profile}")
    public Post getPostByProfile(@PathVariable String profile) {
        log.info("Getting post by profile #####");
        return postService.getByProfile(profile);
    }

    @GetMapping
    public List<Post> getAllPosts() {
        log.info("Getting all the posts #####");
        return postService.getAllPosts();
    }

    @PostMapping
    public Post createPost(@Valid @RequestBody Post post) {
        log.info("Saving post #####");
        return postService.createPost(post);
    }
    @PutMapping("/{profile}")
    public Post updatePost(@PathVariable String profile,@Valid @RequestBody Post post) {
        log.info("Updating post #####");
        post.setProfile(profile);
        return postService.updatePost(post);
    }

}
