package repository;

import Entitys.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    // Найти все посты пользователя по его ID
    List<Post> findByUserId(Long userId);

    // Найти посты, содержащие ключевое слово в заголовке
    List<Post> findByTitleContainingIgnoreCase(String keyword);
}
