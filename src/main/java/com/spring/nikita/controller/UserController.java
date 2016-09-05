package com.spring.nikita.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.spring.nikita.model.User;
import com.spring.nikita.service.RoleService;
import com.spring.nikita.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
public class UserController extends GetUserName{

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homePage() {
		return "welcome";
	}



	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(ModelMap model) throws SQLException {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			model.addAttribute("userName", super.getUserName());
		}
		model.addAttribute("userName", getUserName());
		return "admin";
	}



	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String addUser(@ModelAttribute User user) {
		return "addUser";
	}



	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String addUserFinal(@ModelAttribute User user, @RequestParam("password")String password1,
							   @RequestParam("password")String password2) throws SQLException {
		if (password1.equals(password2)) {
			user.getRoles().add(roleService.getRoleByName(2));
			userService.addUser(user);
		} else {
			System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
		}
		return "redirect:/";
	}



	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String dbaPage(ModelMap model) throws SQLException {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			model.addAttribute("userName", super.getUserName());
		}
		return "user";
	}



	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) throws SQLException {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			model.addAttribute("userName", super.getUserName());
		}
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
		return "redirect:/";
	}



	@RequestMapping(value = "/info/{login}", method = RequestMethod.GET)
	public String myInfo(@ModelAttribute User user, @PathVariable("login") String login, Model model) throws SQLException {
		model.addAttribute("userLog", userService.getUserByLogin(login));
		return "info";
	}


	@RequestMapping(value = "/info/{login}", method = RequestMethod.POST)
	public String myInfoEdit(@Valid @ModelAttribute User user, BindingResult result) throws SQLException {
		if (result.hasErrors()) {
			return "info";
		} else {
			userService.editUser(user);
			return "redirect:/";
		}
	}



	@RequestMapping(value = "/my_page", method = RequestMethod.GET)
	public String privatePage(@ModelAttribute User user, Model model) throws SQLException {
//		login = super.getPrincipal();
//		model.addAttribute("user", userService.getUserByLogin(login));

		model.addAttribute("firstName", user.getFirstName());
		model.addAttribute("lastName", user.getLastName());
		model.addAttribute("login", user.getLogin());
		model.addAttribute("password", user.getPassword());
		return "myPage";
	}



	@RequestMapping(value = "/my_page", method = RequestMethod.POST)
	public String editPrivatePage(@ModelAttribute User user, BindingResult result) throws SQLException {
		if (result.hasErrors()) {
			return "myPage";
		} else {
			String login = super.getPrincipal();
			user = userService.getUserByLogin(login);
			userService.editUser(user);
			return "redirect:/my_page";
		}
	}




}