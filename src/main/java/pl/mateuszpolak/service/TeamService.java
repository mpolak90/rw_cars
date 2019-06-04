package pl.mateuszpolak.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mateuszpolak.model.Team;
import pl.mateuszpolak.repository.TeamRepository;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    public List<Team> findAllActive() {return teamRepository.findAllByActive(true);}

    public void save(Team team) {
        teamRepository.save(team);
    }

    public void delete(Team team) {
        teamRepository.delete(team);
    }

    public Team findOne(Long id) { return teamRepository.findOne(id); }
}
