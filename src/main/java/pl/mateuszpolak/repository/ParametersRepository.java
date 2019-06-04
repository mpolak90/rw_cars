package pl.mateuszpolak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mateuszpolak.model.Parameters;

public interface ParametersRepository extends JpaRepository<Parameters, Long> {
}
