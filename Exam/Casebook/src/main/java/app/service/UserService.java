package app.service;

import app.domain.models.service.UserServiceModel;

import java.util.List;

public interface UserService {

    void register(UserServiceModel user);

    UserServiceModel getByUsernameAndPassword(String username, String password);

    List<UserServiceModel> getAll();

    void addFriend(UserServiceModel user);

    UserServiceModel getById(String id);

    void remove(String id, String unFriendId);

}
