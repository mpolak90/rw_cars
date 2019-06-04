package pl.mateuszpolak.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mateuszpolak.model.Parameters;
import pl.mateuszpolak.repository.ParametersRepository;

@Service
public class ParametersService {

    @Autowired
    ParametersRepository parametersRepository;

    public void save(Parameters parameters) {
        parametersRepository.save(parameters);
    }
}