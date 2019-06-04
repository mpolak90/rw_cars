package pl.mateuszpolak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mateuszpolak.model.Team;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findAllByActive(boolean active);
}