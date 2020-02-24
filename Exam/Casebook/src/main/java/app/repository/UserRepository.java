package app.repository;

import app.domain.entities.User;

import java.util.List;

public interface UserRepository {

    void save(User user);

    User findByUsernameAndPassword(String username, String password);

    List<User> findAll();

    void update(User user);

    User findById(String id);

}
