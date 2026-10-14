package sio.spring.todo.services;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import sio.spring.todo.entities.User;

@Service
public class AppService {

	private final HttpSession session;

	public AppService(HttpSession session) {
		this.session = session;
	}

	public User getCurrentUser() {
		Object o = session.getAttribute("currentUser");
		if (o != null) {
			return (User) o;
		}
		return null;
	}

	public void logout() {
		session.invalidate();
	}
}
