package db.entity;

import controller.command.Command;
import db.enums.UserRole;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class User {

    private int id;
    private String login;
    private String password;
    private UserRole role;

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getLogin() {return login;}

    public void setLogin(String login) {this.login = login;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public UserRole getRole() {return role;}

    public void setRole(UserRole role) {this.role = role;}
}
