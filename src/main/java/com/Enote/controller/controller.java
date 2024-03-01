package com.Enote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Enote.entity.User;
import com.Enote.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class controller {
	@Autowired
	private UserService userService;

	@RequestMapping(value = { "", "/", "home", "index" })
	public String home() {
		return "index";
	}

	@RequestMapping("/registration")
	public String registration() {
		return "registration";
	}

	@PostMapping("/SeveUser")
	public String SeveUser(@ModelAttribute User user, HttpSession session) {
		boolean f= userService.existEmailChack(user.getEmail());
		if(f) {
			session.setAttribute("msg", "Email already exist");
		}else {
			User u = userService.saveUser(user);
			if (u != null) {
				session.setAttribute("msg", " Registration successfully compleated");
			} else {
				session.setAttribute("msg", "Registration not done successfully");
			}
		}
		return "redirect:/registration";
	}

	@RequestMapping("/signup")
	public String login() {
		return "login";
	}

}
