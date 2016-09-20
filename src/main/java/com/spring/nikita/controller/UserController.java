package com.spring.nikita.controller;


import com.spring.nikita.model.Role;
import com.spring.nikita.model.User;
import com.spring.nikita.model.UserRoles;
import com.spring.nikita.service.RoleService;
import com.spring.nikita.service.UserRolesService;
import com.spring.nikita.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;


@Controller
public class UserController extends GetUserName {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserRolesService userRolesService;


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
	public String addUserFinal(@Validated @ModelAttribute User user, BindingResult result, @ModelAttribute Role role,
							   @RequestParam("password") String password1,
							   @RequestParam("passwordC") String passwordC, Model model) throws SQLException {

		String returnString = null;

		if (result.hasErrors() & passwordC.isEmpty()) {
			model.addAttribute("password2Error", "Confirm password must be field");
			return "addUser";
		}

		if (password1.equals(passwordC)) {
			userService.addUser(user);
			UserRoles userRoles = new UserRoles();
			userRoles.setUser(user);
			userRoles.setRole(roleService.getRoleByName(1));
			userRolesService.addUserRole(userRoles);
			returnString = "redirect:/main";

		} else {

			model.addAttribute("passwordsDontEquals", "Passwords don't equals");
			returnString = "addUser";
		}

		return returnString;
	}



	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String dbaPage(ModelMap model) throws SQLException {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			model.addAttribute("userName", super.getUserName());
		}
		return "user";
	}



	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String userInfo(Model model, @ModelAttribute User user) throws SQLException {
		String login = super.getPrincipal();
		user = userService.getUserByLogin(login);
		model.addAttribute("userId", user);
		model.addAttribute("fName", user.getFirstName());
		model.addAttribute("lName", user.getLastName());
		model.addAttribute("login", user.getLogin());
		return "infoPage";
	}



	@RequestMapping(value = "/info/edit/{id}", method = RequestMethod.GET)
	public String showEditUserInfo(Model model, @PathVariable("id") int id, @ModelAttribute User user) throws SQLException {
		String login = super.getPrincipal();
		user = userService.getUserByLogin(login);
		model.addAttribute("userId", user);
		model.addAttribute("user", userService.getUserById(id));
		return "userEditInfo";
	}



	@RequestMapping(value = "/info/edit/{id}", method = RequestMethod.POST)
	public String editUserInfo(@Validated @ModelAttribute User user, BindingResult result) throws SQLException {
//		userValidator.validate(user, result);
		if (result.hasErrors()) {
			return "userEditInfo";
		}
		userService.editUser(user);
		return "redirect:/info";
	}



	@RequestMapping(value = "/info/edit/password", method = RequestMethod.GET)
	public String editPassword(@ModelAttribute User user, Model model) throws SQLException {
		return "editUserPassword";
	}



	@RequestMapping(value = "/info/edit/password", method = RequestMethod.POST)
	public String postEditPass(@Validated @ModelAttribute User user, BindingResult result,
							   @RequestParam("password") String password, @RequestParam("newPassword") String newPassword,
							   @RequestParam("confirmNewPassword") String confirmNewPassword, Model model) throws SQLException {
		String login = super.getPrincipal();
		user = userService.getUserByLogin(login);
		String userPassword = user.getPassword();

		String returnString = null;

		if (!userPassword.equals(password)) {
			String passwordMessage = "You have entered invalid password";
			model.addAttribute("passwordMessage", passwordMessage);
			returnString = "editUserPassword";

		} else if (newPassword.isEmpty()) {
			String newPassMessage = "New password field can not be empty";
			model.addAttribute("newPassMessage", newPassMessage);
			returnString = "editUserPassword";

		} else if (confirmNewPassword.isEmpty()) {
			String confirmPassMessage = "Confirm password field can not be empty";
			model.addAttribute("confirmPassMessage", confirmPassMessage);
			returnString = "editUserPassword";

		} else if (!newPassword.equals(confirmNewPassword)) {
			String notEqualsPasswords = "Passwords are not equals";
			model.addAttribute("notEqualsPasswords", notEqualsPasswords);
			returnString = "editUserPassword";

		} else {
			user.setPassword(newPassword);
			userService.editUser(user);
			returnString = "redirect:/info";
		}

		return returnString;
	}


}




