package com.report.service.controller;

import com.report.service.constants.APIConstants;
import com.report.service.dto.PostDTO;
import com.report.service.model.PostModal;
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
@RequiredArgsConstructor
public class PostController {


    final PostService postService;

    @GetMapping(APIConstants.GET_ALL_POSTS)
    public List<PostModal> getAllPosts() {
        log.info("Getting all the posts #####");
        return this.postService.getAllPosts();
    }

    @PostMapping(APIConstants.SAVE_POST)
    public PostModal savePost(@Valid @RequestBody PostDTO postDTORequest) {
        log.info("Saving post #####");
        return this.postService.savePost(postDTORequest);
    }
}
