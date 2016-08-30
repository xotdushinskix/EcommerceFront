package com.websystique.nikita.main;

import com.websystique.nikita.configuration.HelloWorldConfiguration;
import com.websystique.nikita.configuration.HibernateConfiguration;
import com.websystique.nikita.model.User;
import com.websystique.nikita.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.SQLException;

/**
 * Created by nikita on 30.08.16.
 */

public class Main {

    public static void main (String[] args) throws SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfiguration.class);

        UserService userService = context.getBean(UserService.class);
        User user = new User();
        user.setFirstName("Ssdd");
        user.setLastName("Awwd");
        user.setLogin("ddssa");
        user.setPassword("ppoo");
        user.setPosition("Active");
        userService.addUser(user);
    }

}
