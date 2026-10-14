package sio.spring.todo.controllers;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("/{id}")
	public ModelAndView getDetails(@PathVariable UUID id) {
		Optional<TodoList> opt = todoService.getById(id);
		if (opt.isPresent()) {
			return new ModelAndView("/todos/listDetails", "todolist", opt.get());
		}
		return new ModelAndView("/notFound");
	}

	@PostMapping("/{id}/{itemId}/toggle")
	public RedirectView toggleItem(@PathVariable UUID id, @PathVariable UUID itemId) {
		todoService.toggleItem(itemId);
		return new RedirectView("/todos/" + id);
	}

	@PostMapping("/add")
	public RedirectView submitCreate(@ModelAttribute TodoListCreateRequest todoRequest) {
		todoService.createNewList(todoRequest);
		return new RedirectView("/");
	}
}
