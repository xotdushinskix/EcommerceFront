package com.spring.nikita.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.NoHandlerFoundException;


/**
 * Created by nikita on 20.09.16.
 */
@ControllerAdvice
@Controller
public class Handle404Controller {


    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle(Exception ex) {
        return "redirect:/404";
    }

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String NotFoundPage() {
        return "error404";
    }



}
