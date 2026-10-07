package sio.spring.todo.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import sio.spring.todo.entities.User;
import sio.spring.todo.repositories.UserMemoryRepository;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserMemoryRepository userRepository;

	@GetMapping
	public String index(Model model) {
		List<User> users = userRepository.findAll();
		model.addAttribute("users", users);
		return "/users/index";
	}

	@GetMapping("/add")
	public ModelAndView addForm() {
		return new ModelAndView("/users/form", "user", new User());
	}

	@PostMapping("/add")
	public RedirectView add(@ModelAttribute User user, RedirectAttributes attrs) {
		attrs.addFlashAttribute("user", user);
		attrs.addFlashAttribute("message", "L'utilisateur " + user.getLogin() + " a été ajouté");
		userRepository.save(user);
		return new RedirectView("/users");
	}

	@GetMapping("/update/{id}")
	public ModelAndView updateForm(@PathVariable UUID id) {
		Optional<User> opt = userRepository.findById(id);
		if (opt.isPresent()) {
			return new ModelAndView("/users/form", "user", opt.get());
		}
		return new ModelAndView("/users/form", "user", null);
	}

	@GetMapping("/delete/{id}")
	public RedirectView add(@PathVariable UUID id, RedirectAttributes attrs) {
		Optional<User> opt = userRepository.findById(id);
		if (opt.isPresent()) {
			userRepository.deleteById(id);
			attrs.addFlashAttribute("message", "L'utilisateur " + opt.get().getLogin() + " a été supprimé");
		} else {
			attrs.addFlashAttribute("message", "Aucun utilisateur supprimé");
		}
		return new RedirectView("/users");

	}
}
