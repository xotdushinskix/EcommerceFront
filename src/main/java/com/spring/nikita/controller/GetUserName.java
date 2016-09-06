package com.spring.nikita.controller;

import com.spring.nikita.model.User;
import com.spring.nikita.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import java.sql.SQLException;

/**
 * Created by nikita on 05.09.16.
 */
@Controller
public class GetUserName {

    @Autowired
    private UserService userService;

    protected String getPrincipal() {
        String userLogin = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userLogin = ((UserDetails)principal).getUsername();
        } else {
            userLogin = principal.toString();
        }
        return userLogin;
    }



    protected String getUserName() throws SQLException {
        String userName = null;
        String login = getPrincipal();
        User helper = userService.getUserByLogin(login);
        userName = helper.getFirstName();
        return userName;
    }



    protected int getUserId() throws SQLException {
        int id = 0;
        String login = getPrincipal();
        User helper = userService.getUserByLogin(login);
        id = helper.getId();
        return id;
    }

}
