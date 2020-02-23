package app.web.beans;

import app.domain.models.binding.UserLoginBindingModel;
import app.domain.models.service.UserServiceModel;
import app.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

@Named
@RequestScoped
public class LoginBean extends BaseBean {

    private UserLoginBindingModel model;
    private UserService userService;
    private ModelMapper modelMapper;

    public LoginBean(){}

    @Inject
    public LoginBean(UserLoginBindingModel model, UserService userService, ModelMapper modelMapper){
        this.model = model;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init(){
        this.model = new UserLoginBindingModel();
    }

    public void login(){
        UserServiceModel user = this.userService.getByUsernameAndPassword(
                this.model.getUsername(), DigestUtils.sha256Hex(this.model.getPassword()));

        if (user == null){
            this.redirect("/login");
        }

        Map<String, Object> sessionMap = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap();
        sessionMap.put("username", user.getUsername());
        sessionMap.put("userId", user.getId());

        this.redirect("/home");
    }

    public UserLoginBindingModel getModel() {
        return model;
    }

    public void setModel(UserLoginBindingModel model) {
        this.model = model;
    }
}
