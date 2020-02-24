package app.web.beans;

import app.domain.models.service.UserServiceModel;
import app.domain.models.view.UserHomeViewModel;
import app.service.UserService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HomeBean extends BaseBean{

    private List<UserHomeViewModel> models;
    private UserService userService;
    private ModelMapper modelMapper;

    public HomeBean(){}

    @Inject
    public HomeBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init(){
        String username = (String) ((HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false)).getAttribute("username");

        this.setModels(this.userService.getAll()
                .stream()
                .filter(u -> !u.getUsername().equals(username)
                        && !u.getFriends().stream()
                        .map(UserServiceModel::getUsername)
                        .collect(Collectors.toList())
                        .contains(username))
                .map(u -> this.modelMapper.map(u, UserHomeViewModel.class))
                .collect(Collectors.toList()));
    }

    public void addFriend(String friendId){
        String userId = (String) ((HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false)).getAttribute("userId");

        UserServiceModel loggedInUser = this.userService.getById(userId);
        UserServiceModel friend = this.userService.getById(friendId);

        loggedInUser.getFriends().add(friend);
        friend.getFriends().add(loggedInUser);

        this.userService.addFriend(loggedInUser);
        this.userService.addFriend(friend);

        this.redirect("/home");
    }

    public List<UserHomeViewModel> getModels() {
        return models;
    }

    public void setModels(List<UserHomeViewModel> models) {
        this.models = models;
    }
}
