package com.websystique.nikita.main;

import com.websystique.nikita.configuration.HibernateConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by nikita on 30.08.16.
 */
public class Main {

    public static void main (String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfiguration.class);
    }

}
