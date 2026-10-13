package sio.spring.todo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import sio.spring.todo.dtos.TodoListCreateRequest;
import sio.spring.todo.entities.TodoList;
import sio.spring.todo.services.AppService;
import sio.spring.todo.services.TodoService;

@Controller
@RequestMapping("/todos")
public class TodoController {

	@Autowired
	private AppService appService;

	@Autowired
	private TodoService todoService;

	@GetMapping
	public ModelAndView index() {
		return new ModelAndView("/index", "currentUser", appService.getCurrentUser());
	}

	@GetMapping("/add")
	public ModelAndView addForm() {
		TodoList todoList = new TodoList("Ma liste");
		return new ModelAndView("/todos/todoForm", "todolist", todoList);
	}

	@PostMapping("/add")
	public RedirectView submitCreate(@ModelAttribute TodoListCreateRequest todoRequest) {
		todoService.createNewList(todoRequest);
		return new RedirectView("/todos");
	}
}
