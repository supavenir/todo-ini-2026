package sio.spring.todo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import sio.spring.todo.repositories.UserRepository;
import sio.spring.todo.services.AppService;
import sio.spring.todo.services.TodoService;
import sio.spring.todo.services.UserService;

@Controller
public class MainController {

	@Autowired
	private AppService appService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TodoService todoService;

	@ModelAttribute(name = "currentUser")
	private User getCurrentUser() {
		return appService.getCurrentUser();
	}

	@GetMapping
	public String index(Model model) {
		//Recharger les TodoLists de currentUser
		User u=getCurrentUser();
		if(u!=null) {
			model.addAttribute("lists", todoService.findByOwner(u));
		}
		return "index";
	}
	
	@GetMapping("/signup")
	public ModelAndView signup() {
		return new ModelAndView("/users/form","user", new User());
	}
	
	@GetMapping("/signin")
	public String signin() {
		return "/users/login";
	}
	
	@PostMapping("/signin")
	public RedirectView submitLogin(@ModelAttribute LoginRequest request, HttpSession session) {
		Optional<User> opt=userService.findUserByLogin(request.getLogin());
		if(opt.isPresent()) {
			User user=opt.get();
			if(request.getPassword().equals(user.getPassword())) {
				session.setAttribute("currentUser", user);
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
