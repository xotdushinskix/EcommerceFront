package com.spring.nikita.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @NotEmpty(message = "Please, enter your first name")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "Please, enter your last name")
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty(message = "Please, enter your login")
    @Column(name = "login", unique = true)
    private String login;

    @NotEmpty(message = "Please, enter your password")
    @Column(name = "password")
    private String password;

    @Column(name = "position")
    private String position = UserPosition.ACTIVE.getPosition();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = {@JoinColumn(name = "user_id")},
        inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles = new HashSet<Role>();

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<OrderLines> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLines> orderLines) {
        this.orderLines = orderLines;
    }
}
