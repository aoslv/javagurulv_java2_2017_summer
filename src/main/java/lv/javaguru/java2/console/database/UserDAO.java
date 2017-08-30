package lv.javaguru.java2.console.database;

import lv.javaguru.java2.console.domain.User;

import java.util.Optional;

public interface UserDAO {

    User save(User user);

    Optional<User> getById(Long id);

    Optional<User> getByLogin(String login);

}
