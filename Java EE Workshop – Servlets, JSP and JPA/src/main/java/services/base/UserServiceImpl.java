package services.base;

import models.entity.User;
import models.service.UserServiceModel;
import org.modelmapper.ModelMapper;
import services.UserService;
import services.UserValidationService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final EntityManager entityManager;
    private final ModelMapper modelMapper;
    private final UserValidationService userValidationService;

    @Inject
    public UserServiceImpl(EntityManager entityManager, ModelMapper modelMapper, UserValidationService userValidationService){
        this.entityManager = entityManager;
        this.modelMapper = modelMapper;
        this.userValidationService = userValidationService;
    }

    public void register(String username, String email, String password, String confirmPassword) throws Exception {
        if(!userValidationService.isUserValid(username, email, password, confirmPassword)){
            throw new Exception("Cannot create user.");
        }
        this.entityManager.getTransaction().begin();
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        this.entityManager.persist(user);
        this.entityManager.getTransaction().commit();
    }

    public UserServiceModel login(String username, String password) {
        List<User> users = this.entityManager.createQuery("select u from User u where u.username = :username",User.class)
                .setParameter("username", username)
                .getResultList();
        if(users.isEmpty()){
            return null;
        }

        User user = users.get(0);

        if(!user.getPassword().equals(password)){
            return null;
        }

        return modelMapper.map(user, UserServiceModel.class);
    }
}
