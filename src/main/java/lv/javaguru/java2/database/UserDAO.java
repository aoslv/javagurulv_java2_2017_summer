package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.User;

import java.util.Optional;

public interface UserDAO {

    User save(User user);

    Optional<User> getById(Long id);

    Optional<User> getByLogin(String login);

}
