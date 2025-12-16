package com.fraerboll.blog_api.controller;

import com.fraerboll.blog_api.DTO.CreatePostRequest;
import com.fraerboll.blog_api.DTO.PostDTO;
import com.fraerboll.blog_api.entity.Post;
import com.fraerboll.blog_api.entity.User;
import com.fraerboll.blog_api.mapper.PostMapper;
import org.springframework.web.bind.annotation.*;
import com.fraerboll.blog_api.repository.PostRepository;
import com.fraerboll.blog_api.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    private final PostMapper postMapper;

    public PostController(PostRepository postRepository, UserRepository userRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postMapper = postMapper;
    }

    // GET /api/posts - все посты
    @GetMapping
    public List<PostDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();

        // Конвертируем каждый Post в PostDTO
        return posts.stream()
                .map(postMapper::toDTO)  // метод reference: post -> postMapper.toDTO(post)
                .collect(Collectors.toList());
    }

    // GET /api/posts/1 - пост по ID
    @GetMapping("/{id}")
    public PostDTO getPostById(@PathVariable Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Пост не найден"));
        return postMapper.toDTO(post);
    }

    // POST /api/posts - создать пост


    @PostMapping
    public PostDTO createPost(@RequestBody CreatePostRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(()->new RuntimeException("Пользователь не найден"));
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setUser(user);

        Post savedPost = postRepository.save(post);

        return postMapper.toDTO(savedPost);
    }
}
