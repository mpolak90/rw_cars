package pl.mateuszpolak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mateuszpolak.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByIdNotAndActive(Long id, boolean active);

    List<User> findUserByLoginAndActive(String login, boolean active);

    List<User> findByLogin(String login);
}