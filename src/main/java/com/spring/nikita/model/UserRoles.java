package com.spring.nikita.model;

import javax.persistence.*;

/**
 * Created by nikita on 12.09.16.
 */
@Entity
@Table(name = "user_roles")
public class UserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id")
    private int userRoles_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public UserRoles() {

    }

    public int getUserRoles_id() {
        return userRoles_id;
    }

    public void setUserRoles_id(int userRoles_id) {
        this.userRoles_id = userRoles_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
