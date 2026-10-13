package sio.spring.todo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sio.spring.todo.dtos.TodoListCreateRequest;
import sio.spring.todo.entities.TodoItem;
import sio.spring.todo.entities.TodoList;
import sio.spring.todo.repositories.TodoListRepository;

@Service
public class TodoService {

	@Autowired
	private AppService appService;

	@Autowired
	private TodoListRepository todoListRepository;

	private void elementsToList(String elements, TodoList list) {
		for (String elm : elements.split("\n")) {
			list.addItem(new TodoItem(elm));
		}
	}

	public TodoList createNewList(TodoListCreateRequest request) {
		TodoList todoList = new TodoList(request.getName());
		todoList.setOwner(appService.getCurrentUser());
		elementsToList(request.getElements(), todoList);
		return todoListRepository.save(todoList);
	}

	public List<TodoList> getAllLists() {
		return todoListRepository.findAll();
	}
}
