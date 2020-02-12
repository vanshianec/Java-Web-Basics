package services.base;

import models.entity.User;
import services.UserValidationService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidationServiceImpl implements UserValidationService {

    static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private final EntityManager entityManager;

    @Inject
    public UserValidationServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public boolean isUserValid(String username, String email, String password, String confirmPassword) {
        return isEmailValid(email) && arePasswordsMatching(password, confirmPassword) && !isUsernameTaken(username);
    }

    private boolean isEmailValid(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return matcher.find();
    }

    private boolean arePasswordsMatching(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    private boolean isUsernameTaken(String username) {
        List<User> users = this.entityManager.createQuery("select u from User u where u.username = :username",User.class)
                .setParameter("username", username)
                .getResultList();
        return !users.isEmpty();
    }
}
