package lv.javaguru.java2.businesslogic.users;

import lv.javaguru.java2.businesslogic.api.Error;
import lv.javaguru.java2.businesslogic.api.Response;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

import static lv.javaguru.java2.domain.UserBuilder.createUser;

public interface RegisterUserService {
    Response register(String login, String password);
}

@Component
class RegisterUserServiceImpl implements RegisterUserService {

    @Autowired private RegisterUserValidator validator;
    @Autowired private UserDAO dao;

    @Override
    @Transactional
    public Response register(String login, String password) {
        List<Error> validationErrors = validator.validate(login, password);
        if (!validationErrors.isEmpty()) {
            return Response.createFailResponse(validationErrors);
        }

        User user = createUser()
                .withLogin(login)
                .withPassword(password).build();

        dao.save(user);

        return Response.createSuccessResponse();
    }

}
