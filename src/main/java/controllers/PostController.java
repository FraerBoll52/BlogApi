package controllers;

import Entitys.Post;
import org.springframework.web.bind.annotation.*;
import repository.PostRepository;
import repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostController(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    // GET /api/posts - все посты
    @GetMapping
    public List<Post> getAllPosts() {
        return postRepository.findAll();

    }

    // GET /api/posts/1 - пост по ID
    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id){
        return postRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Пост не найден"));
    }

    // POST /api/posts - создать пост
    @PostMapping
    public Post createPost(@RequestBody Post post){
        // Предполагаем, что User уже существует
        return postRepository.save(post);
    }
}
