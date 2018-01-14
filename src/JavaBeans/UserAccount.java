package JavaBeans;

import config.SecurityConfig;

import java.util.ArrayList;
import java.util.List;

public class UserAccount {
    private int userID;
    private String userName;
    private String userLogin;
    private String password;
    private String accessLevel;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserAccount() {
    }


    public UserAccount(String userLogin, String password, String accessLevel) {
        this.userLogin = userLogin;
        this.password = password;
        this.accessLevel = accessLevel;
    }

    public UserAccount(int userID, String userLogin, String password, String userName, String email ) {
        this.userID = userID;
        this.userLogin = userLogin;
        this.password = password;
        this.userName = userName;
        this.email = email;
        this.accessLevel = SecurityConfig.ROLE_CUSTOMER;

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }
}
