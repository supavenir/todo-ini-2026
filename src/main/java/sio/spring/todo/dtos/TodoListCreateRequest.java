package sio.spring.todo.dtos;

import java.util.UUID;

public class TodoListCreateRequest {
	private UUID id;
	private String name;
	private String elements;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getElements() {
		return elements;
	}

	public void setElements(String elements) {
		this.elements = elements;
	}

}
