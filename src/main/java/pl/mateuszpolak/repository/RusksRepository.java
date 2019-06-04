package pl.mateuszpolak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mateuszpolak.model.Rusk;

public interface RusksRepository extends JpaRepository<Rusk, Long> {
}
