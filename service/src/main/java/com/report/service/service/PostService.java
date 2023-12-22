package com.report.service.service;

import com.report.service.dto.PostDTO;
import com.report.service.model.PostModal;

import java.util.List;

public interface PostService {

    public List<PostModal> getAllPosts();

    public PostModal savePost(PostDTO post);
}
