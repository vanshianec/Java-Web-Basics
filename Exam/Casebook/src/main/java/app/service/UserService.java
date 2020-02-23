package app.service;

import app.domain.models.service.UserServiceModel;

public interface UserService {

    void register(UserServiceModel user);

    UserServiceModel getByUsernameAndPassword(String username, String password);

}
