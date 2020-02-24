package app.domain.models.view;

import app.domain.entities.Gender;

public class UserHomeViewModel {

    private String id;
    private String username;
    private Gender gender;

    public UserHomeViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender.toString().toLowerCase();
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
