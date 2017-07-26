package lv.javaguru.java2.businesslogic;

import com.google.common.collect.Lists;
import lv.javaguru.java2.database.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AddProductValidator {

     private Database database;

    @Autowired
    public AddProductValidator(Database database) {
        this.database = database;
    }

    public List<Error> validate(String title, String description) {
        List<Error> errors = Lists.newArrayList();
        validateTitle(title).ifPresent(e -> errors.add(e));
        validateDescription(description).ifPresent(e -> errors.add(e));
        return errors;
    }

    private Optional<Error> validateTitle(String title) {
        if (title == null || "".equals(title)) {
            return Optional.of(new Error("title", "Must be not empty"));
        } else if (alreadyExist(title)) {
            return Optional.of(new Error("title", "Already exist"));
        } else {
            return Optional.empty();
        }
    }

    private boolean alreadyExist(String title) {
        return database.getProductByTitle(title).isPresent();
    }

    private Optional<Error> validateDescription(String description) {
        if (description == null || "".equals(description)) {
            return Optional.of(new Error("description", "Must be not empty"));
        } else {
            return Optional.empty();
        }
    }
}
