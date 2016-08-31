package com.spring.nikita.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.nikita.model.User;
import com.spring.nikita.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;

@Controller
public class StartController {

	@Autowired
	UserService userService;

	
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		model.addAttribute("greeting", "Hi, Welcome to mysite");
		return "welcome";
	}



	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(ModelMap model) throws SQLException {
		model.addAttribute("user", getUserName());
		return "admin";
	}



	@RequestMapping(value = "/admin/add", method = RequestMethod.GET)
	public String addUser(@ModelAttribute User user, ModelMap model) {
		return "addUser";
	}



	@RequestMapping(value = "/admin/add", method = RequestMethod.POST)
	public String addUserFinal(@ModelAttribute User user) throws SQLException {
		userService.addUser(user);
		return "redirect:/admin";
	}



	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String dbaPage(ModelMap model) throws SQLException {
		model.addAttribute("user", getUserName());
		return "user";
	}



	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) throws SQLException {
		model.addAttribute("user", getUserName());
		return "accessDenied";
	}



	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}



	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logOut(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return "redirect:/login";
	}



	private String getPrincipal() {
		String userLogin = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			userLogin = ((UserDetails)principal).getUsername();
		} else {
			userLogin = principal.toString();
		}
		return userLogin;
	}



	private String getUserName() throws SQLException {
		String userName = null;
		String login = getPrincipal();
		User helper = userService.getUserByLogin(login);
		userName = helper.getFirstName();
		return userName;
	}


}