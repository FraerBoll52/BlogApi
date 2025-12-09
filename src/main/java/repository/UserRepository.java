package repository;

import Entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
// Наследуемся от JpaRepository<ТипСущности, ТипID>
public interface UserRepository extends JpaRepository<User, Long> {
    // Spring Data JPA автоматически создаст запрос:
    // SELECT * FROM users WHERE username = ?
    Optional<User> findByUsername(String username);
    // Автоматический запрос: SELECT * FROM users WHERE email = ?
    Optional<User> findByEmail(String email);

}
