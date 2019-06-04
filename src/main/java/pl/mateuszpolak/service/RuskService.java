package pl.mateuszpolak.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mateuszpolak.model.Rusk;
import pl.mateuszpolak.repository.RusksRepository;

import java.util.List;

@Service
public class RuskService {

    @Autowired
    RusksRepository rusksRepository;

    public List<Rusk> findAll() {
        return rusksRepository.findAll();
    }

    public void save(Rusk rusk) {
        rusksRepository.save(rusk);
    }

    public void delete(Rusk rusk) {
        rusksRepository.delete(rusk);
    }

    public Rusk findOne(Long id) {
        return rusksRepository.findOne(id);
    }
}