package sio.spring.todo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class TodoItem extends BaseObject {
	private String label;
	private boolean checked;

	@ManyToOne
	private TodoList list;

	public TodoItem() {
		this("Item");
	}

	public TodoItem(String label) {
		super();
		this.label = label;
		this.checked = false;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
