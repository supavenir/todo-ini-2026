package sio.spring.todo.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class User extends BaseObject {

	@Column(length = 120, nullable = false)
	private String login;

	private String password;

	@OneToMany(mappedBy = "owner")
	private List<TodoList> todoLists;

	public User() {
		super();
		this.todoLists = new ArrayList<TodoList>();
	}

	public void addTodoList(TodoList list) {
		todoLists.add(list);
		list.setOwner(this);
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<TodoList> getTodoLists() {
		return todoLists;
	}

}
