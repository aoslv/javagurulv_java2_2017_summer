package lv.javaguru.java2.businesslogic.users;

import com.google.common.collect.Lists;
import lv.javaguru.java2.businesslogic.api.Error;
import lv.javaguru.java2.database.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

public interface RegisterUserValidator {
    List<Error> validate(String login, String password);
}

@Component
class RegisterUserValidatorImpl implements RegisterUserValidator {

    @Autowired private UserDAO userDAO;

    @Override
    public List<Error> validate(String login, String password) {
        List<Error> errors = Lists.newArrayList();
        validateLogin(login).ifPresent(e -> errors.add(e));
        validatePassword(password).ifPresent(e -> errors.add(e));
        return errors;
    }

    private Optional<Error> validateLogin(String login) {
        if (login == null || "".equals(login)) {
            return Optional.of(new Error("login", "Must be not empty"));
        } else if (alreadyExist(login)) {
            return Optional.of(new Error("login", "Already exist"));
        } else {
            return Optional.empty();
        }
    }

    private boolean alreadyExist(String login) {
        return userDAO.getByLogin(login).isPresent();
    }

    private Optional<Error> validatePassword(String password) {
        if (password == null || "".equals(password)) {
            return Optional.of(new Error("password", "Must be not empty"));
        } else {
            return Optional.empty();
        }
    }

}
