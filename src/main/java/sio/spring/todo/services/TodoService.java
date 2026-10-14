package sio.spring.todo.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sio.spring.todo.dtos.TodoListCreateRequest;
import sio.spring.todo.entities.TodoItem;
import sio.spring.todo.entities.TodoList;
import sio.spring.todo.entities.User;
import sio.spring.todo.repositories.TodoItemRepository;
import sio.spring.todo.repositories.TodoListRepository;

@Service
public class TodoService {

	@Autowired
	private AppService appService;

	@Autowired
	private TodoListRepository todoListRepository;

	@Autowired
	private TodoItemRepository todoItemRepository;

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
	
	public List<TodoList> findByOwner(User owner){
		return todoListRepository.findByOwner(owner);
	}

	public Optional<TodoList> getById(UUID id) {
		return todoListRepository.findById(id);
	}

	public void toggleItem(UUID itemId) {
		Optional<TodoItem> opt = todoItemRepository.findById(itemId);
		if (opt.isPresent()) {
			TodoItem item = opt.get();
			item.setChecked(!item.isChecked());
			todoItemRepository.save(item);
		}
	}
}
