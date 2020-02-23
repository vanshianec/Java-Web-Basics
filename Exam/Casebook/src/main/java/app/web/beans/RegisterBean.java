package app.web.beans;

import app.domain.entities.Gender;
import app.domain.models.binding.UserRegisterBindingModel;
import app.domain.models.service.UserServiceModel;
import app.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class RegisterBean extends BaseBean {

    private UserRegisterBindingModel model;
    private UserService userService;
    private ModelMapper modelMapper;

    public RegisterBean() {
    }

    @Inject
    public RegisterBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        this.model = new UserRegisterBindingModel();
    }

    public void register() {
        if (!this.model.getPassword().equals(this.model.getConfirmPassword())) {
            this.redirect("/register");
        }
        this.model.setPassword(DigestUtils.sha256Hex(this.model.getPassword()));
        Gender gender = null;
        try {
            gender = Gender.valueOf(this.model.getGender().toUpperCase());
        } catch (Exception e) {
            e.printStackTrace();
            this.redirect("/register");
        }
        this.model.setGender(gender.toString().toUpperCase());
        this.userService.register(this.modelMapper.map(this.model, UserServiceModel.class));
        this.redirect("/login");
    }

    public UserRegisterBindingModel getModel() {
        return model;
    }

    public void setModel(UserRegisterBindingModel model) {
        this.model = model;
    }
}
