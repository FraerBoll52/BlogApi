package com.fraerboll.blog_api.mapper;

import com.fraerboll.blog_api.DTO.PostDTO;
import com.fraerboll.blog_api.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    public PostDTO toDTO(Post post){
        PostDTO dto = new PostDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());

        if(post.getUser() != null){
            dto.setUserId(post.getUser().getId());
            dto.setUsername(post.getUser().getUsername());
        }
        return dto;
    }

    public Post toEntity(PostDTO dto){
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        return post;

    }
}
