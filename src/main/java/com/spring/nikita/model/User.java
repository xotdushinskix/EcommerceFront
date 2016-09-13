package com.spring.nikita.model;

import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by nikita on 30.08.16.
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    //@NotBlank(message = "Please, enter your first name")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Please, enter your last name")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Please, enter your login")
    @Column(name = "login", unique = true)
    private String login;

    @NotBlank(message = "Please, enter your password")
    @Column(name = "password")
    private String password;

    @Column(name = "position")
    private String position = UserPosition.ACTIVE.getPosition();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserRoles> userRoles = new ArrayList<UserRoles>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<OrderLines> orderLines = new ArrayList<OrderLines>();

    public User() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


    public List<UserRoles> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRoles> userRoles) {
        this.userRoles = userRoles;
    }

    public List<OrderLines> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLines> orderLines) {
        this.orderLines = orderLines;
    }
}
