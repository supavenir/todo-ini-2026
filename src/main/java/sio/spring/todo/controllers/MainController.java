package sio.spring.todo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import sio.spring.todo.entities.User;
import sio.spring.todo.services.AppService;

@Controller
public class MainController {

	@Autowired
	private AppService appService;

	@ModelAttribute(name = "currentUser")
	private User getCurrentUser() {
		return appService.getCurrentUser();
	}

	@GetMapping
	public String index() {
		return "index";
	}
}
