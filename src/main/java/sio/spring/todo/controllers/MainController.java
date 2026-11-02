package sio.spring.todo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.servlet.http.HttpSession;
import sio.spring.todo.dtos.LoginRequest;
import sio.spring.todo.entities.User;
import sio.spring.todo.services.AppService;
import sio.spring.todo.services.TodoService;
import sio.spring.todo.services.UserService;

@Controller
public class MainController {

	private final AppService appService;

	private final UserService userService;

	private final TodoService todoService;

	private final PasswordEncoder pEncoder;

	MainController(AppService appService, UserService userService, TodoService todoService, PasswordEncoder pEncoder) {
		this.appService = appService;
		this.userService = userService;
		this.todoService = todoService;
		this.pEncoder = pEncoder;
	}

	@ModelAttribute(name = "currentUser")
	private User getCurrentUser(Authentication auth) {
		if (auth != null) {
			return (User) auth.getPrincipal();
		}
		return null;
	}

	@GetMapping
	public String index(Model model, Authentication auth) {
		// Recharger les TodoLists de currentUser
		User u = getCurrentUser(auth);
		if (u != null) {
			model.addAttribute("lists", todoService.findByOwner(u));
		}
		return "index";
	}

	@GetMapping("/signup")
	public ModelAndView signup() {
		return new ModelAndView("/users/form", "user", new User());
	}

	@GetMapping("/signin")
	public String signin() {
		return "/users/login";
	}

	@PostMapping("/signin")
	public RedirectView submitLogin(@ModelAttribute LoginRequest request, HttpSession session) {
		UserDetails userD = userService.loadUserByUsername(request.getLogin());
		if (userD != null) {
			User user = (User) userD;
			if (pEncoder.matches(request.getPassword(), user.getPassword())) {
				return new RedirectView("/");
			}
		}
		return new RedirectView("/signin");
	}

	@GetMapping("/logout")
	public RedirectView logout() {
		appService.logout();
		return new RedirectView("/");
	}

}
