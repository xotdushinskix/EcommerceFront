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
		model.addAttribute("userInfo", userService.getUserById(id));
		return "userEditInfo";
	}



	@RequestMapping(value = "/info/edit/{id}", method = RequestMethod.POST)
	public String editUserInfo(@Valid @ModelAttribute User user, BindingResult result) throws SQLException {
		if (result.hasErrors()) {
			return "userEditInfo";
		}
		System.out.println();
		System.out.println(user.getLogin());
		System.out.println();
		userService.editUser(user);
		return "redirect:/info";
	}



	@RequestMapping(value = "/info/edit/password/{id}", method = RequestMethod.GET)
	public String editPassword(@ModelAttribute User user, Model model) throws SQLException {
		String login = super.getPrincipal();
		user = userService.getUserByLogin(login);
		model.addAttribute("userId", user);
		return "editUserPassword";
	}



	@RequestMapping(value = "/info/edit/password/{id}", method = RequestMethod.POST)
	public String postEditPassword(@ModelAttribute User user, @RequestParam("currentPassword") String password,
								   @RequestParam("newPSWRD") String newPassword, Model model,
								   @RequestParam("cnfrmPSWRD") String confirmPassword) throws SQLException {
		String login = super.getPrincipal();
		user = userService.getUserByLogin(login);
		if (password.equals(user.getPassword())) {
			if (newPassword.equals(confirmPassword)) {
				userService.editUser(user);
			} else {
				String nonEqualsPasswords = "Your confirm password is not equal new password";
				model.addAttribute("message2", nonEqualsPasswords);
				return "editUserPassword";
			}
		} else {
			String invalidCurrentPassword = "You have enter invalid current password";
			model.addAttribute("message1", invalidCurrentPassword);
			return "editUserPassword";
		}

		System.out.println(password);
		System.out.println(newPassword);
		System.out.println(confirmPassword);
		return "redirect:/info";
	}

}