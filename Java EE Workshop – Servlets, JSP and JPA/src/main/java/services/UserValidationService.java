package services;

public interface UserValidationService {
    boolean isUserValid(String username, String email, String password, String confirmPassword);
}
