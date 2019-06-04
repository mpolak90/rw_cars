package pl.mateuszpolak.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mateuszpolak.model.User;
import pl.mateuszpolak.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAllActive() {
        return userRepository.findAllByIdNotAndActive(1L, true);
    }

    public List<User> findAllBlock() {
        return userRepository.findAllByIdNotAndActive(1L, false);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    public boolean checkAvailable(String login) {
        return userRepository.findByLogin(login).size() == 0;
    }

    public boolean checkLogin(String login) {
        return userRepository.findByLogin(login).size() == 1;
    }

    public boolean checkPass(String login, String password) {
        User user = userRepository.findUserByLoginAndActive(login, true).get(0);
        return BCrypt.checkpw(password, user.getPassword());
    }

    public void hashPass(User user, String password) {
        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
    }
}
