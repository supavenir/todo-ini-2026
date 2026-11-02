package sio.spring.todo.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import sio.spring.todo.entities.TodoList;
import sio.spring.todo.entities.User;

public interface TodoListRepository extends JpaRepository<TodoList, UUID> {
	
	public List<TodoList> findByOwner(User owner);
}
