package sio.spring.todo.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class TodoList extends BaseObject {

	@Column(length = 150, nullable = false)
	private String name;

	private LocalDateTime createdAt;

	@ManyToOne
	private User owner;

	@OneToMany(mappedBy = "list", cascade = CascadeType.ALL)
	private List<TodoItem> items;

	public TodoList() {
		this("nouvelle liste");
	}

	public TodoList(String name) {
		super();
		this.name = name;
		this.createdAt = LocalDateTime.now();
		this.items = new ArrayList<TodoItem>();
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public User getOwner() {
		return owner;
	}

	public List<TodoItem> getItems() {
		return items;
	}

	public void addItem(TodoItem item) {
		if (items.add(item)) {
			item.setList(this);
		}
	}

	public void addItems(List<TodoItem> items) {
		for (TodoItem item : items) {
			addItem(item);
		}
	}
}
